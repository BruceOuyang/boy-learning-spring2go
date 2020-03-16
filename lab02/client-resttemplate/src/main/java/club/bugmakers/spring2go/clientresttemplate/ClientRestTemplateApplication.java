package club.bugmakers.spring2go.clientresttemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Web 应用接入授权服务器 Demo
 * 示例使用授权码模式的授权服务器
 * @see  club.bugmakers.spring2go.clientresttemplate.oauth.AuthorizationCodeConfiguration#getBody(String)  grant_type
 *
 * 测试时，需要启动 lab01 里边的 authcode-server
 */
@SpringBootApplication
public class ClientRestTemplateApplication implements ServletContextInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ClientRestTemplateApplication.class);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.getSessionCookieConfig().setName("client-session");
    }
}
