package College_Library_Management.CollegeLibraryManagement.Controller;

import College_Library_Management.CollegeLibraryManagement.Entity.Book;
import College_Library_Management.CollegeLibraryManagement.Service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/books")
@RequiredArgsConstructor
@PreAuthorize("hasRole('LIBRARIAN')")
public class BookController {

    private final BookService bookService;

    @PostMapping(path = "/{add}")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book saveBook = bookService.addbook(book);
        return new ResponseEntity<>(saveBook, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId){
        bookService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{bookId}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable Long bookId){
        Book updateBook = bookService.updateBook(bookId, book);
        return new ResponseEntity<>(updateBook,HttpStatus.OK);
    }


    @GetMapping(path = "/search")
    public ResponseEntity<List<Book>> searchBook(@RequestParam String keyword,@RequestParam String searchType){
        List<Book> books = bookService.searchBooks(keyword, searchType);
        return new ResponseEntity<>(books,HttpStatus.OK);
    }
}
