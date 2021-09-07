package com.jojoiddu.book.springboot.web.dto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter //모든 get메서드를 생성합니다.
@RequiredArgsConstructor //생성된 모든 final필드가 포함된 생성자를 생성해줍니다.
public class HelloResponseDto {

    private final String name;
    private final int amount;

}
