package College_Library_Management.CollegeLibraryManagement.Service;

import College_Library_Management.CollegeLibraryManagement.Entity.Student;
import College_Library_Management.CollegeLibraryManagement.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Student registerStudent(Student student){
        return studentRepository.save(student);
    }

    public Optional<Student> getStudentById(Long studentId){
        return studentRepository.findById(studentId);
    }
}
