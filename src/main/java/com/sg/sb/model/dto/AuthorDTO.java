package com.sg.sb.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class AuthorDTO{
    private long id;
    private String name;
    private List<BookDTO> books;
}
