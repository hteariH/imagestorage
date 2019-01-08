package com.mamoru.imagestorage.controller;

import com.mamoru.imagestorage.dto.File;
import com.mamoru.imagestorage.service.StorageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller("HomeController")
public class HomeController {

    private StorageService storageService;

    public HomeController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/")
    public String handleUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        File store = storageService.store(file);
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");
        System.out.println(file.getOriginalFilename());

        return "redirect:/"+store.getName();
    }

    @GetMapping("/{filename}")
    public void getImage(@PathVariable String filename, HttpServletResponse response) throws IOException {
        File load = storageService.load(filename);
        System.out.println("filename="+load.getName());
        response.setContentType(load.getContentType());
        ByteArrayInputStream bytes = new ByteArrayInputStream(load.getFile());
        IOUtils.copy(bytes, response.getOutputStream());
    }
}
