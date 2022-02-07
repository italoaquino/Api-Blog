package com.blog.blog.service;

import com.blog.blog.dtos.PostDTO;
import com.blog.blog.entites.Category;
import com.blog.blog.entites.Post;
import com.blog.blog.exception.ObjectNotFound;
import com.blog.blog.repositories.CategoryRepository;
import com.blog.blog.repositories.PostRepository;
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

    public Post add(PostDTO postDTO){
        Category category = this.Categoryrepository.findById(postDTO.getCategory_id()).orElseThrow(() -> new ObjectNotFound("CATEGORIA NAO ENCONTRADA!"));
        Post p1 = new Post();
        p1.setText(postDTO.getText());
        p1.setSubtittle(postDTO.getSubtittle());
        p1.setTittle(postDTO.getTittle());
        p1.setAuthor(postDTO.getAuthor());
        p1.setGuid(UUID.randomUUID().toString());
        p1.setDate(LocalDateTime.now());
        p1.setCategory(category);
        return this.repository.save(p1);
    }

    public Post update(PostDTO postDTO, String guid){
        Category category = this.Categoryrepository.findById(postDTO.getCategory_id()).orElseThrow(() -> new ObjectNotFound("CATEGORIA NAO ENCONTRADA!"));
        Post p1 = this.repository.findByGuid(guid).orElseThrow(() -> new ObjectNotFound("Post não encontrado!"));
        p1.setAuthor(postDTO.getAuthor());
        p1.setTittle(postDTO.getTittle());
        p1.setSubtittle(postDTO.getSubtittle());
        p1.setDate(LocalDateTime.now());
        p1.setText(postDTO.getText());
        p1.setCategory(category);
        return p1;

    }

    public void remove(String guid){
        Optional<Post> p1 = this.repository.findByGuid(guid);
        if(!p1.isPresent()){
            throw new ObjectNotFound("Objeto não encontrado!");
        }
        this.repository.delete(p1.get());
    }




}
