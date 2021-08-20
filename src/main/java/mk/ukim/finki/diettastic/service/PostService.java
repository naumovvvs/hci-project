package mk.ukim.finki.diettastic.service;

import mk.ukim.finki.diettastic.model.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    Post publishPost(String title, String type, String recipe, String location);
}
