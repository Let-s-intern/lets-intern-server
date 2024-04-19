package com.letsintern.letsintern.domain.program.helper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.*;

class ProgramHelperMentorPasswordTest {

    @Test
    @DisplayName("랜덤 비밀번호 생성 자릿수 테스트 (기존)")
    void generateRandomNumber_previous() {
        // given
        int RANDOM_NUMBER_LENGTH = 4;
        int n = 100000;

        while(n-- > 0) {
            // when
            SecureRandom secureRandom = new SecureRandom();
            int upperLimit = (int) Math.pow(10, RANDOM_NUMBER_LENGTH);
            int mentorPassword = secureRandom.nextInt(upperLimit);

            // then
            System.out.println(mentorPassword);
            assertEquals(RANDOM_NUMBER_LENGTH, String.valueOf(mentorPassword).length());
        }

    }

    @Test
    @DisplayName("랜덤 비밀번호 생성 자릿수 테스트 (변경 후)")
    void generateRandomNumber() {
        // given
        int RANDOM_NUMBER_LENGTH = 4;
        int n = 100000;

        while(n-- > 0) {
            // when
            SecureRandom secureRandom = new SecureRandom();
            int upperLimit = (int) Math.pow(10, RANDOM_NUMBER_LENGTH);
            String mentorPassword = String.valueOf(secureRandom.nextInt(upperLimit));

            while(mentorPassword.length() < RANDOM_NUMBER_LENGTH) {
                mentorPassword = "0" + mentorPassword;
            }

            // then
            System.out.println(mentorPassword);
            assertEquals(RANDOM_NUMBER_LENGTH, mentorPassword.length());
        }
    }
}