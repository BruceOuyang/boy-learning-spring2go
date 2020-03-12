package club.bugmakers.spring2go.passwordserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 基于授权码模式+Spring Security OAuth2的最简授权服务器
 *
 * 实验步骤：
 * 1、获取 token
 * 通过 postman 工具，创建一个新的请求，
 * 设置请求：类型 post，地址 http://localhost:8080/oauth/token
 * 设置 Params：grant_type=password，username=bruce，password=123456，scope=read_userinfo
 * 设置 Authorization：type = Basic Auth，Username = bruce ，Password = 123456
 * 设置 Headers：Content-Type = application/x-www-form-urlencoded，Accept=application/json
 * 点击 send 按钮在响应信息中可以获得 token
 *
 * 2、使用 token 调用资源服务器接口
 *  * 通过 postman 工具，创建一个新的请求，
 *  * 设置请求：类型 post，地址 http://localhost:8080/api/userinfo
 *  * 设置 Authorization：type = Bearer token，token = （上一步获取到的 token）
 */
@SpringBootApplication
public class PasswordServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PasswordServerApplication.class);
    }
}
