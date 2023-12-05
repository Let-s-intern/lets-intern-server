package com.letsintern.letsintern.domain.user.util;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailUtil {
    private final JavaMailSender javaMailSender;

    public void sendEmail(String emailAddress, String tempPassword) {
        javaMailSender.send(createSMessage(emailAddress, tempPassword));
    }

    private SimpleMailMessage createSMessage(String emailAddress, String tempPassword) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(emailAddress);
        simpleMailMessage.setSubject("[렛츠인턴] 비밀번호 재설정 메일입니다.");
        simpleMailMessage.setText("안녕하세요, 렛츠인턴입니다.\n\n" +
                "비밀번호 재설정을 위한 임시 비밀번호는 아래와 같습니다.\n" +
                "해당 비밀번호로 로그인 후, 마이페이지에서 비밀번호를 재설정해주시길 바랍니다.\n감사합니다.\n\n" +
                "임시 비밀번호 : " + tempPassword);

        return simpleMailMessage;
    }
}
