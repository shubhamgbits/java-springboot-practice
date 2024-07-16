package com.sg.sb.controller;

import com.sg.sb.model.dto.AuthorDTO;
import com.sg.sb.model.dto.BookDTO;
import com.sg.sb.model.request.AddAuthorRequest;
import com.sg.sb.model.request.AddBookRequest;
import com.sg.sb.model.response.Response;
import com.sg.sb.model.response.SuccessResponse;
import com.sg.sb.service.LibraryService;
import com.sg.sb.util.ServiceConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ServiceConstants.APP_PATH_V1)
public class LibraryController implements ServiceConstants {
    @Autowired
    private LibraryService libraryService;

    @PostMapping(value = ENDPOINT_BOOK)
    public ResponseEntity<Response> saveBook(@RequestBody AddBookRequest request){

        libraryService.addBook(request);

        ResponseEntity<Response> response = ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.get("Insertion Successful"));

        return response;
    }

    @PostMapping(value = ENDPOINT_AUTHOR)
    public ResponseEntity<Response> addAuthor(@RequestBody AddAuthorRequest request){

        libraryService.addAuthor(request);

        ResponseEntity<Response> response = ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.get("Insertion Successful"));

        return response;
    }

    @GetMapping(value = ENDPOINT_AUTHOR_ALL)
    public ResponseEntity<Response> getAllAuthor(){

        List<AuthorDTO> authors = libraryService.getAllAuthors();

        ResponseEntity<Response> response = ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.get(authors));

        return response;
    }

    @GetMapping(value = ENDPOINT_BOOK_ALL)
    public ResponseEntity<Response> getAllBook(){

        List<BookDTO> books = libraryService.getAllBooks();

        ResponseEntity<Response> response = ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.get(books));

        return response;
    }
}
