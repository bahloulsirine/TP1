package com.middelware.middelware.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateArticle {

    private Long id;
    private String title;
    private String description;
    private String image;
    private Long coachId;
    private Long themeId;
}