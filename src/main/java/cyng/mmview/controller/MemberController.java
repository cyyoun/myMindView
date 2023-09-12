package cyng.mmview.controller;

import cyng.mmview.domain.Gender;
import cyng.mmview.domain.Members;
import cyng.mmview.service.MembersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/mem")
@AllArgsConstructor
@Controller
public class MemberController {
    private final MembersService membersService;

    @GetMapping("/add")
    public String joinForm(Model model) {
        model.addAttribute("genderTypes", Gender.values());
        model.addAttribute("members", new Members());
        return "members/addMem";
    }

    @PostMapping("/add")
    public String join(@ModelAttribute Members members, Model model) {
        model.addAttribute("genderTypes", Gender.values());
        membersService.join(members);
        return "members/login";
    }
}
