package com.blog.blog.controllers;

import com.blog.blog.dtos.CategoryDTO;
import com.blog.blog.entites.Category;
import com.blog.blog.entites.Post;
import com.blog.blog.service.CategoryService;
import com.blog.blog.service.PostService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = "/v1/categories/")
public class CategoryController {

    private CategoryService service;

    public CategoryController(CategoryService service){
        this.service = service;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<CategoryDTO>> findAll(){
       List<CategoryDTO> categories = StreamSupport.stream(this.service.findAll().spliterator(), false).map(category -> CategoryDTO.toDTO(category)).collect(Collectors.toList());
       return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @RequestMapping(value = "{guid}", method = RequestMethod.GET)
    public ResponseEntity<CategoryDTO> findByGuid(@PathVariable String guid){
        Optional<Category> category = this.service.findByGuid(guid);
        CategoryDTO dto = CategoryDTO.toDTO(category.get());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Void> addCategory(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult result) throws ValidationException {
        if (result.hasErrors()) {
            throw new javax.validation.ValidationException("error: "+result);
        }
        Category category1 = this.service.addCategory(categoryDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "{guid}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody CategoryDTO categoryDTO, @PathVariable String guid){
        Category category1 = this.service.update(categoryDTO, guid);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "{guid}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String guid){
        this.service.remove(guid);
        return ResponseEntity.noContent().build();
    }


}
