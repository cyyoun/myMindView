package cyng.mmview.controller;

import cyng.mmview.domain.Gender;
import cyng.mmview.domain.Members;
import cyng.mmview.service.MembersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/mem")
@AllArgsConstructor
@Controller
@CrossOrigin(origins = "http://localhost:8080", exposedHeaders = "Authorization")
public class MemberController {
    private final MembersService membersService;

    @GetMapping("/add")
    public String joinForm(Model model) {
        model.addAttribute("genderTypes", Gender.values());
        model.addAttribute("members", new Members());
        return "members/addMem";
    }

    @PostMapping("/add")
    public String join(@Validated @ModelAttribute Members members, BindingResult bindingResult, Model model) {
        model.addAttribute("genderTypes", Gender.values());

        if (bindingResult.hasErrors())
            return "members/addMem";

        membersService.join(members);
        return "members/login";
    }
    @ResponseBody
    @GetMapping("idChk")
    public boolean overlapId(String accntId) {
        Members members = membersService.getMembers(accntId);
        if (members == null) { //중복 아이디 없음
            return false;
        }
        return true; //중복되는 아이디 존재
    }
}
