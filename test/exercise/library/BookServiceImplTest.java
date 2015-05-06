package exercise.library;

import static org.junit.Assert.*;

/**
 * Created by sfalcon on 06/05/2015.
 */
public class BookServiceImplTest {

    BookServiceImpl bookService;

    @org.junit.Before
    public void setUp(){
        bookService = new BookServiceImpl(new BookRepositoryImpl());
    }

    @org.junit.Test
    public void testInvalidText() throws Exception {
        //Throws an exception when it doesn't start with ISBN-
        Exception invalidText = new Exception("blank");
        try{
            bookService.retrieveBook("INVALID-TEXT");
        }catch (Exception e){
            invalidText = e;
        }

        assertEquals("ISBN must begin with \"ISBN-\"", invalidText.getMessage());

        invalidText = new Exception("blank");
        try{
            bookService.getBookSummary("INVALID-TEXT");
        }catch (Exception e){
            invalidText = e;
        }
        assertEquals("ISBN must begin with \"ISBN-\"", invalidText.getMessage());
    }

    @org.junit.Test
    public void testBookNotFound() throws Exception {
        //Throws a not found exception for a book not in the repository
        Exception notFound = new Exception("blank");
        try{
            bookService.retrieveBook("ISBN-777");
        }catch (Exception e) {
            notFound = e;
        }

        assertEquals(BookNotFoundException.class, notFound.getClass());

        notFound = new Exception("blank");
        try{
            bookService.getBookSummary("ISBN-777");
        }catch (Exception e) {
            notFound = e;
        }

        assertEquals(BookNotFoundException.class, notFound.getClass());
    }

    @org.junit.Test
    public void testValidBook() throws Exception{
        //Finds a valid book
        Book book = bookService.retrieveBook("ISBN-001");
        assertEquals("Harry Potter and the Deathly Hallows", book.getTitle());
    }

    @org.junit.Test
    public void testValidSummary() throws Exception {

        assertEquals("Sorcery and Magic.",
                    bookService.getBookSummary("ISBN-001"));
        assertEquals("Jernau Morat Gurgeh. The Player of Games. Master of every board, computer and strategy.",
                    bookService.getBookSummary("ISBN-002"));
        assertEquals("A brilliant interweaving of Richard Feynman's colourful life and a detailed and accessible account of his theories and experiments.",
                    bookService.getBookSummary("ISBN-003"));
    }
}