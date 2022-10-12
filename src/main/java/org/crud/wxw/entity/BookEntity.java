package org.crud.wxw.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "book", schema = "crudspring",catalog = "postgres")
@EqualsAndHashCode
@ToString
public class BookEntity {
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    @Column (name = "name")
    String name;
    @Column (name = "author")
    String author;
    @Column (name = "year")
    int yaer;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    PersonEntity personEntity;
}
