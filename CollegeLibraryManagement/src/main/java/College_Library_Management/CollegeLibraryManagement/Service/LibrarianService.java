package College_Library_Management.CollegeLibraryManagement.Service;

import College_Library_Management.CollegeLibraryManagement.Entity.Librarian;
import College_Library_Management.CollegeLibraryManagement.Repository.LibrarianRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LibrarianService {

    private final LibrarianRepository librarianRepository;

    public Librarian registerLibrarian(Librarian librarian){
        return librarianRepository.save(librarian);
    }

    public Optional<Librarian> getLibrarianByEmail(String email){
        return librarianRepository.findByEmail(email);
    }
}
