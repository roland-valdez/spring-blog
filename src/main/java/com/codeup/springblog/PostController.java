package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    @ResponseBody
    public String postIndex() {
        return "This page will show all the posts";
    }
    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String individualPost(@PathVariable int id) {
        return "This page will a post that has the following " + id + " based on the url";
    }
    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    @ResponseBody
    public String createPostsGet() {
        return "This page will show forms to create a post";
    }
    @PostMapping(path = "/posts/create")
    @ResponseBody
    public String createPostsPost() {
        return "This page will post what was submitted in the create post page";
    }
}
