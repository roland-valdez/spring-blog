package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/show/{id}")
    public String show(@PathVariable long id,  Model model){
        model.addAttribute("blog", postDao.getById(id));
        return "/posts/show";
    }

    @PostMapping("/posts/{id}/delete")
    public String showDelete(@PathVariable long id) {
        postDao.deleteById(id);
        return "/posts/index";
    }
//    @PostMapping("/delete")
//    public String wipe(@RequestParam(name = "title") String title, Model model) {
//        model.addAttribute("blog", postDao.deletePostByTitleLike(title));
//        model.addAttribute("list", postDao.findAll());
//        return "/posts/index";
//    }
//
    @GetMapping("posts/add")
        public String addPost(){
            return "posts/add";
    }
    @PostMapping("posts/add")
    public String savePost(@RequestParam(value = "title") String title, @RequestParam(value = "description") String description){
        Post post = new Post(title, description);
        Post dbPost = postDao.save(post);
        return "redirect:/posts/" + dbPost.getId();
    }

    @GetMapping("posts/{id}/edit")
    public String edit(@PathVariable long id, Model model){
        Post post = postDao.getById(id);
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("posts/{id}/edit")
    @ResponseBody
    public String dbEdit(@PathVariable long id, @RequestParam(name = "title") String title, @RequestParam(name = "description") String description){
        Post post = postDao.getById(id);
        post.setTitle(title);
        post.setBody(description);
        postDao.save(post);
        return "posts/index";
    }

}
