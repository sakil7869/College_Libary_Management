package College_Library_Management.CollegeLibraryManagement.Repository;

import College_Library_Management.CollegeLibraryManagement.Entity.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BorrowedBookRepository extends JpaRepository<BorrowedBook , Long> {

    @Query("SELECT b FROM BorrowedBook b WHERE b.dueDate < :today AND b.returned = false")
    List<BorrowedBook> findOverdueBorrowings(@Param("today") LocalDate today);

    List<BorrowedBook> findByStudentId(Long studentId);
    BorrowedBook findByStudentIdAndBookId(Long studentId,Long bookId);
}
