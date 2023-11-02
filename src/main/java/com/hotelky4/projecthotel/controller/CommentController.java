package com.hotelky4.projecthotel.controller;

import com.hotelky4.projecthotel.entity.Blog;
import com.hotelky4.projecthotel.entity.Comment;
import com.hotelky4.projecthotel.service.BlogService;
import com.hotelky4.projecthotel.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final BlogService blogService;

    @Autowired
    public CommentController(CommentService commentService, BlogService blogService) {
        this.commentService = commentService;
        this.blogService = blogService;
    }

    @PostMapping("/add")
    public String addComment(@RequestParam("blogId") int blogId,
                             @RequestParam("author") String author,
                             @RequestParam("email") String email,
                             @RequestParam("website") String website,
                             @RequestParam("content") String content) {
        // Tạo đối tượng Comment từ dữ liệu được gửi từ form
        Blog blog = blogService.findById(blogId);
        Comment comment = new Comment(blog, author, email, website, content);
        comment.setCreatedAt(LocalDateTime.now());

        // Đẩy comment mới lên đầu danh sách trong Blog
        blog.addComment(comment); // Sử dụng phương thức addComment trong lớp Blog

        // Lưu comment vào cơ sở dữ liệu thông qua CommentService (nếu cần)
        commentService.saveComment(comment);

        // Chuyển hướng người dùng đến trang chi tiết của blog sau khi gửi comment
        return "redirect:/blogs/blog/?blogId=" + blogId;
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Comment comment = commentService.findCommentById(id);
        model.addAttribute("comment", comment);
        return "edit-comment";
    }

    @PostMapping("/edit/{id}")
    public String editComment(@PathVariable Long id,
                              @ModelAttribute Comment updatedComment,
                              @RequestParam("blogId") Long blogId) {
        Comment existingComment = commentService.findCommentById(id);
        existingComment.setAuthor(updatedComment.getAuthor());
        existingComment.setEmail(updatedComment.getEmail());
        existingComment.setWebsite(updatedComment.getWebsite());
        existingComment.setContent(updatedComment.getContent());

        // Update the comment in the database
        commentService.saveComment(existingComment);

        // Redirect user to the blog details page after editing the comment
        return "redirect:/blogs/blog/?blogId=" + blogId;
    }

    @PostMapping("/delete/{id}")
    public String deleteComment(@PathVariable Long id, @RequestParam("blogId") Long blogId) {
        commentService.deleteCommentById(id);
        // Chuyển hướng người dùng về trang chi tiết blog sau khi xóa comment
        return "redirect:/blogs/blog/?blogId=" + blogId;
    }

}
