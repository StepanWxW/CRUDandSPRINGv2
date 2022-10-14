package org.crud.wxw.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.internal.build.AllowPrintStacktrace;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "person", schema = "crudspring",catalog = "postgres")
@NoArgsConstructor
@AllArgsConstructor
public class  Person {
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    @Column (name = "name")
    @NotEmpty(message = "Имя не должно быть пустым.")
    @Size(min= 2, max =20, message = "Имя должно быть от до 20 символов.")
    private String name;
    @Column (name = "age")
    @Min(value = 1900, message = "Год рождения должен быть больше чем 1900")
    private int age;
    @OneToMany (mappedBy = "person")
    private List<Book> bookList;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

}
