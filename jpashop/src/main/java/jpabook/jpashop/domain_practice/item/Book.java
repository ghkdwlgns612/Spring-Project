package jpabook.jpashop.domain_practice.item;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("B")
public class Book extends Item {
    private String author;
    private String isbn;
}
