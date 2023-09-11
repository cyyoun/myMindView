package cyng.mmview.controller;

import cyng.mmview.domain.Members;
import cyng.mmview.service.MembersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class LoginController {
    private final MembersService membersService;

    @GetMapping("/login")
    public String LoginForm(Model model) {
        model.addAttribute("members", new Members());
        return "members/login";
    }

    @PostMapping("/login")
    public String Login(@ModelAttribute Members members) {
        //(+) null 값 처리 필요
        membersService.chkAccount(members.getAccntId(), members.getAccntPw());
        return "posts/posts";
    }
}
