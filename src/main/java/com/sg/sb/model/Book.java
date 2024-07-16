package com.sg.sb.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String rating;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
