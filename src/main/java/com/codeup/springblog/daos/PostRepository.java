package com.codeup.springblog.daos;

import com.codeup.springblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByTitleLike(String s);
    Post deletePostByTitleLike(String t);

}
