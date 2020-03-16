package club.bugmakers.spring2go.jwtauthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 基于 jwt 的授权服务器
 *
 * 通过 postman 发送 post 请求到 http://localhost:8080/oauth/token
 * Authorization: type = Basic Auth, username = clientapp, password = 123456
 * Headers: Accept = application/json, Content-Type = application/x-www.form-urlencoded
 * Params: grant_type = password, username=bruce, password=123456, scope=read_userinfo
 */
@SpringBootApplication
public class JwtAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtAuthServerApplication.class, args);
    }
}
