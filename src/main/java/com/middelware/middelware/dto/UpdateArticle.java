package com.middelware.middelware.dto;

import com.middelware.middelware.Models.Theme;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateArticle {
    private Long id;
    private String title;
    private String description;
    private String image;
}
