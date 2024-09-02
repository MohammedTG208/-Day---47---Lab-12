package com.example.blogsystem.Controller;

import com.example.blogsystem.Model.Blog;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;
    @GetMapping("/get-all-blog")
    public ResponseEntity<List<Blog>> getAllBlog() {
        return ResponseEntity.status(200).body(blogService.getAllBlog());
    }

    @PostMapping("/add-new-blog")
    public ResponseEntity<String> addNewBlog(@AuthenticationPrincipal User user, @Valid @RequestBody Blog blog) {
        blogService.addNewBlog(blog, user.getId());
        return ResponseEntity.status(201).body("blog add successful for: "+user.getUsername());
    }

    @DeleteMapping("/delete-blog/{blogId}")
    public ResponseEntity<String> deleteBlog(@AuthenticationPrincipal User user, @PathVariable Integer blogId) {
        blogService.deleteBlog(user.getId(), blogId);
        return ResponseEntity.status(200).body("blog delete successful for: "+user.getUsername());
    }

    @PutMapping("/update-blog/{blogId}")
    public ResponseEntity<String> updateBlog(@AuthenticationPrincipal User user, @Valid @RequestBody Blog blog,@PathVariable Integer blogId) {
        blogService.updateBlog(blog, user.getId(), blogId);
        return ResponseEntity.status(200).body("blog update successful for: "+user.getUsername());
    }

    @DeleteMapping("/delete-blog-by-admin/{blogId}")
    public ResponseEntity<String> deleteBlogByAdmin(@AuthenticationPrincipal User user, @PathVariable Long blogId) {
        blogService.deleteBlogByAdmin(user.getId(), blogId);
        return ResponseEntity.status(200).body("blog delete successful");
    }

    @GetMapping("/search/{title}")
    public ResponseEntity search(@PathVariable String title) {
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(title));
    }



}
