package cyng.mmview.controller;

import cyng.mmview.domain.Members;
import cyng.mmview.service.MembersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class LoginController {
    private final MembersService membersService;

    @GetMapping("/login")
    public String LoginForm(Model model) {
        model.addAttribute("members", new Members());
        return "members/login";
    }

    @ResponseBody
    @PostMapping("/login")
    public String Login(@ModelAttribute Members members, BindingResult bindingResult, HttpServletResponse response, Model model, HttpServletRequest request) {

        System.out.println("----------------------------LoginController.Login----------------------------");
        System.out.println("response = " + response.getHeader("Authorization"));

        if (members.getAccntId() != null && members.getAccntPw() != null) {
            Members chkedMembers = membersService.chkMembers(members);

            if (chkedMembers == null) { //아이디, 비번 계정 불일치로 조회 결과 없음
                bindingResult.reject("noAccnt", "아이디 또는 비밀번호를 잘못 입력했습니다.");
            }
        }

        if (bindingResult.hasErrors()) {
            return "members/login";
        }
        return "어머나 성공이네";
//        return "redirect:/post/posts";
    }
}
