package com.blog.blog.entites;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_post")
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String tittle;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String subtittle;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String author;

    @NotBlank
    @Column(nullable = false)
    private String text;

    private String guid;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd' : 'HH:mm:ss", timezone="GMT")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private Category category;

    public Post(){
    }

    public Post(String text, String tittle, String author, String subtittle, LocalDateTime date, Category category)
    {
        this.text = text;
        this.tittle = tittle;
        this.author = author;
        this.subtittle = subtittle;
        this.date = date;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubtittle() {
        return subtittle;
    }

    public void setSubtittle(String subtittle) {
        this.subtittle = subtittle;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getGuid(@NotBlank String guid) {
        return this.guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}
