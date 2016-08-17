
package com.talentica.bookshelf;

import com.talentica.domain.model.Book;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BookTest {

    private Book book;

    @Before
    public void setUp() {
        book = new Book("Life Of Pie", "Shakti Singh Moyal", "Apun", "", "Shakti Singh Moyal", "Apun", "", "Shakti Singh Moyal", "Apun", "");
    }

    @Test
    public void testBookSimpleCase() {
        String bookName = book.getBookName();

        assertThat(bookName, is("Life Of Pie"));
    }
}
