package com.letsintern.letsintern.global.common.util;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.domain.ProgramWay;
import com.letsintern.letsintern.domain.program.vo.ProgramEmailVo;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public void sendReviewMail(List<Application> applicationList, Program program) {
        javaMailSender.send(createReviewMessage(applicationList, program));
    }

    private SimpleMailMessage createApplicationApprovedMessage(String emailAddress, String name, ProgramEmailVo programEmailVo) {
        simpleMailMessage.setTo(emailAddress);
        simpleMailMessage.setSubject("[렛츠인턴] " + programEmailVo.getType().getValue() + " #" + programEmailVo.getTh() + ". " + programEmailVo.getTitle() + " 세션 확정 안내");

        String way;
        if(programEmailVo.getWay().equals(ProgramWay.ONLINE)) {
            way = "- Zoom 링크: " + programEmailVo.getLink() + " \n" + "- Zoom 회의실 암호: " + programEmailVo.getLinkPassword();
        }
        else if(programEmailVo.getWay().equals(ProgramWay.OFFLINE)) {
            way = "- 장소: " + programEmailVo.getLocation();
        }
        else {
            way = "- Zoom 링크: " + programEmailVo.getLink() + "\n-Zoom 회의실 암호: " + programEmailVo.getLinkPassword()
                    + "\n- 장소: " + programEmailVo.getLocation();
        }

        simpleMailMessage.setText(
                "안녕하세요, " + name + "님\n" +
                "커리어의 첫 시작, 렛츠임팩트 입니다.\n\n" +
                programEmailVo.getType().getValue() + " #" + programEmailVo.getTh() + " [" + programEmailVo.getTitle() + "] 에 신청해주셔서 감사합니다!\n" +
                programEmailVo.getType().getValue() + " #" + programEmailVo.getTh() + " 참여 확정되어 안내드립니다.\n\n" +
                "- 일시 : " + stringUtils.dateToString(programEmailVo.getStartDate()) + " ~ " + stringUtils.dateToString(programEmailVo.getEndDate()).substring(14) + "\n" +
                way + "\n\n" +
                "세션 당일에 뵈어요 :)\n혹시 사정이 생겨 참여하지 못하게 되시면, 꼭 미리 말씀 부탁드립니다!\n\n" +
                "감사합니다.\n렛츠인턴 드림\n\n" +
                "--\n오늘도 응원합니다.\n커리어의 첫 시작, 렛츠인턴\nE. letsintern.official@gmail.com\nH. https://www.letsintern.co.kr"
                );

        return simpleMailMessage;
    }

    private SimpleMailMessage createReviewMessage(List<Application> applicationList, Program program) {
        String emailAddresses = "";
        for(Application app : applicationList) {
            if(app.getEmail() != null) emailAddresses += (";" + app.getEmail());
            else emailAddresses += (";" + app.getUser().getEmail());
        }
        simpleMailMessage.setTo(emailAddresses);
        simpleMailMessage.setSubject("[렛츠인턴] " + program.getType().getValue() + " #" + program.getTh() + ". " + program.getTitle() + " 후기 작성 안내");

        simpleMailMessage.setText(
                "안녕하세요, 렛츠인턴입니다.\n" +
                        "시간 내어 " + program.getType().getValue() + " #" + program.getTh() + " [" + program.getTitle() + "] 에 참여해주셔서 감사합니다!\n\n" +
                        "렛츠챗 후기를 작성해주세요!\n" +
                        "- 작성링크 : https://www.letsintern.co.kr/program/" + program.getId() + "/review/create " + "\n" +
                        "- 모든 후기는 강연자님께 전달되고 있습니다. 후기를 작성해주시면 시간 내어 세션을 진행해주신 멘토님께 큰 힘이 될 수 있습니다 :)\n\n" +
                        "렛츠인턴의 다양한 프로그램을 더 즐겨보세요!\n" +
                        "- https://www.letsintern.co.kr " + "\n- 렛츠인턴에서 무료로 다양한 당일치기 워크숍, 더 많은 커리어 선배들이 들려주는 렛츠챗 세션 등을 모집 중에 있습니다. 오늘 세션이 만족스러우셨던 분들은 더 많이 즐겨주세요!\n\n" +
                        "커리어의 시작점에서 고민이 있다면, 언제든 렛츠인턴을 찾아주세요!\n\n" +
                        "감사합니다.\n렛츠인턴 드림\n\n" +
                        "--\n오늘도 응원합니다.\n커리어의 첫 시작, 렛츠인턴\nE. letsintern.official@gmail.com\nH. https://www.letsintern.co.kr"
        );

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
