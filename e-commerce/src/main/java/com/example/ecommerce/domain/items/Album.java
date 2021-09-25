package com.example.ecommerce.domain.items;

import com.example.ecommerce.domain.Item;
import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("A")
public class Album extends Item {
    private String artist;
    private String etc;
}
