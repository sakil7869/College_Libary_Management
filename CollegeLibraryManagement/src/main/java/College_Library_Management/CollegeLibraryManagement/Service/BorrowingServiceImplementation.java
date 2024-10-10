package College_Library_Management.CollegeLibraryManagement.Service;
import College_Library_Management.CollegeLibraryManagement.Entity.Book;
import College_Library_Management.CollegeLibraryManagement.Entity.BorrowedBook;
import College_Library_Management.CollegeLibraryManagement.Entity.Student;
import College_Library_Management.CollegeLibraryManagement.Repository.BookRepository;
import College_Library_Management.CollegeLibraryManagement.Repository.BorrowedBookRepository;
import College_Library_Management.CollegeLibraryManagement.Repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
public class BorrowingServiceImplementation {

    private final BorrowedBookRepository borrowedBookRepository;
    private final BookRepository bookRepository;
    private final StudentRepository studentRepository;
    private final JavaMailSender javaMailSender;

    public String issueBook(Long studentId,Long bookId){
        Student student = studentRepository.findById(studentId).orElseThrow(()-> new RuntimeException("Student Not Found."));
        if (student.getBorrowedBooks()>=3){
            return "Limit exceeded: A student cannot borrow more than 3 books.";
        }
        Book book = bookRepository.findById(bookId).orElseThrow(()-> new RuntimeException("Book Not Found."));
        if (!book.isAvailable()){
            return "Book is not available.";
        }

        BorrowedBook borrowedBook = new BorrowedBook();
        borrowedBook.setStudent(student);
        borrowedBook.setBook(book);
        borrowedBook.setIssueDate(LocalDate.now());
        borrowedBook.setDueDate(LocalDate.now().plusDays(15));

        borrowedBookRepository.save(borrowedBook);

        book.setAvailable(false);
        bookRepository.save(book);

        student.setBorrowedBooks(student.getBorrowedBooks()+1);
        studentRepository.save(student);

        return "Book issued Successfully!";
        }
    @Scheduled(cron = "0 0 9 * * ?")
    public void sendOverDueReminders(){
        LocalDate today = LocalDate.now();
        List<BorrowedBook> overDueBorrowedBooks = borrowedBookRepository.findOverdueBorrowings(today);

        for (BorrowedBook borrowedBook : overDueBorrowedBooks){
            sendReminderEmail(borrowedBook);
        }
    }
    private void sendReminderEmail(BorrowedBook borrowedBook){
        String to = borrowedBook.getStudent().getEmail();
        String subject = "OverDue Book Reminder";
        String text = "Dear "+borrowedBook.getStudent().getName()+",\n\n"+
                "The Book Title "+borrowedBook.getBook().getTitle()+"' was due on "+borrowedBook.getDueDate() +" . "+
                "You have been fine Rs. "+calculateFine(borrowedBook.getDueDate())+". Please return the book to avoid further fines.\n\n"+
                "Library Management";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
    }

    private int calculateFine(LocalDate dueDate){
        long daysOverDue = ChronoUnit.DAYS.between(dueDate,LocalDate.now());
        return (int)(daysOverDue * 10);
    }
}
