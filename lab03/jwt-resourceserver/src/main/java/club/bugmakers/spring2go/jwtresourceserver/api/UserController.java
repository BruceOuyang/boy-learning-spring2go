package club.bugmakers.spring2go.jwtresourceserver.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/api/userInfo")
    public ResponseEntity<UserInfo> getUserInfo() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = username + "@bugmakers.club";

        return ResponseEntity.ok(new UserInfo(username, email));
    }
}
