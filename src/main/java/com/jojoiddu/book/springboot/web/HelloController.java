package com.jojoiddu.book.springboot.web;

import com.jojoiddu.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
/*
전통적인 Spring mvc 컨트롤러 @Controller는 view를 반환함
But data를 반환해야 하는 경우도 존재함 @ResponseBody를 사용함.
이 둘을 합친 annotation이 RestController이다.
*/
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HelloResponseDto(name,amount);
    }
}
