package com.blog.blog.controllers;

import com.blog.blog.dtos.PostDTO;
import com.blog.blog.entites.Post;
import com.blog.blog.exception.ObjectNotFound;
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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@CrossOrigin
@RequestMapping(value = "/v1/posts/")
public class PostController {

    private PostService service;

    public PostController(PostService service){
        this.service = service;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<PostDTO>> findALl(){
        List<PostDTO> posts = StreamSupport.stream(this.service.findALl().spliterator(),false)
                .map(post -> PostDTO.toDto(post))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(posts);
    }

    @RequestMapping(value = "{guid}", method = RequestMethod.GET)
    public ResponseEntity<PostDTO> findByGuid(@PathVariable String guid){
        Optional<Post> p1 = this.service.findByGuid(guid);
        PostDTO post = PostDTO.toDto(p1.get());
        return ResponseEntity.ok().body(post);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Void> add(@Valid @RequestBody PostDTO postDTO){
      Post post = this.service.add(
              postDTO.getTittle(), postDTO.getSubtittle(), postDTO.getAuthor(), postDTO.getText(), postDTO.getCategory_id()
      );
      return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "{guid}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody PostDTO postDTO, @PathVariable String guid, BindingResult result){
        if (result.hasErrors()) {
            throw new ValidationException("error: "+result);
        }
        this.service.update(postDTO.getTittle(), postDTO.getSubtittle(), postDTO.getAuthor(), postDTO.getText(), postDTO.getCategory_id(), guid);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "{guid}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> remove(@PathVariable String guid){
        if(Strings.isEmpty(guid)){
            throw new IllegalArgumentException("guid nao pode ser nulo");
        }
        this.service.remove(guid);
        return ResponseEntity.noContent().build();
    }

}
