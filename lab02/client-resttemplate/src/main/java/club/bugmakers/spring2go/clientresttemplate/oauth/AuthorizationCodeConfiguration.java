package club.bugmakers.spring2go.clientresttemplate.oauth;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.Base64;

@Component
public class AuthorizationCodeConfiguration {

    public String encodeCredentials(String username, String password) {
        String credentials = username + ":" + password;
        String encodeed = new String(Base64.getEncoder().encode(credentials.getBytes()));
        return encodeed;
    }

    public MultiValueMap<String, String> getBody(String authorizationCode) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "authorization_code");
        formData.add("scope", "read_userinfo");
        formData.add("code", authorizationCode);
        formData.add("redirect_uri", "http://localhost:9001/callback");
        return formData;
    }

    public HttpHeaders getHeader(String clientAuthentication) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.add("Authorization", "Basic " + clientAuthentication);
        return httpHeaders;
    }

}
