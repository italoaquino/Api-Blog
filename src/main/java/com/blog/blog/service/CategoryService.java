package com.blog.blog.service;

import com.blog.blog.dtos.CategoryDTO;
import com.blog.blog.entites.Category;
import com.blog.blog.entites.Post;
import com.blog.blog.exception.ObjectNotFound;
import com.blog.blog.repositories.CategoryRepository;
import com.blog.blog.repositories.PostRepository;
import com.github.javafaker.Cat;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    private CategoryRepository repository;

    public CategoryService(CategoryRepository repository){
        this.repository = repository;
    }

    public List<Category> findAll(){
        return this.repository.findAll();
    }

    public Optional<Category> findByGuid(String guid){
        Optional<Category> category = this.repository.findByGuid(guid);
        return Optional.ofNullable(category.orElseThrow(() -> new ObjectNotFound("Categoria não encontrada!")));

    }

    public Category addCategory(CategoryDTO categoryDTO){
        Category cat = new Category();
        cat.setName(categoryDTO.getName());
        cat.setGuid(UUID.randomUUID().toString());
        cat.setDate(LocalDateTime.now());
        return this.repository.save(cat);
    }

    public Category update(CategoryDTO categoryDTO, String guid){
        Optional<Category> category1 = Optional.ofNullable(this.repository.findByGuid(guid).orElseThrow(() -> new ObjectNotFound("Categoria não encontrada!")));
        category1.get().setName(categoryDTO.getName());
        category1.get().setDate(categoryDTO.getDate());
        category1.get().setDate(LocalDateTime.now());
        return this.repository.save(category1.get());
    }

    public void remove(String guid){
        Optional<Category> category = Optional.ofNullable(this.repository.findByGuid(guid).orElseThrow(() -> new ObjectNotFound("Categoria não encontrada!")));
        this.repository.delete(category.get());
    }


}
