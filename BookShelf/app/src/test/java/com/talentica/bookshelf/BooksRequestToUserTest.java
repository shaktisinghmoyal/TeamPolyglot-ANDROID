package com.talentica.bookshelf;


import com.talentica.domain.model.BooksRequestedToUser;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BooksRequestToUserTest {

    private BooksRequestedToUser booksRequests;

    @Before
    public void setUp() {
        booksRequests = new BooksRequestedToUser("Life Of Pie", "Shakti", "Manish", "09 nov");
    }

    @Test
    public void testBookSimpleCase() {
        String bookName = booksRequests.getBookName();

        assertThat(bookName, is("Life Of Pie"));
    }
}
