package club.bugmakers.spring2go.clientresttemplate.controller;

import club.bugmakers.spring2go.clientresttemplate.oauth.AuthorizationCodeTokenService;
import club.bugmakers.spring2go.clientresttemplate.oauth.OAuth2Token;
import club.bugmakers.spring2go.clientresttemplate.security.ClientUserDetails;
import club.bugmakers.spring2go.clientresttemplate.user.ClientUser;
import club.bugmakers.spring2go.clientresttemplate.user.Entry;
import club.bugmakers.spring2go.clientresttemplate.user.UserInfo;
import club.bugmakers.spring2go.clientresttemplate.user.UserRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@Controller
public class MainPageController {

    @Autowired
    private AuthorizationCodeTokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/callback")
    public ModelAndView callback(String code, String state) {
        ClientUserDetails userDetails = (ClientUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ClientUser clientUser = userDetails.getClientUser();

        OAuth2Token token = tokenService.getToken(code);
        clientUser.setAccessToken(token.getAccessToken());

        Calendar tokenValidity = Calendar.getInstance();
        tokenValidity.setTime(new Date(Long.parseLong(token.getExpiresIn())));
        clientUser.setAccessTokenValidity(tokenValidity);

        userRepository.save(clientUser);

        return new ModelAndView("redirect:/mainpage");
    }

    @GetMapping("/mainpage")
    public ModelAndView mainpage(){
        ClientUserDetails userDetails = (ClientUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ClientUser clientUser = userDetails.getClientUser();

        if (clientUser.getAccessToken() == null) {
            String authEndpoint = tokenService.getAuthorizationEndpoint();
            return new ModelAndView("redirect:"+authEndpoint);
        }

        clientUser.setEntries(Arrays.asList(
                new Entry("entry 1"),
                new Entry("entry 2")));

        ModelAndView mv = new ModelAndView("mainpage");
        mv.addObject("user", clientUser);

        tryToGetUserInfo(mv, clientUser.getAccessToken());

        return mv;
    }

    private void tryToGetUserInfo(ModelAndView mv, String token) {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", "Bearer " + token);
        String endpoint = "http://localhost:8080/api/userinfo";

        try {
            RequestEntity<Object> request = new RequestEntity<>(
                    headers, HttpMethod.GET, URI.create(endpoint));

            ResponseEntity<UserInfo> userInfo = restTemplate.exchange(request, UserInfo.class);

            if (userInfo.getStatusCode().is2xxSuccessful()) {
                mv.addObject("userInfo", userInfo.getBody());
            } else {
                throw new RuntimeException("it was not possible to retrieve user profile");
            }
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("it was not possible to retrieve user profile");
        }
    }
}
