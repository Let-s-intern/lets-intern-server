package com.letsintern.letsintern.domain.application.service;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationChallengeUpdateDTO;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationUpdateDTO;
import com.letsintern.letsintern.domain.application.dto.response.*;
import com.letsintern.letsintern.domain.application.exception.ApplicationNotFound;
import com.letsintern.letsintern.domain.application.helper.ApplicationHelper;
import com.letsintern.letsintern.domain.application.mapper.ApplicationMapper;
import com.letsintern.letsintern.domain.application.repository.ApplicationRepository;
import com.letsintern.letsintern.domain.coupon.domain.CouponProgramType;
import com.letsintern.letsintern.domain.coupon.domain.CouponUser;
import com.letsintern.letsintern.domain.coupon.helper.CouponHelper;
import com.letsintern.letsintern.domain.coupon.mapper.CouponMapper;
import com.letsintern.letsintern.domain.coupon.vo.CouponUserHistoryVo;
import com.letsintern.letsintern.domain.mission.repository.MissionRepository;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.domain.ProgramFeeType;
import com.letsintern.letsintern.domain.program.helper.ProgramHelper;
import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.domain.user.helper.UserHelper;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Transactional
@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ApplicationHelper applicationHelper;
    private final ApplicationMapper applicationMapper;
    private final ProgramHelper programHelper;
    private final CouponHelper couponHelper;
    private final CouponMapper couponMapper;
    private final UserHelper userHelper;
    private final MissionRepository missionRepository;

    @Transactional
    public ApplicationCreateResponse createUserApplication(Long programId, ApplicationCreateDTO applicationCreateDTO, PrincipalDetails principalDetails) {
        User user = principalDetails.getUser();
        applicationHelper.validateDuplicateApplication(programId, user);
        checkUserDetailInfoAndUpdateInfo(user, applicationCreateDTO);
        Program program = programHelper.findProgramOrThrow(programId);
        checkAccountInfoForProgramRefundTypeAndUpdate(user, applicationCreateDTO, program.getFeeType());
        Integer discountValue = checkCouponAppliedAndGetDiscountValue(user, applicationCreateDTO);
        Integer totalFee = applicationHelper.calculateTotalFee(program, discountValue);
        Application newUserApplication = createApplicationAndSave(user, programId, applicationCreateDTO, totalFee);
        updateProgramApplicationCountAndSave(program);
        return applicationMapper.toApplicationCreateResponse(newUserApplication);
    }

    @Transactional
    public ApplicationCreateResponse createGuestApplication(Long programId, ApplicationCreateDTO applicationCreateDTO) {
        return applicationHelper.createGuestApplication(programId, applicationCreateDTO);
    }

    public AdminApplicationListResponse getApplicationListOfProgram(Long programId, Pageable pageable) {
        return applicationMapper.toAdminApplicationListResponse(applicationHelper.getApplicationListOfProgramId(programId, pageable));
    }

    public AdminApplicationListResponse getApplicationListOfProgramAndApproved(Long programId, Boolean approved, Pageable pageable) {
        return applicationMapper.toAdminApplicationListResponse(
                applicationHelper.getApplicationListOfProgramIdAndApproved(programId, approved, pageable)
        );
    }

    public UserApplicationListResponse getApplicationListOfUser(Long userId, Pageable pageable) {
        return applicationMapper.toUserApplicationListResponse(applicationHelper.getApplicationListOfUserId(userId, pageable));
    }

    public ApplicationListResponse getAdminApplicationListOfUserId(Long userId, Pageable pageable) {
        return applicationMapper.toApplicationListResponse(applicationHelper.getAdminApplicationListOfUserId(userId, pageable));
    }

    @Transactional
    public ApplicationIdResponse updateApplication(Long applicationId, ApplicationUpdateDTO applicationUpdateDTO) {
        return applicationMapper.toApplicationIdResponse(applicationHelper.updateApplication(applicationId, applicationUpdateDTO));
    }

    @Transactional
    public void deleteApplication(Long applicationId) {
        applicationHelper.deleteApplication(applicationId);
    }

    public ApplicationValidityResponse checkApplicationValidity(Long programId, PrincipalDetails principalDetails) {
        final User user = principalDetails.getUser();
        return ApplicationValidityResponse.from(applicationRepository.findByProgramIdAndUserIdAndIsApproved(programId, user.getId(), true).orElse(null) != null);
    }

    @Transactional
    public ApplicationIdResponse updateChallengeApplication(Long applicationId, ApplicationChallengeUpdateDTO applicationChallengeUpdateDTO, PrincipalDetails principalDetails) {
        final User user = principalDetails.getUser();
        return applicationMapper.toApplicationIdResponse(applicationHelper.updateChallengeApplication(applicationId, applicationChallengeUpdateDTO, user.getId()));
    }

    public EmailListResponse getEmailList(Long programId) {
        return applicationMapper.toEmailListResponse(
                applicationRepository.findAllEmailByIsApproved(programId, true),
                applicationRepository.findAllEmailByIsApproved(programId, false)
        );
    }

    public ApplicationChallengeAdminVosResponse getApplicationChallengeAdminList(Long programId, Pageable pageable, String name, String email, String phoneNum) {
        return applicationMapper.toApplicationChallengeAdminVosResponse(applicationHelper.getApplicationChallengeAdminList(programId, pageable, name, email, phoneNum));
    }

    public ApplicationChallengeAdminVoDetail getApplicationChallengeAdminDetail(Long applicationId) {
        final Application application = applicationRepository.findById(applicationId).orElseThrow(() -> ApplicationNotFound.EXCEPTION);
        return ApplicationChallengeAdminVoDetail.of(application.getApplyMotive(), missionRepository.getMissionAdminApplicationVos(application.getProgram().getId(), application.getUser().getId()));
    }

    /* 대학 & 전공 추가 정보 없는 사용자 */
    private void checkUserDetailInfoAndUpdateInfo(User user, ApplicationCreateDTO applicationCreateDTO) {
        if (userHelper.checkDetailInfoExist(user)) return;
        applicationHelper.validateHasUserDetailInfo(applicationCreateDTO);
        userHelper.addUserDetailInfo(user, applicationCreateDTO.getUniversity(), applicationCreateDTO.getMajor());
    }

    /* 보증금 프로그램인데 계좌 추가 정보 없는 사용자 */
    private void checkAccountInfoForProgramRefundTypeAndUpdate(User user, ApplicationCreateDTO applicationCreateDTO, ProgramFeeType programFeeType) {
        if (!(programFeeType.equals(ProgramFeeType.REFUND) && !userHelper.checkDetailAccountInfoExist(user))) return;
        applicationHelper.validateHasUserAccountInfo(applicationCreateDTO);
        userHelper.addUserDetailAccountInfo(user, applicationCreateDTO.getAccountType(), applicationCreateDTO.getAccountNumber());
    }


    /* 프로그램 등록시 쿠폰 사용여부 판단 */
    private Integer checkCouponAppliedAndGetDiscountValue(User user, ApplicationCreateDTO applicationCreateDTO) {
        if (!isCouponApplied(applicationCreateDTO.getCode()))
            return 0;
        CouponUserHistoryVo couponUserHistoryVo = couponHelper.findCouponUserHistoryVoOrCreate(user, applicationCreateDTO.getCode());
        CouponProgramType couponProgramType = couponMapper.toCouponProgramType(applicationCreateDTO.getCouponProgramType());
        couponHelper.validateApplyTimeForCoupon(couponUserHistoryVo.coupon().getStartDate(), couponUserHistoryVo.coupon().getEndDate());
        couponHelper.validateRemainTimeForUser(couponUserHistoryVo.remainTime());
        couponHelper.validateAvailableCouponProgram(couponUserHistoryVo.coupon().getId(), couponProgramType);
        CouponUser couponUser = getCouponHistoryOrCreateCouponUser(user, couponUserHistoryVo);
        couponUser.decreaseRemainTime();
        return couponUserHistoryVo.coupon().getDiscount();
    }

    private CouponUser getCouponHistoryOrCreateCouponUser(User user, CouponUserHistoryVo couponUserHistoryVo) {
        if (isCouponUsed(couponUserHistoryVo.user()))
            return couponHelper.findCouponUserByCouponIdAndUserIdThrow(couponUserHistoryVo.coupon().getId(), couponUserHistoryVo.user().getId());
        else
            return createCouponUserAndSave(user, couponUserHistoryVo);
    }

    private CouponUser createCouponUserAndSave(User user, CouponUserHistoryVo couponUserHistoryVo) {
        CouponUser couponUser = CouponUser.createCouponUser(couponUserHistoryVo.coupon(), user);
        return couponHelper.saveCouponUser(couponUser);
    }

    private Application createApplicationAndSave(User user, Long programId, ApplicationCreateDTO applicationCreateDTO, Integer totalFee) {
        Application newUserApplication = applicationMapper.toEntity(programId, applicationCreateDTO, user, totalFee);
        return applicationRepository.save(newUserApplication);
    }

    /* program application count 증가 및 저장 */
    private void updateProgramApplicationCountAndSave(Program program) {
        program.increaseProgramApplicationCount();
        programHelper.saveProgram(program);
    }

    private boolean isCouponApplied(String code) {
        return !Objects.isNull(code);
    }

    private boolean isCouponUsed(User user) {
        return !Objects.isNull(user);
    }
}