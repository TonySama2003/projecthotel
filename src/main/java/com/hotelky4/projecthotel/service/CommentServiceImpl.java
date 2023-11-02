package com.hotelky4.projecthotel.service;

import com.hotelky4.projecthotel.dao.CommentRepository;
import com.hotelky4.projecthotel.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment saveComment(Comment comment) {
        if (comment.getId() != null) {
            Optional<Comment> existingComment = commentRepository.findById(comment.getId());
            if (existingComment.isPresent()) {
                Comment updatedComment = existingComment.get();
                updatedComment.setAuthor(comment.getAuthor());
                updatedComment.setEmail(comment.getEmail());
                updatedComment.setWebsite(comment.getWebsite());
                updatedComment.setContent(comment.getContent());
                // Cập nhật các trường khác nếu cần
                return commentRepository.save(updatedComment);
            }
        }
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public void deleteComment(int commentId) {
        commentRepository.deleteById((long) commentId);
    }

    @Override
    public Comment findById(int commentId) {
        return commentRepository.findById((long) commentId).orElse(null);
    }

    @Override
    public Comment findCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Comment> findCommentsByCreatedAtBetween(Date startDate, Date endDate) {
        // Sử dụng CommentRepository để tìm kiếm các comment trong khoảng thời gian từ startDate đến endDate
        List<Comment> comments = commentRepository.findCommentsByCreatedAtBetween(startDate, endDate);
        return comments;
    }
}
