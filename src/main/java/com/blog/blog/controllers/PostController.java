package com.blog.blog.controllers;

import com.blog.blog.entites.Post;
import com.blog.blog.service.PostService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "/v1/posts/")
public class PostController {

    private PostService service;

    public PostController(PostService service){
        this.service = service;
    }
    @CrossOrigin(origins = "http:/localhost:4200")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findALl(){
        return ResponseEntity.ok().body(this.service.findALl());
    }

    @CrossOrigin(origins = "http:/localhost:4200")
    @RequestMapping(value = "{guid}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Post>> findByGuid(@PathVariable String guid){
        Optional<Post> p1 = this.service.findByGuid(guid);
        return ResponseEntity.ok().body(p1);
    }

    @CrossOrigin(origins = "http:/localhost:4200")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Void> add(@Valid @RequestBody Post post, BindingResult result){
        if (result.hasErrors()) {
            throw new ValidationException("error: "+result);
        }
        this.service.add(post);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @CrossOrigin(origins = "http:/localhost:4200")
    @RequestMapping(value = "{guid}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody Post post, @PathVariable String guid, BindingResult result){
        if (result.hasErrors()) {
            throw new ValidationException("error: "+result);
        }
        this.service.update(post, guid);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin(origins = "http:/localhost:4200")
    @RequestMapping(value = "{guid}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> remove(@PathVariable String guid){
        if(Strings.isEmpty(guid)){
            throw new IllegalArgumentException("guid nao pode ser nulo");
        }
        this.service.remove(guid);
        return ResponseEntity.noContent().build();
    }

}
