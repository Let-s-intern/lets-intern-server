package com.letsintern.letsintern.global.common.util;

import com.letsintern.letsintern.domain.program.domain.ProgramWay;
import com.letsintern.letsintern.domain.program.vo.ProgramEmailVo;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailUtils {
    private final JavaMailSender javaMailSender;
    private static final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    private final StringUtils stringUtils;

    public void sendPasswordResetEmail(String emailAddress, String tempPassword) {
        javaMailSender.send(createPasswordResetMessage(emailAddress, tempPassword));
    }

    public void sendApplicationApprovedEmail(String emailAddress, String name, ProgramEmailVo programEmailVo) {
        javaMailSender.send(createApplicationApprovedMessage(emailAddress, name, programEmailVo));
    }

    private SimpleMailMessage createApplicationApprovedMessage(String emailAddress, String name, ProgramEmailVo programEmailVo) {
        simpleMailMessage.setTo(emailAddress);
        simpleMailMessage.setSubject("[렛츠인턴] " + programEmailVo.getType().getValue() + " #" + programEmailVo.getTh() + ". " + programEmailVo.getTitle() + " 세션 확정 안내");

        String way = (programEmailVo.getWay().equals(ProgramWay.ONLINE)) ? "- Zoom 링크: " : "- 장소: ";
        simpleMailMessage.setText(
                "안녕하세요, " + name + "님\n" +
                "커리어의 첫 시작, 렛츠임팩트 입니다.\n\n" +
                programEmailVo.getType().getValue() + " #" + programEmailVo.getTh() + " [" + programEmailVo.getTitle() + "] 에 신청해주셔서 감사합니다!\n" +
                "참여 확정되어 안내드립니다.\n\n" +
                "- 일시 : " + stringUtils.dateToString(programEmailVo.getStartDate()) + " ~ " + stringUtils.dateToString(programEmailVo.getEndDate()).substring(14) + "\n" +
                way + programEmailVo.getLocation() + "\n\n" +
                "세션 당일에 뵈어요 :)\n혹시 사정이 생겨 참여하지 못하게 되시면, 꼭 미리 말씀 부탁드립니다!\n\n" +
                "감사합니다.\n렛츠인턴 드림");

        return simpleMailMessage;
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
