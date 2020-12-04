package pl.khamul.service;



import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import pl.khamul.domain.Book;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MockBookService {

    private Book book;
    private List<Book> list;


    public MockBookService() {
        list = new ArrayList<>();
        list.add(new Book( "9788324631766", "Thiniking in Java", "Bruce Eckel",  "Helion", "programming"));
        list.add(new Book("9788324627738", "Rusz głową Java.", "Sierra Kathy, Bates Bert", "Helion",
                "programming"));
        list.add(new Book(33L,"9780130819338", "Java 2.Podstawy", "Cay Horstmann, Gary Cornell", "Helion",
                "programming"));
    }

    private Optional<Book> bookPresent(Long id) {
        return  list.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst();
    }


    public Book printBook(@PathVariable Long id) {
        return bookPresent(id).orElseThrow(() -> {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "brak obiektu"
            );
        });
    }


    public void add(Book book){
        list.add(book);
    }

    public void deleteBook(Long id){

        if(bookPresent(id).isPresent()) {
            list.remove(bookPresent(id).get());
        }else{
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND , "brak obiektu"
            );
        }
    }

    public List<Book> getList() {
        return list;
    }

    public void updateBook(Book book){
        if (bookPresent(book.getId()).isPresent()) {
            list.set(list.indexOf(bookPresent(book.getId()).get()),book);
        }else{
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "brak obiektu"
            );
        }
    }
    }







