package com.example.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentDto {
    private Long id;
    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Name should have at least 3 characters")
    private String name;
    @Email(message = "Invalid email address")
    private String emailId;
    @Size(min = 10, max = 10, message = "Mobile number should be 10 digits")
    private String mobile;


}
