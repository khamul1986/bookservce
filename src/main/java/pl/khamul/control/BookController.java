package pl.khamul.control;


import org.springframework.web.bind.annotation.*;
import pl.khamul.domain.Book;
import pl.khamul.service.MockBookService;

import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {

    MockBookService mockBookService;

    public BookController(MockBookService mockBookService) {
        this.mockBookService = mockBookService;
    }


    @RequestMapping("/helloBook")
    public Book helloBook() {
        return new Book("9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }

    @GetMapping()
    public List<Book> printBook() {
        return mockBookService.getList();
    }

    @GetMapping(path = "/{id}")
    public Book printBook(@PathVariable Long id) {
        return mockBookService.printBook(id);


    }

    @PostMapping()
    public void addBook(@RequestBody Book book) {
        mockBookService.add(book);

    }

    @DeleteMapping(path = "/{id}")
    public void deleteBook(@PathVariable Long id) {
        mockBookService.deleteBook(id);
    }

    @PutMapping("")
    public void updateBook(@RequestBody Book book) {
        mockBookService.updateBook(book);

    }
}
