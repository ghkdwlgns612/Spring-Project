package com.example.ecommerce.domain.items;

import com.example.ecommerce.domain.Item;
import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@Getter
public class Book extends Item{
    private String author;
    private String isbn;
}
