package com.example.blogsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "title can not be null")
    @Size(message = "max length for title is 15",max = 15)
    private String title;
    @Size(max = 100,message = "max length for body is 100")
    @NotEmpty(message = "body can not be null")
    private String body;

    @ManyToOne
    @JsonIgnore
    private User user;
}
