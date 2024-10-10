package College_Library_Management.CollegeLibraryManagement.Controller;

import College_Library_Management.CollegeLibraryManagement.Entity.Librarian;
import College_Library_Management.CollegeLibraryManagement.Service.LibrarianService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/librarians")
@RequiredArgsConstructor
public class LibrarianController {

    private final LibrarianService librarianService;

    @PostMapping(path = "/register")
    public ResponseEntity<Librarian> registerLibrarian(@RequestBody Librarian librarian){
        Librarian saveLibrarian = librarianService.registerLibrarian(librarian);
        return new ResponseEntity<>(saveLibrarian, HttpStatus.CREATED);
    }

    @GetMapping(path = "/profile")
    public ResponseEntity<Librarian> getLibrarianProfile(@RequestParam String email){
        Optional<Librarian> librarian = librarianService.getLibrarianByEmail(email);
        return librarian.map(value -> new ResponseEntity<>(value,HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
