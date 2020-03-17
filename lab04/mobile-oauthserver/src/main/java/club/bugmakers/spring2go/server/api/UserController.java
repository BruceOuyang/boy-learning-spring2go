package club.bugmakers.spring2go.server.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/api/userinfo")
    public ResponseEntity<UserInfo> getUserInfo(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = user.getUsername() + "@bugmakers.club";

        UserInfo userInfo = new UserInfo(user.getUsername(), email);

        return ResponseEntity.ok(userInfo);
    }
}
