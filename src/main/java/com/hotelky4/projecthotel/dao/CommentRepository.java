package com.hotelky4.projecthotel.dao;

import com.hotelky4.projecthotel.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findCommentsByCreatedAtBetween(Date startDate, Date endDate);
}