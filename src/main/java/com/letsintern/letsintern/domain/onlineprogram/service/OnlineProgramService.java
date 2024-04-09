package com.letsintern.letsintern.domain.onlineprogram.service;

import com.letsintern.letsintern.domain.file.helper.S3Helper;
import com.letsintern.letsintern.domain.file.vo.S3SavedFileVo;
import com.letsintern.letsintern.domain.onlineprogram.domain.OnlineProgram;
import com.letsintern.letsintern.domain.onlineprogram.dto.request.OnlineProgramCreateDTO;
import com.letsintern.letsintern.domain.onlineprogram.dto.request.OnlineProgramUpdateDTO;
import com.letsintern.letsintern.domain.onlineprogram.dto.response.OnlineProgramAdminListResponse;
import com.letsintern.letsintern.domain.onlineprogram.helper.OnlineProgramHelper;
import com.letsintern.letsintern.domain.onlineprogram.mapper.OnlineProgramMapper;
import com.letsintern.letsintern.domain.onlineprogram.vo.OnlineProgramAdminVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class OnlineProgramService {

    private final OnlineProgramHelper onlineProgramHelper;
    private final OnlineProgramMapper onlineProgramMapper;
    private final S3Helper s3Helper;
    private static final String S3_ONLINE_PROGRAM_DIR = "online-program/thumbnail/";

    public void createOnlineProgram(OnlineProgramCreateDTO onlineProgramCreateDTO, MultipartFile file) {
        S3SavedFileVo s3SavedFileVo = s3Helper.saveFile(file, S3_ONLINE_PROGRAM_DIR);
        OnlineProgram newOnlineProgram = onlineProgramMapper.toEntity(onlineProgramCreateDTO, s3SavedFileVo.getS3Url());
        onlineProgramHelper.saveOnlineProgram(newOnlineProgram);
    }

    public OnlineProgramAdminListResponse getOnlineProgramListForAdmin(Pageable pageable) {
        Page<OnlineProgramAdminVo> onlineProgramAdminVoPage = onlineProgramHelper.getOnlineProgramAdminList(pageable);
        return onlineProgramMapper.toOnlineProgramAdminListResponse(onlineProgramAdminVoPage);
    }

    public void UpdateOnlineProgram(Long id, OnlineProgramUpdateDTO onlineProgramUpdateDTO, MultipartFile file) {
        OnlineProgram onlineProgram = onlineProgramHelper.findOnlineProgramById(id);
        S3SavedFileVo s3SavedFileVo = s3Helper.changeImgFile(S3_ONLINE_PROGRAM_DIR, onlineProgram.getThumbnailUrl(), file);
        onlineProgram.updateOnlineProgram(onlineProgramUpdateDTO, s3SavedFileVo);
    }
}
