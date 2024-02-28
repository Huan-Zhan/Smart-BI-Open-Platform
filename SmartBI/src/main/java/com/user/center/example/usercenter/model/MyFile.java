package com.user.center.example.usercenter.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MyFile {
    MultipartFile file;
}
