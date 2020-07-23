package com.ssafy.devlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.devlog.dto.Blog;
import com.ssafy.devlog.mapper.BlogMapper;

@Service
public class BlogServiceImpl implements BlogService {
	
	@Autowired
	private BlogMapper blogMapper;
	
	@Override
	public List<Blog> selectAllBlog() {
		return blogMapper.selectAllBlog();
	}

	@Override
	public Blog selectBlogByUser(int seq_user) {
		return blogMapper.selectBlogByUser(seq_user);
	}

	@Override
	public int insertBlog(Blog blog) {
		return blogMapper.insertBlog(blog);
	}

	@Override
	public int updateBlog(Blog blog) {
		return blogMapper.updateBlog(blog);
	}

	@Override
	public int deleteBlog(int seq_user) {
		return blogMapper.deleteBlog(seq_user);
	}

}