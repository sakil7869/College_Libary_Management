package College_Library_Management.CollegeLibraryManagement.Controller;

import College_Library_Management.CollegeLibraryManagement.Entity.Student;
import College_Library_Management.CollegeLibraryManagement.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping(path = "/{register}")
    public ResponseEntity<Student> registerStudent(@RequestBody Student student){
        Student savedStudent = studentService.registerStudent(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }
}
