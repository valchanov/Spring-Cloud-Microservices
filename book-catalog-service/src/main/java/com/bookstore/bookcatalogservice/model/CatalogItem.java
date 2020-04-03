package com.bookstore.bookcatalogservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CatalogItem {
    private String title;
    private String author;
    private String description;
    private int rating;
}
