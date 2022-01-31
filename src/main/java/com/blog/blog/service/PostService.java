package com.blog.blog.service;

import com.blog.blog.entites.Category;
import com.blog.blog.entites.Post;
import com.blog.blog.exception.ObjectNotFound;
import com.blog.blog.repositories.CategoryRepository;
import com.blog.blog.repositories.PostRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {

    private PostRepository repository;
    private CategoryRepository Categoryrepository;

    public PostService(PostRepository repository, CategoryRepository Categoryrepository){
        this.repository = repository;
        this.Categoryrepository = Categoryrepository;
    }

    public List<Post> findALl(){
        return this.repository.findAll();
    }

    public Optional<Post> findByGuid(String guid){
        Optional<Post> post = this.repository.findByGuid(guid);
        return Optional.ofNullable(post.orElseThrow(() -> new ObjectNotFound("Objeto não encontrado!")));
    }

    public Post add(String tittle, String subttile, String author, String text, Long id){
        Post post = new Post();
        post.setTittle(tittle);
        post.setSubtittle(subttile);
        post.setAuthor(author);
        post.setText(text);
        post.setDate(LocalDateTime.now());
        post.setGuid(UUID.randomUUID().toString());
        Category category = this.Categoryrepository.findById(id).orElseThrow(() -> new ObjectNotFound("CATEGORIA NAO ENCONTRADA!"));
        post.setCategory(category);
        return this.repository.save(post);
    }

    public Post update(String tittle, String subttile, String author, String text, Long categoryId, String guid){
        Optional<Post> p1 = this.repository.findByGuid(guid);
        if(!p1.isPresent()){
            throw new ObjectNotFound("Objeto não encontrado!");
        }
        p1.get().setTittle(tittle);
        p1.get().setText(text);
        p1.get().setDate(LocalDateTime.now());
        p1.get().setAuthor(author);
        p1.get().setSubtittle(subttile);
        p1.get().setDate(LocalDateTime.now());
        Category category = this.Categoryrepository.findById(categoryId).orElseThrow(()-> new ObjectNotFound("Objeto não encontrado!"));
        p1.get().setCategory(category);
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
