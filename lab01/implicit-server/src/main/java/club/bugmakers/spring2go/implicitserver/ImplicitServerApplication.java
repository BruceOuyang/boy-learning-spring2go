package club.bugmakers.spring2go.implicitserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 基于授权码模式+Spring Security OAuth2的最简授权服务器
 *
 * 实验步骤：
 * 1、获取 token
 * 通过浏览器访问 http://localhost:8080/oauth/authorize?client_id=bruce&redirect_uri=http://localhost:8080/callback&response_type=token&scope=read_userinfo&state=abc
 * 在弹出的校验框输入 application.properties 配置好的用户名 bruce 密码 123456，提交
 * 在授权页面选择 accept 确认之后，出现回调页面，会在回调页面得到一个响应的 token
 *
 * 2、使用 token 调用资源服务器接口
 * 通过 postman 工具，创建一个新的请求，
 * 设置请求：类型 post，地址 http://localhost:8080/api/userinfo
 * 设置 Authorization：type = Bearer token，token = （上一步获取到的 token）
 */
@SpringBootApplication
public class ImplicitServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImplicitServerApplication.class);
    }
}
