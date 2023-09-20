package cyng.mmview.controller;

import cyng.mmview.domain.Posts;
import cyng.mmview.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/post")
@AllArgsConstructor
public class PostsController {
    private final PostsService postsService;

    @GetMapping("/posts")
    public String postsForm(Model model, @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        System.out.println("----------------------------PostsController.postsForm----------------------------");
        Page<Posts> posts = postsService.allPosts(pageable);
        int page = pageable.getPageNumber();
        int startPage = page / 5 * 5 + 1;
        int lastPage = posts.getTotalPages();
        model.addAttribute("nowPage", page + 1);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", Math.min(startPage + 4, lastPage));
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("posts", posts);
        return "posts/posts";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("posts", new Posts());
        return "posts/addPost";
    }

    @PostMapping("/add")
    public String addPost(@Validated @ModelAttribute Posts posts, BindingResult bindingResult, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            return "posts/addPost";
        }

        postsService.addPost(posts);
        redirect.addAttribute("post", posts.getId());
        return "redirect:/post";
    }

    @GetMapping
    public String postForm(@RequestParam("post") long postsId, Model model) {
        Posts posts = postsService.onePosts(postsId).orElse(null);
        model.addAttribute("posts", posts);
//        (+) 작성자 추가
//        model.addAttribute("accntId", posts.getMembers().getAccntId());
        return "posts/post";
    }

    @DeleteMapping("/del")
    public String delPost(@RequestParam("post") long postsId) {
        Posts posts = postsService.onePosts(postsId).orElse(null);
        postsService.delPosts(posts);
        return "redirect:/post/posts";
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam("post") long postsId, Model model, RedirectAttributes redirect) {
        Posts posts = postsService.onePosts(postsId).orElse(null);
        model.addAttribute("posts", posts);
        redirect.addAttribute("post", postsId);
        return "posts/editPost";
    }

    @PostMapping("/edit")
    public String editPost(@Validated @ModelAttribute Posts posts, BindingResult bindingResult, Model model,
                           @RequestParam("post") long postsId, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            return "posts/editPost";
        }
        postsService.editPosts(postsId, posts);
        redirect.addAttribute("post", postsId);
        return "redirect:/post";
    }
}
