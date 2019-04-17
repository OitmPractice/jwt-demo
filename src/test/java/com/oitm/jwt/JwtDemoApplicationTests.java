package com.oitm.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtDemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    public static final String SIGN_KEY = "jwt_sign_key";
    @Test
    public void createJwt(){
        String id = "124567890";

        String jwtToken = Jwts.builder().setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .setId(id)
                .setSubject("Oitm") //可传递自定义对象
                .signWith(SignatureAlgorithm.HS512, SIGN_KEY)
                .claim("name","oitm")
                .claim("age","18")
                .compact();
        System.out.println(jwtToken);

    //
    }

    @Test
    public void jwtParse(){
        //这里替换成你生成的token
        String token = "eyJhbGciOiJIUzUxMiJ9." +
                "eyJpYXQiOjE1NTU1MDE5NjcsImV4cCI6MTU1NTU4ODM2NywianRpIjoiMTI0NTY3ODkwIiwic3ViIjoiT2l0bSIsIm5hbWUiOiJvaXRtIiwiYWdlIjoiMTgifQ." +
                "pJGzqtB6fK6wTTfGR7YrJs8aocAhhe7RHVRRUeXAx2RaQndkUwPLQs-9ZzGJv4YSye11Jn6CmqNxvY4IZPm4mQ";
        Claims body = Jwts.parser().setSigningKey(SIGN_KEY).parseClaimsJws(token).getBody();
        System.out.println(body.getExpiration());
        System.out.println(body.getIssuedAt());
        System.out.println(body.getId());
        System.out.println(body.getSubject());
        System.out.println(body.get("name"));
        System.out.println(body.get("age"));
    }



}
