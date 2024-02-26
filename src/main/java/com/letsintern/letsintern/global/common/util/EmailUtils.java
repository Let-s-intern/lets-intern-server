package com.letsintern.letsintern.global.common.util;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.program.domain.ProgramWay;
import com.letsintern.letsintern.domain.program.vo.ProgramEmailVo;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EmailUtils {
    private final JavaMailSender javaMailSender;
    private static final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    private final StringUtils stringUtils;

    private static final String MAIL_HEADER = "안녕하세요, 렛츠인턴입니다.\n";
    private static final String MAIL_HEADER_LONG = "안녕하세요,\n커리어의 시작을 함께 하는 렛츠인턴입니다.\n\n";
    private static final String MAIL_NOTICE = "혹시 사정이 생겨 참여하지 못하게 되시면, 꼭 미리 말씀 부탁드립니다!";
    private static final String MAIL_FROM = "감사합니다.\n렛츠인턴 드림\n\n";
    private static final String MAIL_FOOTER = "--\n오늘도 응원합니다.\n커리어의 첫 시작, 렛츠인턴\nE. letsintern.official@gmail.com\nH. https://www.letsintern.co.kr";

    public void sendPasswordResetEmail(String emailAddress, String tempPassword) {
        javaMailSender.send(createPasswordResetMessage(emailAddress, tempPassword));
    }

    public void sendApplicationApprovedEmail(String emailAddress, String name, ProgramEmailVo programEmailVo) {
        javaMailSender.send(createApplicationApprovedMessage(emailAddress, name, programEmailVo));
    }

    public void sendRemindMail(List<Application> applicationList, Program program) {
        javaMailSender.send(createRemindMessage(applicationList, program));
    }

    public void sendReviewMail(List<Application> applicationList, Program program) {
        javaMailSender.send(createReviewMessage(applicationList, program));
    }

    private SimpleMailMessage createApplicationApprovedMessage(String emailAddress, String name, ProgramEmailVo programEmailVo) {
        simpleMailMessage.setTo(emailAddress);
        simpleMailMessage.setSubject("[렛츠인턴] " + programEmailVo.getType().getValue() + " #" + programEmailVo.getTh() + ". " + programEmailVo.getTitle() + " 세션 확정 안내");

        simpleMailMessage.setText(
                "안녕하세요, " + name + "님\n" +
                "커리어의 첫 시작, 렛츠임팩트 입니다.\n\n" +
                createTitleInfo(programEmailVo.getType(), programEmailVo.getTh(), programEmailVo.getTitle()) + "에 신청해주셔서 감사합니다!\n" +
                programEmailVo.getType().getValue() + " #" + programEmailVo.getTh() + " 참여 확정되어 안내드립니다.\n\n" +
                createStartDateInfo(programEmailVo.getStartDate(), programEmailVo.getEndDate()) +
                createProgramWayInfo(programEmailVo.getWay(), programEmailVo.getLink(), programEmailVo.getLinkPassword(), programEmailVo.getLocation()) + "\n\n" +
                "세션 당일에 뵈어요 :)\n" + MAIL_NOTICE + "\n\n" +
                MAIL_FROM + MAIL_FOOTER);

        return simpleMailMessage;
    }

    private SimpleMailMessage createRemindMessage(List<Application> applicationList, Program program) {
        String[] emailAddressList = getEmailAddressList(applicationList);
        simpleMailMessage.setBcc(emailAddressList);
        simpleMailMessage.setSubject("[렛츠인턴] D-1 리마인드 안내: " + program.getTitle());

        simpleMailMessage.setText(
                MAIL_HEADER +
                "신청해주신 " + createTitleInfo(program.getType(), program.getTh(), program.getTitle()) + "이 하루 앞으로 다가왔습니다.\n" +
                "다들 잊지 않으셨죠? 내일 뵐게요!\n\n" +
                createStartDateInfo(program.getStartDate(), program.getEndDate()) +
                createProgramWayInfo(program.getWay(), program.getLink(), program.getLinkPassword(), program.getLocation()) + "\n\n" +
                "원활한 세션 진행을 위해 5분 전 입장 부탁드립니다.\n" + MAIL_NOTICE + "\n\n" +
                MAIL_FROM + MAIL_FOOTER);

        return simpleMailMessage;
    }


    private SimpleMailMessage createReviewMessage(List<Application> applicationList, Program program) {
        String[] emailAddressList = getEmailAddressList(applicationList);
        simpleMailMessage.setBcc(emailAddressList);
        simpleMailMessage.setSubject("[렛츠인턴] " + program.getType().getValue() + " #" + program.getTh() + ". " + program.getTitle() + " 후기 작성 안내");

        simpleMailMessage.setText(
                MAIL_HEADER +
                "시간 내어 " + createTitleInfo(program.getType(), program.getTh(), program.getTitle()) + "에 참여해주셔서 감사합니다!\n\n" +
                "렛츠챗 후기를 작성해주세요!\n" +
                "- 작성링크 : https://www.letsintern.co.kr/program/" + program.getId() + "/review/create " + "\n" +
                "- 모든 후기는 강연자님께 전달되고 있습니다. 후기를 작성해주시면 시간 내어 세션을 진행해주신 멘토님께 큰 힘이 될 수 있습니다 :)\n\n" +
                "렛츠인턴의 다양한 프로그램을 더 즐겨보세요!\n" +
                "- https://www.letsintern.co.kr " + "\n- 렛츠인턴에서 무료로 다양한 당일치기 워크숍, 더 많은 커리어 선배들이 들려주는 렛츠챗 세션 등을 모집 중에 있습니다. 오늘 세션이 만족스러우셨던 분들은 더 많이 즐겨주세요!\n\n" +
                "커리어의 시작점에서 고민이 있다면, 언제든 렛츠인턴을 찾아주세요!\n\n" +
                MAIL_FROM + MAIL_FOOTER);

        return simpleMailMessage;
    }

    public static String getChallengeApprovedEmailText(Program program) {
        return "[렛츠인턴] " + program.getTitle() + " 선발 및 입금 안내\n\n" +

                MAIL_HEADER_LONG +
                program.getTitle() + "에 선발되셨습니다.\n" +
                "작성해주신 지원동기에서 열정과 진심을 확인할 수 있었습니다.\n" +
                "2주간 열심히 참여하시어 좋은 성과 이루시길 바라겠습니다.\n\n" +

                "챌린지는 동기부여를 위한 보증금과 함께\n" +
                "현직자 연사 초청 및 네트워킹 마련 등을 위한 운영비를 받고 있습니다.\n\n" +

                "본격적인 안내에 앞서 " + (program.getFeeCharge() + program.getFeeRefund()) + "원을 입금 부탁 드립니다.\n" +
                "이때 입금주 명은 신청인 이름과 동일하게 설정해 주세요.\n\n" +

                "\uD83C\uDFE6 계좌 : " + program.getAccountType().getValue() + " " + program.getAccountNumber() + "\n" +
                "\uD83D\uDCC5 기한 : " + StringUtils.dateToString(program.getFeeDueDate()) + "까지\n\n" +

                "입금 기한 직후, 입금이 확인된 인원을 대상으로\n" +
                "오픈채팅방 및 온라인 OT 참여 링크, 챌린지 대시보드 접속 안내 등의 공지를 드립니다.\n\n" +

                "다시 한번 렛츠인턴에 지원해 주셔서 감사합니다.\n\n" +
                MAIL_FOOTER;
    }

    public static String getChallengeFeeConfirmedEmailText(Program program) {
        return "[렛츠인턴] " + program.getTitle() + " 참여 확정 공지\n\n" +

                MAIL_HEADER_LONG +
                "드디어 내일(" + StringUtils.dateToStringMMdd(program.getStartDate()) + ") OT와 함께 " + program.getTitle() + "가 시작합니다!\n" +
                "챌린지 참여에 핵심이 될 공지를 몇가지 드리려 합니다.\n" +
                "안내 사항에 따라 설정 및 접속 부탁 드리겠습니다.\n\n" +

                "[1] 챌린지 " + program.getTitle() + "기 카카오톡 오픈 채팅방에 들어와 주세요!\n" +
                "참여자들 간의 소통이 이루어지는 공간입니다.\n" +

                "이때 카카오프렌즈 프로필이 아닌 \"새로운 오픈 프로필 만들기\"로 가입해주세요!\n" +
                "프로필 우측 하단에 회색 박스가 있으면 됩니다.\n" +
                "사전에 세팅해 주셔야 1:1 대화가 가능합니다.\n\n" +

                "☑︎ 프로필 이미지 : 자신을 잘 나타낼 수 있는 이미지로 자유롭게\n" +
                "☑︎ 닉네임 : OOO_희망 직무 (ex. 홍길동_콘텐츠 마케팅, 김철수_개발)\n" +
                "☑︎ 채팅 설정 : 기본 프로필만 허용 '해제'\n\n" +

                "채팅 설정은 한번 프로필을 만든 후에는 수정이 어려워,\n" +
                "다시 만들어야 한다는 번거로움이 있어 상기 조건을 한번 더 확인해 주세요.\n" +
                "\uD83D\uDD17 링크: " + program.getOpenKakaoLink() + " \n" +
                "\uD83D\uDD10 비밀번호 : " + program.getOpenKakaoPassword() + "\n\n" +

                "[2] OT는 온라인으로 " + StringUtils.dateToStringMMddEaHHmm(program.getStartDate()) + "에 진행됩니다.\n" +
                "1시간 소요 예정으로 렛츠인턴 소개와 챌린지 운영 방식, 보증금 및 혜택을 안내 드립니다.\n" +
                "참여하신 분들만을 위해 커리큘럼을 상세하게 설명 드리오니 최대한 참여해 주세요.\n" +
                "\uD83D\uDD17 링크 : " + program.getLink() + " \n" +
                "\uD83D\uDD10 비밀번호 : " + program.getLinkPassword() + "\n\n" +

                "[3] 이번 챌린지는 렛츠인턴 웹사이트에서 콘텐츠 및 미션 공지와 인증이 이루어집니다.\n" +
                "신청하신 계정의 마이페이지에서 챌린지 대시보드에 바로 접속하실 수 있습니다.\n" +
                "금일 중으로 확인 부탁 드리며, 접속에 어려움 있으실 경우 회신해 주세요.\n" +
                "\uD83D\uDD17 링크 : https://www.letsintern.co.kr/ \n" +
                "\uD83D\uDC49 방법 : 로그인 후 마이페이지 → 신청 현황 → 참여 중 섹션 → 챌린지로 이동 클릭\n\n" +

                program.getTitle() + "에 함께 하게 되신 걸 다시 한번 환영합니다.\n" +
                "모두 내일 OT에서 만나요 \uD83D\uDE0A \n\n" +
                MAIL_FOOTER;
    }

    private String[] getEmailAddressList(List<Application> applicationList) {
        String[] emailAddresses = new String[applicationList.size()];

        for(int i=0 ; i<applicationList.size() ; i++) {
            if(applicationList.get(i).getEmail() != null)
                emailAddresses[i] = applicationList.get(i).getEmail();
            else
                emailAddresses[i] = applicationList.get(i).getUser().getEmail();
        }
        return emailAddresses;
    }

    private String createTitleInfo(ProgramType type, Integer th, String title) {
        return type.getValue() + " #" + th + " [" + title + "]";
    }

    private String createProgramWayInfo(ProgramWay way, String link, String linkPassword, String location) {
        String wayInfo;

        if(way.equals(ProgramWay.ONLINE)) {
            wayInfo = "- Zoom 링크: " + link + " \n" + "- Zoom 회의실 암호: " + linkPassword;
        }
        else if(way.equals(ProgramWay.OFFLINE)) {
            wayInfo = "- 장소: " + location;
        }
        else {
            wayInfo = "- Zoom 링크: " + link + "\n-Zoom 회의실 암호: " + linkPassword
                    + "\n- 장소: " + location;
        }

        return wayInfo;
    }

    private String createStartDateInfo(LocalDateTime startDate, LocalDateTime endDate) {
        return "- 일시 : " + stringUtils.dateToString(startDate) + " ~ " + stringUtils.dateToString(endDate).substring(14) + "\n";
    }

    private SimpleMailMessage createPasswordResetMessage(String emailAddress, String tempPassword) {
        simpleMailMessage.setTo(emailAddress);
        simpleMailMessage.setSubject("[렛츠인턴] 비밀번호 재설정 메일입니다.");
        simpleMailMessage.setText("안녕하세요, 렛츠인턴입니다.\n\n" +
                "비밀번호 재설정을 위한 임시 비밀번호는 아래와 같습니다.\n" +
                "해당 비밀번호로 로그인 후, 마이페이지에서 비밀번호를 재설정해주시길 바랍니다.\n감사합니다.\n\n" +
                "임시 비밀번호 : " + tempPassword);

        return simpleMailMessage;
    }

}
