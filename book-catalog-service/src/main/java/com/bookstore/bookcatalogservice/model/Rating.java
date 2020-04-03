package com.bookstore.bookcatalogservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private Long id;
    private Long bookid;
    private Integer rating;
}
