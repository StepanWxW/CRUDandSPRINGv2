package org.crud.wxw.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;


@Entity
@Getter
@Setter
@Table(name = "book", schema = "crudspring",catalog = "postgres")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

public class Book {
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    @Column (name = "name")
    @NotEmpty(message = "Имя не должно быть пустым.")
    @Size(min= 2, max =100, message = "Имя должно быть от до 100 символов.")
    String name;
    @Column (name = "author")
    @NotEmpty(message = "Поле автор не должно быть пустым.")
    @Size(min= 2, max =100, message = "Имя автора должно быть от до 100 символов.")
    String author;
    @Column (name = "year")
    @Min(value = 1400, message = "Год должен быть больше, чем 1400")
    int year;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    Person person;

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book(String name, String author, int year, Person person) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.person = person;
    }
}
