package College_Library_Management.CollegeLibraryManagement.Repository;

import College_Library_Management.CollegeLibraryManagement.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
}
