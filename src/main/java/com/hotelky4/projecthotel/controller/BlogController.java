package com.hotelky4.projecthotel.controller;

import com.hotelky4.projecthotel.entity.Blog;
import com.hotelky4.projecthotel.service.BlogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;


    //show blog list - user
    @GetMapping("/list")
    public String listBlog(Model theModel){
        List<Blog> theBlogs = blogService.findAll();
        theModel.addAttribute("blogs",theBlogs);
        return "blogs.html";
    }
    //search by title and type

    //get blog by Id
    @GetMapping("/blog")
    public String getBlogById(@RequestParam(value = "blogId") int theId, Model model) {
        Blog theBlog = blogService.findById(theId);
        model.addAttribute("blog", theBlog);
        return "blogs-single.html";
    }



    //show blog list - admin
    @GetMapping("/admin/list")
    public String getListBlog(Model theModel) {
        List<Blog> theBlogs = blogService.findAll();
        theModel.addAttribute("blogs",theBlogs);
        return "/admin/blog/list-blog.html";
    }

    //add blog
    @GetMapping("/admin/showFormForAdd")
    public String showFormForAdd(Model model){
        Blog newBlog = new Blog();
        model.addAttribute("blog",newBlog);
        return "/admin/blog/blog-form.html";
    }

    @PostMapping("/admin/save")
    public String saveBlog(@ModelAttribute("blog")Blog theBlog,@RequestParam("image") MultipartFile imageFile){
        Blog check = new Blog();
        check = theBlog;
        if (!imageFile.isEmpty()) {
            try {
                addImg(theBlog,imageFile);
                theBlog.setBlogCreatedDate(LocalDate.now());
                blogService.save(theBlog);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        theBlog.setBlogCreatedDate(LocalDate.now());
        blogService.save(theBlog);
        return "redirect:/blogs/admin/list";
    }
    //update blog
    @GetMapping("/admin/showFormForUpdate")
    public String showFormForAdd(@RequestParam("blogId") int theId, Model theModel){
        //get room from service
        Blog theBlog = blogService.findById(theId);
        theModel.addAttribute("blog",theBlog);
        return "/admin/blog/blog-form-update.html";
    }
    @PostMapping("/admin/update")
    public String updateBlog(@ModelAttribute("blog")Blog theBlog,@RequestParam("image") MultipartFile imageFile){
        if(!imageFile.isEmpty()) {
            try {
                addImg(theBlog,imageFile);
                theBlog.setBlogCreatedDate(LocalDate.now());
                blogService.save(theBlog);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        theBlog.setBlogCreatedDate(LocalDate.now());
        blogService.save(theBlog);
        return "redirect:/blogs/admin/list";
    }
    //delete blog
    @GetMapping("/admin/delete")
    public String delete(@RequestParam("blogId")int theId){
        blogService.deleteById(theId);
        return "redirect:/blogs/admin/list";
    }
    public Blog addImg(Blog theBlog,MultipartFile imageFile) throws IOException {
        String originalFileName = imageFile.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String fileName = theBlog.getBlogTitlel().replaceAll("\\s+","_")+ UUID.randomUUID()+fileExtension;
        String staticDirectory = "src/main/resources/static";
        String imageDirectory = "/images/blogImg/";
        Path imageFilePath = Paths.get(staticDirectory + imageDirectory + fileName);
        Files.copy(imageFile.getInputStream(),imageFilePath);
        theBlog.setBlogImg(fileName);
        return theBlog;
    }
    
}