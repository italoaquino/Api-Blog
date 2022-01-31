package com.blog.blog.dtos;

import com.blog.blog.entites.Category;
import com.blog.blog.entites.Post;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Builder
public class CategoryDTO {

    private Long id;

    private String guid;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd' : 'HH:mm:ss", timezone="GMT")
    private LocalDateTime date;

    @NotBlank
    private String name;

    public static CategoryDTO toDTO(Category category){
        return CategoryDTO.builder()
                .id(category.getId())
                .guid(category.getGuid())
                .date(category.getDate())
                .name(category.getName())
                .build();

    }


}
