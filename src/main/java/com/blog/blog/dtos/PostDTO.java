package com.blog.blog.dtos;

import com.blog.blog.entites.Post;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
public class PostDTO {

    private Long category_id;


    private String guid;

    @NotBlank
    private String tittle;

    @NotBlank
    private String subtittle;

    @NotBlank
    private String text;

    @NotBlank
    private String author;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd' : 'HH:mm:ss", timezone="GMT")
    private LocalDateTime date;

    public static PostDTO toDto(Post post){

        return PostDTO.builder()
                .guid(post.getGuid())
                .tittle(post.getTittle())
                .subtittle(post.getSubtittle())
                .text(post.getText())
                .author(post.getAuthor())
                .date(post.getDate())
                .guid(post.getGuid())
                .category_id(post.getCategory().getId())
                .build();

    }


}
