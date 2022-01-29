package com.blog.blog.entites;


import com.fasterxml.jackson.annotation.JsonFormat;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String tittle;

    @NotBlank
    private String subtittle;

    @NotBlank
    private String author;

    @NotBlank
    private String text;

    private String guid;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd' : 'HH:mm:ss", timezone="GMT")
    private LocalDateTime date;

    public Post(){
    }

    public Post(String tittle, String text, LocalDateTime date, String guid, String subtittle, String author){
        this.tittle = tittle;
        this.text = text;
        this.date = date;
        this.guid = guid;
        this.subtittle =subtittle;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setSubtittle(String subtittle) {
        this.subtittle = subtittle;
    }

    public String getSubtittle() {
        return subtittle;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }
}
