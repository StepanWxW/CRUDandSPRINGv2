package org.crud.wxw.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "person", schema = "crudspring",catalog = "postgres")
@EqualsAndHashCode
@ToString

public class PersonEntity {
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    @Column (name = "name")
    String name;
    @Column (name = "age")
    int age;
    @OneToMany (mappedBy = "personEntity")
    List<BookEntity> bookListEntity;

    public PersonEntity(Long id) {
        this.id = id;
    }

    public PersonEntity() {
    }
}
