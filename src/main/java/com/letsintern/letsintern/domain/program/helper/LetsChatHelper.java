package com.letsintern.letsintern.domain.program.helper;

import com.letsintern.letsintern.domain.program.domain.LetsChat;
import com.letsintern.letsintern.domain.program.repository.LetsChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
@RequiredArgsConstructor
public class LetsChatHelper {

    private final LetsChatRepository letsChatRepository;
    private final static int RANDOM_NUMBER_LENGTH = 4;

    public String generateMentorPassword() {
        SecureRandom secureRandom = new SecureRandom();
        int upperLimit = (int) Math.pow(10, RANDOM_NUMBER_LENGTH);

        String mentorPassword = String.valueOf(secureRandom.nextInt(upperLimit));
        while(mentorPassword.length() < RANDOM_NUMBER_LENGTH) {
            mentorPassword = "0" + mentorPassword;
        }

        return mentorPassword;
    }

    public void saveLetsChat(LetsChat letsChat) {
        letsChatRepository.save(letsChat);
    }
}
