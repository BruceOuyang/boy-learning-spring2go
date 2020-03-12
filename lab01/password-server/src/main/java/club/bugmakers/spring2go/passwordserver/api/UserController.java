package club.bugmakers.spring2go.passwordserver.api;

import club.bugmakers.spring2go.passwordserver.model.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/api/userinfo")
    public ResponseEntity<UserInfo> getUserInfo() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User user = (User) authentication.getPrincipal();

        String email = user.getUsername() + "@bugmakers.club";

        return ResponseEntity.ok(UserInfo.builder().name(user.getUsername()).email(email).build());
    }
}
