package com.example.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    private List<CategoryItem> categoryItems = new ArrayList<>();

}
