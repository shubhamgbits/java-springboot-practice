package com.sg.sb.model.dto;

import lombok.Data;

@Data
public class BookDTO {
    private long id;
    private String name;
    private Long authorId;
}
