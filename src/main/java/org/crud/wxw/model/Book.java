package org.crud.wxw.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    Long id;
    @NotEmpty(message = "Имя не должно быть пустым.")
    @Size(min= 2, max =100, message = "Имя должно быть от до 100 символов.")
    String name;
    @NotEmpty(message = "Поле автор не должно быть пустым.")
    @Size(min= 2, max =100, message = "Имя автора должно быть от до 100 символов.")
    String author;
    @Min(value = 1400, message = "Год должен быть больше, чем 1400")
    int year;
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
