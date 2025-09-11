package com.sist.ex0911_jwt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.sist.ex0911_jwt.jwt.JwtProvider;

import io.jsonwebtoken.security.Keys;

// 단언테스트를 static import를 하자!
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

@SpringBootTest
class Ex0911JwtApplicationTests {

	@Value("${custom.jwt.secretKey}") //application-secret.yml에 정의된 key 가져오기
	private String secretKeyCode;

	@Test
	@DisplayName("시크릿Key 코드 체크")
	void contextLoads() {
		//단언: assertJ
		assertThat(secretKeyCode).isNotNull();
	}

	@Test
	@DisplayName("암호화알고리즘 시크릿key 암호화")
	void genBase64(){
		String encoding = 
			Base64.getEncoder().encodeToString(secretKeyCode.getBytes());
		
			SecretKey sKey = Keys.hmacShaKeyFor(encoding.getBytes());

			assertThat(sKey).isNotNull();
	}

	@Autowired
	private JwtProvider jwtProvider;

	@Test
	@DisplayName("accessToken발급")
	void tokenTest(){
		Map<String, Object> claims = new HashMap<>();
		claims.put("id","admin");
		claims.put("uname","어드민");
		claims.put("upwd","1111");
		claims.put("uemail","admin@korea.com");

		String accessToken = jwtProvider.getToken(claims, 60*60*3); //3시간뒤 만료예정
		System.out.println("ACCESS-TOKEN:"+accessToken);

		assertThat(accessToken).isNotNull();
	}

}
