package com.sist.ex0911_jwt.jwt;

import java.util.Base64;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.security.Keys;

@Component //@Component : 언제 어디서든 사용할 수 있게 어노테이션 지정
public class JwtProvider {
    
    @Value("${custom.jwt.secretKey}")
    private String secretKeyCode;

    private SecretKey secretKey; //보안키

    public SecretKey getSecretKey(){
        if(secretKey == null){
            String encoding =
                Base64.getEncoder().encodeToString(
                    secretKeyCode.getBytes()); //배열로 받기
        // secretKey는 숫자와 문자들을 내 맘대로 길게 기술한것이다.(별 의미없이 기술)
        // 그 값(secretKeyCode)을 가지고 jwt키를 만들어야 한다. 이때
        // jjwt라는 라이브러리를 사용하면 편하다!
            secretKey = Keys.hmacShaKeyFor(encoding.getBytes()); //key가 만들어진다.
        }
        return secretKey; //key를 얻어서 주고, 없다면 새로 만들어서 준다.
    }
}
