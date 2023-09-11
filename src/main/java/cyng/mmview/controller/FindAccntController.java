package cyng.mmview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.GeneratedValue;

@Controller
@RequestMapping("/mem/find")
public class FindAccntController {
    @GetMapping
    public String FindAccntForm() {
        return "members/findAccnt/find";
    }
}
