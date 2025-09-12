package com.sist.ex0911_jwt.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
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

    public String genToken(Map<String, Object> map, int seconds){
        long now = new Date().getTime(); //현재시간 객체 생성

        Date accessTokenExpiresIn = new Date(now+1000L*seconds); //토큰 만료시간 지정
        // 1000L*seconds : 밀리초로 변환 - 만료시간

        JwtBuilder jwtBuilder = Jwts.builder().subject("SIST")
                                    .expiration(accessTokenExpiresIn);
        Set<String> keys = map.keySet(); //반복자 처리하기 위해 Set구조화
        Iterator<String> it = keys.iterator(); //Set구조안에 있는 Iterator 사용(인덱스 없음,
                                                // 무분별한 데이터를 반복하기좋게 나열한다.)
        while(it.hasNext()){
            String key = it.next();
            Object value = map.get(key);

            jwtBuilder.claim(key, value);
            /* 
                JWT(JSON Web Token)은 크게 3가지 영역으로 나뉜다.
                    1. Header(알고리즘, 타입)
                    2. Payload(데이터) *.토큰에 담긴 데이터
                    3. Signature(서명)
                     이중 Payload안에 들어있는 정보(data) 단위를 Claim이라 한다.
             */
        }//반복의 끝
        String jwt = jwtBuilder.signWith(getSecretKey()).compact(); //SecretKey는 문자열로 넘어온다.
        return jwt;
    }

    //토큰이 만료되었는지? 확인
    public boolean verify(String token){
        boolean value = true;

        try {
            Jwts.parser().verifyWith(getSecretKey()) //동일한 SecretKey를 갖고온다.
                    .build().parseSignedClaims(token); 
        } catch (Exception e) {
            // 유효기간이 만료되면 예외발생됨!
            value = false;
        }

        return value;
    }

    //토큰에 담긴 사용자정보(claims)를 반환한다.
    public Map<String, Object> getClaims(String token){
        return Jwts.parser().verifyWith(getSecretKey())
                        .build()
                        .parseSignedClaims(token)
                        .getPayload(); // 페이로드안에 있는 사용자정보(claims)을 반환한다.
    }
}
