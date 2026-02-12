package com.iss.springfileuploadmvc.controllers;
import org.springframework.core.io.Resource; // MUST BE THIS ONE
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/")
public class FileUploadController {


    @GetMapping("/upload")
    public String uploadFile(Model model) {
        model.addAttribute("img","knn");
        return "index";
    }

    @PostMapping("/uploadfile")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) throws IOException {


        //it provides the address wherer we need to save the images
        String rootPath = System.getProperty("user.dir") + "/uploads/images";
        // where we are creating the path object using the string
        Path uploadPath = Paths.get(rootPath);
        // we will get the entire file name
        String fileName = file.getOriginalFilename();
        //which is used to create the file with the file name and also the type fo it
        Path destPath = uploadPath.resolve(fileName);
        file.transferTo(destPath.toFile());
        model.addAttribute("message","file uploaded successfully");
        model.addAttribute("img",fileName);
        return "index";
    }

    @GetMapping("/files/{filename}")
    public ResponseEntity<Resource> displyFiles(@PathVariable String filename) throws MalformedURLException {
        Path filePath = Paths.get(System.getProperty("user.dir") + "/uploads/images").resolve(filename);
        Resource resource = new UrlResource(filePath.toUri());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
    /**.
     * Resource interface
     * It’s an "Abstraction" Layer
     If you used standard Java File objects, your code might work on Windows but break on Linux due to path differences. Resource (specifically UrlResource) handles the conversion to a URI (Uniform Resource Identifier), which is the standard way web servers locate files.

     2. Efficient Memory Management
     When you return a Resource in a ResponseEntity, Spring doesn't load the entire image into your RAM all at once.

     If you have a 10MB image and 100 users view it, loading it as a byte array (byte[]) would eat up 1GB of RAM.

     Using Resource, Spring streams the file. It reads a small chunk from the disk and sends it to the browser, then reads the next chunk. This keeps your application's memory usage very low.

     3. Built-in Compatibility with ResponseEntity
     Spring’s web module is designed to recognize the Resource type. When you pass a Resource to the body of a response, Spring automatically:

     Handles the Input Stream for you.

     Can calculate the Content-Length (file size) so the browser knows how long the download will take.

     Closes the connection properly once the file is sent.

     4. Location Flexibility
     Today your images are in /uploads/images on your hard drive. If tomorrow you decide to move them to a different location (like a "Cloud Storage" bucket or a "Classpath" folder), you only change the type of Resource (e.g., from UrlResource to ClassPathResource), but the rest of your controller code stays exactly the same.**/
}
