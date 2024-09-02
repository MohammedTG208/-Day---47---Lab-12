package com.example.blogsystem.Service;

import com.example.blogsystem.API.APIException;
import com.example.blogsystem.Model.Blog;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Repository.AuthRepository;
import com.example.blogsystem.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;

    public List<Blog> getAllBlog() {
        if (blogRepository.findAll().isEmpty()) {
            throw new APIException("No blogs found in system");
        } else {
            return blogRepository.findAll();
        }

    }

    public void addNewBlog(Blog blog,Integer userId) {
        User user=authRepository.findUserById(userId);
        if (user==null){
            throw new APIException("User not found");
        }else {
            blog.setUser(user);
            blogRepository.save(blog);
        }
    }

    public void updateBlog(Blog blog,Integer userId,Integer blogId) {
        User user=authRepository.findUserById(userId);
        if (user==null){
            throw new APIException("User not found");
        }else {
            Blog blog1=blogRepository.findBlogById(blogId);
            if (blog1==null){
                throw new APIException("Blog not found");
            }else if (blog1.getUser().getId()==userId) {
                blog1.setTitle(blog.getTitle());
                blog1.setBody(blog.getBody());
                blog1.setUser(user);
                blogRepository.save(blog1);

            }else {
                throw new APIException("you can not change this blog");
            }
        }
    }

    public void deleteBlog(Integer blogId,Integer userId) {
        User user=authRepository.findUserById(userId);
        if (user==null){
            throw new APIException("User not found");
        }else {
            Blog blog=blogRepository.findBlogById(blogId);
            if (blog.getUser().getId()==userId) {
                blogRepository.delete(blog);
            }else {
                throw new APIException("you can not delete this blog");
            }
        }
    }

    public void deleteBlogByAdmin(Integer adminId,Long blogId) {
        User user=authRepository.findUserById(adminId);
        if (user==null){
            throw new APIException("User not found");
        }else {
            blogRepository.deleteById(blogId);
        }
    }

    public Blog getBlogByTitle(String title) {
        if (blogRepository.findBlogByTitle(title).getTitle().isEmpty()){
            throw new APIException("No blogs found in system by this title");
        }else {
            return blogRepository.findBlogByTitle(title);
        }
    }


}
