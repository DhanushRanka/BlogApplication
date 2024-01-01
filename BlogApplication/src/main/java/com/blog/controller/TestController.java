package com.blog.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TestController {

	
	@PostMapping("/test")
	public void request(@RequestParam("f1")String value1,@RequestParam("file")MultipartFile file)
	{
		System.out.println(value1);
		System.out.println(file.getOriginalFilename());

	}
	
	@GetMapping("/t1")
	public void getrequest(@RequestParam("f1")String value1,@RequestParam("file")MultipartFile file)
	{
		System.out.println(value1);
		System.out.println(file.getOriginalFilename());

	}
	
	 @PostMapping("/image")
	    public void uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
	        String name = file.getOriginalFilename();
	        String randomId = "abcd";
//	        String fileName = randomId.concat(name.substring(name.lastIndexOf(".")));

	        // Specify the directory to store images
	        String directoryPath = "images";
	        File directory = new File(directoryPath);

	        // Create the directory if it doesn't exist
	        if (!directory.exists()) {
	            directory.mkdirs();
	        }

	        // Construct the complete file path
	        String filePath = directoryPath + File.separator + name;

	        // Copy the file to the specified location
	        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
	    }
}
