package com.hotelky4.projecthotel.service;

import com.hotelky4.projecthotel.entity.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> findAll();
    Blog findById(int theId);
    void save(Blog theBlog);
    void deleteById(int theId);
}
