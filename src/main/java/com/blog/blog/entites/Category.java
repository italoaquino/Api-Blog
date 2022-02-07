package com.blog.blog.entites;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String guid;

    @NotBlank
    @Column(nullable = false, length = 30)
    private String name;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd' : 'HH:mm:ss", timezone="GMT")
    private LocalDateTime date;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Post> posts;

    public Category(){
    }

    public Category(String name, LocalDateTime date){
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDate() {
        return LocalDateTime.now();
    }
}
