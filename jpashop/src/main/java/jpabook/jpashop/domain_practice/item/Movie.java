package jpabook.jpashop.domain_practice.item;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("M")
public class Movie extends Item {
    private String director;
    private String actor;
}
