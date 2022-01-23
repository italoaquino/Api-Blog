package com.blog.blog.service;

import com.blog.blog.entites.Post;
import com.blog.blog.exception.ObjectNotFound;
import com.blog.blog.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {

    private PostRepository repository;

    public PostService(PostRepository repository){
        this.repository = repository;
    }

    public List<Post> findALl(){
        return this.repository.findAll();
    }

    public Optional<Post> findByGuid(String guid){
        Optional<Post> post = this.repository.findByGuid(guid);
        if(!post.isPresent()){
            throw new ObjectNotFound("Objeto não encontrado!");
        }
        return post;
    }

    public Post add(Post post){
        post.setDate(LocalDateTime.now());
        post.setGuid(UUID.randomUUID().toString());
        return this.repository.save(post);
    }

    public Post update(Post post, String guid){
        Optional<Post> p1 = this.repository.findByGuid(guid);
        if(!p1.isPresent()){
            throw new ObjectNotFound("Objeto não encontrado!");
        }
        p1.get().setTittle(post.getTittle());
        p1.get().setText(post.getText());
        p1.get().setDate(post.getDate());
        p1.get().setDate(LocalDateTime.now());
        return this.repository.save(p1.get());
    }

    public void remove(String guid){
        Optional<Post> p1 = this.repository.findByGuid(guid);
        if(!p1.isPresent()){
            throw new ObjectNotFound("Objeto não encontrado!");
        }
        this.repository.delete(p1.get());
    }

}
