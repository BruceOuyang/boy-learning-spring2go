package club.bugmakers.spring2go.jwtresourceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 基于 jwt 的资源服务器
 *
 * 通过 postman 发送 get 请求到 http://localhost:8081/api/userInfo
 *
 * Authorization: type = Bearer Token, token = {jwt授权服务器分会的token}
 */
@SpringBootApplication
public class JwtResourceServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(JwtResourceServerApplication.class, args);
    }
}
