package College_Library_Management.CollegeLibraryManagement.Service;

import College_Library_Management.CollegeLibraryManagement.Entity.Book;
import College_Library_Management.CollegeLibraryManagement.Repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book addbook(Book book){
        return bookRepository.save(book);
    }

    public void deleteBook(Long bookId){
        bookRepository.deleteById(bookId);
    }

    public Book updateBook(Long bookId,Book book){
        Book book1 = bookRepository.findById(bookId)
                .orElseThrow(()-> new RuntimeException("Book Not Found."));
        book1.setTitle(book.getTitle());
        book1.setAuthor(book.getAuthor());
        book1.setCategory(book.getCategory());
        return bookRepository.save(book1);
    }

    public List<Book> searchBooks(String keyword, String searchType){
        switch (searchType){
            case "title":
                return bookRepository.findByTitleContaining(keyword);
            case "author":
                return bookRepository.findByAuthorContaining(keyword);
            case "category":
                return bookRepository.findByCategoryContaining(keyword);
            default:
                return new ArrayList<>();
        }
    }

}
