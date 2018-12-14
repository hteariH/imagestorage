package com.mamoru.imagestorage.controller;

import com.mamoru.imagestorage.service.StorageService;
import com.mamoru.imagestorage.service.StorageServiceMongo;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");
        System.out.println(file.getOriginalFilename());
        return "redirect:/";
    }

    @GetMapping("/{filename}")
    public void getImage(@PathVariable String filename, HttpServletResponse response) throws IOException {
        GridFsResource load = storageService.load(filename);

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(new FileInputStream(load.getFile()), response.getOutputStream());
    }
}
