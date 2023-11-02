package com.hotelky4.projecthotel.service;

import com.hotelky4.projecthotel.entity.Comment;

import java.util.Date;
import java.util.List;

public interface CommentService {
    Comment saveComment(Comment comment);
    List<Comment> getAllComments();
    Comment getCommentById(Long id);
    void deleteCommentById(Long id);
    void deleteComment(int commentId);

    Comment findById(int commentId);

    Comment findCommentById(Long id);

    List<Comment> findCommentsByCreatedAtBetween(Date startDate, Date endDate);
    // Các phương thức khác liên quan đến Comment (nếu cần)

}


