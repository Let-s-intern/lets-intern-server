package com.letsintern.letsintern.domain.program.helper;

import com.letsintern.letsintern.domain.program.domain.LetsChat;
import com.letsintern.letsintern.domain.program.exception.LetsChatNotFoundException;
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

        StringBuilder mentorPassword = new StringBuilder(String.valueOf(secureRandom.nextInt(upperLimit)));
        while(mentorPassword.length() < RANDOM_NUMBER_LENGTH) {
            mentorPassword.insert(0, "0");
        }

        return mentorPassword.toString();
    }

    public LetsChat saveLetsChat(LetsChat letsChat) {
        return letsChatRepository.save(letsChat);
    }

    public LetsChat findLetsChatByIdOrThrow(Long id) {
        return letsChatRepository.findById(id).orElseThrow(() -> LetsChatNotFoundException.EXCEPTION);
    }

    public void deleteLetsChat(LetsChat letsChat) {
        letsChatRepository.delete(letsChat);
    }
}
