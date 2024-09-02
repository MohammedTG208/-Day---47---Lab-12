package com.example.blogsystem.Service;

import com.example.blogsystem.API.APIException;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;


    public void register(User user) {
        //1-set the role as user
        user.setRole("USER");
        //2-encode the password
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        authRepository.save(user);
    }

    public List<User> getAllUserForAdmin(Integer adminId) {
        User user = authRepository.findUserById(adminId);
        if (authRepository.findAll().isEmpty()) {
            throw new APIException("There are no users in the system");
        } else {
            return authRepository.findAll();
        }

    }

    public void updateUser(User user,Integer userId) {
        if (authRepository.findUserById(userId)==null){
            throw new APIException("User not found");
        }else {
            User user1=authRepository.findUserById(userId);
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            user1.setPassword(user.getPassword());
            user1.setUsername(user.getUsername());
            authRepository.save(user1);
        }
    }

    public void deleteUserByAdmin(Integer userId, Integer adminId) {
        User user=authRepository.findUserById(adminId);
        if (user==null){
            throw new APIException("User not found");
        }
        authRepository.deleteById(userId);
    }

    public String login(Integer userid) {
        User user=authRepository.findUserById(userid);
        if(user==null){
            throw new APIException("password or username wrong");
        }else {
            return "Welcome back "+user.getUsername();
        }
    }
}
