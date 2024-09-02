package com.example.blogsystem.Repository;

import com.example.blogsystem.Model.Blog;
import com.example.blogsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    Blog findAllByUser(User user);
    Blog findBlogByTitle(String title);
    Blog findBlogById(Integer id);


}
