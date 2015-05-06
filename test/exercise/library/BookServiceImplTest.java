package exercise.library;

import static org.junit.Assert.*;

/**
 * Created by nogard on 06/05/2015.
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
    }

    @org.junit.Test
    public void testBookNotFound() throws Exception {
        //Throws a not found exception for a book not in the repository
        Exception notFound = new Exception("blank");
        try{
            bookService.retrieveBook("ISBN-777");
        }catch (Exception e){
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
    public void testGetBookSummary() throws Exception {

    }
}