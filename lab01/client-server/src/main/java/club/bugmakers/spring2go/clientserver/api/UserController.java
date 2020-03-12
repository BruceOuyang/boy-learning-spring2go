package club.bugmakers.spring2go.clientserver.api;

import club.bugmakers.spring2go.clientserver.model.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

    @RequestMapping("/api/userList")
    public ResponseEntity<List<UserInfo>> getAllUsers() {
        return ResponseEntity.ok(getUsers());
    }

    private List<UserInfo> getUsers(){
        return Arrays.asList(new UserInfo("Bruce", "bruce.ouyang@foxmail.com"), new UserInfo("Angelia", "angelia.xia@foxmail.com"));
    }
}
