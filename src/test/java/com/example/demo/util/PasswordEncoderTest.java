package com.example.demo.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class PasswordEncoderTest {

    @Test
    @DisplayName("비밀번호 인코딩 테스트")
    void encode(){
        //Given
        String rawPassword = "abc123";

        //When
        String encodedPassword = PasswordEncoder.encode(rawPassword);

        //Then
        assertThat(encodedPassword).isNotNull();
        assertThat(encodedPassword).isNotEqualTo(rawPassword);
        assertThat(encodedPassword).startsWith("$2a$");

    }

    @Test
    @DisplayName("비밀번호 매칭 성공 테스트")
    void matchesSuccess(){
        //Given
        String rawPassword = "abc123";
        String encodePassword = PasswordEncoder.encode(rawPassword);

        //when
        boolean isMatched = PasswordEncoder.matches(rawPassword, encodePassword);

        //Then
        assertThat(isMatched).isTrue();
    }

    @Test
    @DisplayName("비밀번호 매칭 실패 테스트")
    void machesFailure(){
        //Given
        String rawPassword = "abc123";
        String wrongPassword = "123";
        String encodePassword = PasswordEncoder.encode(rawPassword);

        //When
        boolean isMatched = PasswordEncoder.matches(wrongPassword, encodePassword);

        //Then
        assertThat(isMatched).isFalse();
    }
}