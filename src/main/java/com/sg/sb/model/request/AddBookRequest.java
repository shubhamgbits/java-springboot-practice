package com.sg.sb.model.request;

import lombok.Data;

@Data
public class AddBookRequest {
    private String name;
    private String authorId;
    private String rating;
}
