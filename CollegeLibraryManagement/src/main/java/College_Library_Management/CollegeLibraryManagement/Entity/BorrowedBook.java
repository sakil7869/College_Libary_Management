package College_Library_Management.CollegeLibraryManagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Borrow")
public class BorrowedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_Id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "book_Id")
    private Book book;

    private LocalDate issueDate;
    private LocalDate dueDate;
    private boolean returned = false;
}
