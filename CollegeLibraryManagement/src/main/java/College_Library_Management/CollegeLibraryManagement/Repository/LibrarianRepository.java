package College_Library_Management.CollegeLibraryManagement.Repository;

import College_Library_Management.CollegeLibraryManagement.Entity.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, Long> {

    Optional<Librarian> findByEmail(String email);
}
