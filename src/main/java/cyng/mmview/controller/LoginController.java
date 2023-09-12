package cyng.mmview.controller;

import cyng.mmview.domain.Members;
import cyng.mmview.service.MembersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String Login(@ModelAttribute Members members, BindingResult bindingResult) {
        if (members.getAccntId() != null && members.getAccntPw() != null) {
            Members chkedMembers = membersService.chkMembers(members.getAccntId(), members.getAccntPw());

            if (chkedMembers == null) { //아이디, 비번 계정 불일치로 조회 결과 없음
                bindingResult.reject("noAccnt", "아이디 또는 비밀번호를 잘못 입력했습니다.");
            }
        }

        if (bindingResult.hasErrors()) {
            return "members/login";
        }
        return "redirect:/post/posts";
    }
}
