package com.niit.dao;

import java.util.List;

import com.niit.model.BlogComment;
import com.niit.model.BlogPost;

public interface BlogDao {
void saveBlogPost(BlogPost blogPost);
public List<BlogPost> getBlogPosts(int approved);
public BlogPost getBlogPostById(int id);
void addBlogComment(BlogComment blogComment);
List<BlogComment> getBlogComments(int blogPostId);
void update(BlogPost blogPost);
}