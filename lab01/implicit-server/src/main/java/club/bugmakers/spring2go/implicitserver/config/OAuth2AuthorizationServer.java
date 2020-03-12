package club.bugmakers.spring2go.implicitserver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * 基于 OAuth2 的授权服务器
 */
@Configuration
@ConfigurationProperties(prefix = "oauth2.authorization.config")
@EnableAuthorizationServer
@Data
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {

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
