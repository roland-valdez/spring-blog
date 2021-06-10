package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
//    @RequestMapping(path = "/posts", method = RequestMethod.GET)
//    @ResponseBody
//    public String postIndex() {
//        return "This page will show all the posts";
//    }
//    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public String individualPost(@PathVariable int id) {
//        return "This page will a post that has the following " + id + " based on the url";
//    }
//    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
//    @ResponseBody
//    public String createPostsGet() {
//        return "This page will show forms to create a post";
//    }
//    @PostMapping(path = "/posts/create")
//    @ResponseBody
//    public String createPostsPost() {
//        return "This page will post what was submitted in the create post page";
//    }

    @GetMapping("/index")
    public String allPosts(Model model) {
        List<Post> list = new ArrayList<>();
        list.add(new Post("what am i doing", "hello"));
        list.add(new Post("hello", "goodbye"));
        model.addAttribute("list", list);
        return "posts/index";
    }
    @GetMapping("/show")
    public String individualPost(Model model) {
        Post post = new Post("this is the title", "you need to work better at creating this stuff");
        model.addAttribute("post", post);
        return "posts/show";
    }
}
