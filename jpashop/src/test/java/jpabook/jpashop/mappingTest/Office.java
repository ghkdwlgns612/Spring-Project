package jpabook.jpashop.mappingTest;

import org.apache.tomcat.jni.Address;

import javax.persistence.*;

@Entity
public class Office {
    @Id
    @GeneratedValue
    @Column(name = "office_id")
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addressId")
    private A
ddress address;
}