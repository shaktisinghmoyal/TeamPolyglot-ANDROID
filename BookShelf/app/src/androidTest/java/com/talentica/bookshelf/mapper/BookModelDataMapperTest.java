/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.talentica.bookshelf.mapper;


import com.talentica.domain.model.Book;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;
import com.talentica.presentation.mapper.BookModelDataMapper;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class BookModelDataMapperTest extends TestCase {


    private static final String BOOK_NAME = "MoyalSahab";
    private static final String BOOK_AUTHER = "Ashutosh";
    private static final String BOOK_LENDER = "Akash";

    private BookModelDataMapper bookModelDataMapper;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        bookModelDataMapper = new BookModelDataMapper();
    }

    public void testTransformUser() {
        Book book = createFakeBook();
        BookModel bookModel = bookModelDataMapper.transform(book);

        assertThat(bookModel, is(instanceOf(BookModel.class)));
        assertThat(bookModel.getBookName(), is(BOOK_NAME));
        assertThat(bookModel.getAuthersName(), is(BOOK_AUTHER));
        assertThat(bookModel.getLender(), is(BOOK_LENDER));
    }

    public void testTransformUserCollection() {
        Book mockBookOne = mock(Book.class);
        Book mockBookTwo = mock(Book.class);

        List<Book> userList = new ArrayList<Book>(5);
        userList.add(mockBookOne);
        userList.add(mockBookTwo);

        Collection<BookModel> userModelList = bookModelDataMapper.transform(userList);

        assertThat(userModelList.toArray()[0], is(instanceOf(BookModel.class)));
        assertThat(userModelList.toArray()[1], is(instanceOf(BookModel.class)));
        assertThat(userModelList.size(), is(2));
    }

    private Book createFakeBook() {
        Book book = new Book("MoyalSahab", "Ashutosh", "Akash");


        return book;
    }
}
