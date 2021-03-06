package com.example.ecommerce.domain.items;

import com.example.ecommerce.domain.Item;
import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
@Getter
public class Movie extends Item {
    private String director;
    private String actor;
}
