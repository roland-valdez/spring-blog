package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {
 private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }
    @GetMapping("/index")
    public String postIndex(Model model){
        model.addAttribute("list", postDao.findAll());
        return "/posts/index";
    }
    @GetMapping("/show")
    public String show(Model model){
        model.addAttribute("blog", postDao.findByTitleLike("%good%"));
        return "/posts/show";
    }



}
