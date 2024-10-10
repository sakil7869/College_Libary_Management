package College_Library_Management.CollegeLibraryManagement.Controller;
import College_Library_Management.CollegeLibraryManagement.Service.BorrowingServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('LIBRARIAN')")
@RequestMapping(path = "/borrowing")
public class BorrowingController {

    private final BorrowingServiceImplementation borrowingServiceImplementation;

    @PostMapping(path = "/issue/{studentId}/{bookId}")
    public ResponseEntity<String> issueBook(@PathVariable Long studentId, @PathVariable Long bookId) {
        String result = borrowingServiceImplementation.issueBook(studentId, bookId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/sendOverDueReminders")
    public ResponseEntity<String> sendOverdueReminders(){
        borrowingServiceImplementation.sendOverDueReminders();
        return new ResponseEntity<>("Overdue Reminders sent successfully ",HttpStatus.OK);
    }
}

//    @GetMapping(path = "/fine")
//    public ResponseEntity<Integer> calculateFine(@RequestParam LocalDate issueDate,@RequestParam LocalDate returnDate){
//        int fine = borrowingServiceImplementation.calculateFine(issueDate, returnDate);
//        return new ResponseEntity<>(fine,HttpStatus.OK);
//    }
//}

