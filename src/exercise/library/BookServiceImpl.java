package exercise.library;


/**
 * Created by sfalcon on 06/05/2015.
 */
public class BookServiceImpl implements BookService {

    BookRepository repository;

    BookServiceImpl(BookRepository bookRepository) {
        this.repository = bookRepository;
    }

    @Override
    public Book retrieveBook(String isbn) throws BookNotFoundException{
        if (!isbn.startsWith("ISBN-"))
            throw new IllegalArgumentException("ISBN must begin with \"ISBN-\"");

        Book retrieved = repository.retrieveBook(isbn);

        if (retrieved == null)
            throw new BookNotFoundException();

        return retrieved;
    }

    @Override
    public String getBookSummary(String isbn) throws BookNotFoundException {
        return null;
    }
}

