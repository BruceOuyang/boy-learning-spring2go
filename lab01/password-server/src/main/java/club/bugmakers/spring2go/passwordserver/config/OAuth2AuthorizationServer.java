package club.bugmakers.spring2go.passwordserver.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * 基于 OAuth2 的授权服务器
 */
@Configuration
@ConfigurationProperties(prefix = "oauth2.authorization.config")
@EnableAuthorizationServer
@Data
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    // 用户认证
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }

    private String clientId;
    private String secret;
    private String[] redirectUris;
    private String[] grantTypes;
    private String[] scopes;
    private Integer tokenValidSeconds;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(clientId)
                .secret(secret)
                .redirectUris(redirectUris)
                .authorizedGrantTypes(grantTypes)
                .accessTokenValiditySeconds(tokenValidSeconds)
                .scopes(scopes);
    }
}
