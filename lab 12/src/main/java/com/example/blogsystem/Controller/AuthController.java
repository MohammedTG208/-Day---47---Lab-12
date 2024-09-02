package com.example.blogsystem.Controller;

import com.example.blogsystem.Model.User;
import com.example.blogsystem.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/get-all")
    public ResponseEntity getAllUserForAdmin(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(authService.getAllUserForAdmin(user.getId()));
    }

    @PostMapping("/register")
    public ResponseEntity registerNewUser(@Valid @RequestBody User user) {
        authService.register(user);
        return ResponseEntity.status(200).body("user added successfully as " + user.getUsername());
    }

    @PutMapping("/update/user/{userId}")
    public ResponseEntity updateUser(@PathVariable Integer userId, @Valid @RequestBody User user) {
        authService.updateUser(user,userId);
        return ResponseEntity.status(200).body("user updated successfully as " + user.getUsername());
    }

    @DeleteMapping("/delete/user/{userId}")
    public ResponseEntity deleteUser(@PathVariable Integer userId,@AuthenticationPrincipal User user) {
        authService.deleteUserByAdmin(userId,user.getId());
        return ResponseEntity.status(200).body("user deleted successfully");
    }

    @GetMapping("/login")
    public ResponseEntity login(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(authService.login(user.getId()));
    }
}
