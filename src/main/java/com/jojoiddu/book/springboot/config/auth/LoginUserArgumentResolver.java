package com.jojoiddu.book.springboot.config.auth;

import com.jojoiddu.book.springboot.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver { //조건에 맞는 경우 메소드가 있다면 구현체가 지정한 값으로 해당 메소드의 파라미터로 넘길 수 있다.

    private final HttpSession httpSession;

    @Override
    /*
    * 컨트롤러 메서드의 특정 파라미터를 지원하는지 판단
    * Login User Annotation이 붙어있고 파라미터 클래스 타입이 SessionUser인경우 true를 반환
    * */
    public boolean supportsParameter(MethodParameter parameter){ 
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) !=null;
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());
        return isLoginUserAnnotation && isUserClass;
    }

    @Override
    /*
    * 파라미터에 전달할 객체를 작성
    * 세션에서 객체를 가져옴
    * */
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return httpSession.getAttribute("user");
    }
}
