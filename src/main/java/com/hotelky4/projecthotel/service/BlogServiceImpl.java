package com.hotelky4.projecthotel.service;

import com.hotelky4.projecthotel.dao.BlogRepository;

import com.hotelky4.projecthotel.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import  java.util.List;
import  java.util.Optional;
@Service
public class BlogServiceImpl implements BlogService{
    private BlogRepository blogRepository ;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository){

        this.blogRepository = blogRepository;

    }

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Blog findById(int theId) {
        Optional<Blog> result = blogRepository.findById(theId);
        Blog theBlog = null;
        if (result.isPresent()) {
            theBlog = result.get();

        } else {
            throw new RuntimeException("Did not find the Blog id - " + theId);
        }
        return theBlog;
    }

    @Override
    public void save(Blog theBlog) {
        blogRepository.save(theBlog);
    }

    @Override
    public void deleteById(int theId) {
        blogRepository.deleteById(theId);
    }

}
