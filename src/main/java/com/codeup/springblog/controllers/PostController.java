package com.codeup.springblog.controllers;

import com.codeup.springblog.daos.PostRepository;
import com.codeup.springblog.daos.UserRepository;
import com.codeup.springblog.models.Image;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    private  PostRepository postDao;
    private UserRepository userDao;
    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }


//    public PostController(EmailService emailService){
//        this.emailService = emailService;
//    };
//
//    public PostController(PostRepository postDao, UserRepository userDao) {
//        this.postDao = postDao;
//        this.userDao = userDao;
//    }

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
    public String savePost(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "imageUrl") String imageUrl,
            @RequestParam(value = "imageDescription") String imageDescription){

        User user  = userDao.getById(1L);// just using the only saved user with id of 1. Later will be linked to session of user
        List<Image> images = new ArrayList<>();

        Post post = new Post(title, description, user, null);
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
//    @ResponseBody
    public String dbEdit(@PathVariable long id, @RequestParam(name = "title") String title, @RequestParam(name = "description") String description){
        Post post = postDao.getById(id);
        post.setTitle(title);
        post.setBody(description);
        postDao.save(post);
        return "redirect:/index";
    }
    @GetMapping("/posts/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String create(@ModelAttribute Post post) {
        User user = userDao.getById(1L);
        post.setOwner(user); // need to add user to be able to save a post
        postDao.save(post);
        emailService.prepareAndSend(post, "You just created a post", post.getBody());
        return "redirect:/posts/index";
    }


}
