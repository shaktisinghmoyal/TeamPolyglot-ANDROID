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
import com.talentica.domain.model.BooksRequestedToUser;
import com.talentica.domain.model.Notification;
import com.talentica.presentation.leadCapturePage.home.model.BookModel;
import com.talentica.presentation.leadCapturePage.notifications.model.NotificationModel;
import com.talentica.presentation.leadCapturePage.tasks.model.BooksRequestedToUserModel;
import com.talentica.presentation.mapper.DataMapper;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class BookModelDataMapperTest extends TestCase {


    private static final String BOOK_NAME = "Life";
    private static final String BOOK_AUTHER = "Ashutosh";
    private static final String BOOK_LENDER = "Akash";
    private static final boolean DUE = true;
    private static final String BOOK_DUE_TO_FROM = "Akash";
    private static final String BOOK_REQUESTED_BY = "Akash";

    private DataMapper bookModelDataMapper;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        bookModelDataMapper = new DataMapper();
    }

    public void testTransformBook() {
        Book book = createFakeBook();
        BookModel bookModel = bookModelDataMapper.transform(book);

        assertThat(bookModel, is(instanceOf(BookModel.class)));
        assertThat(bookModel.getBookName(), is(BOOK_NAME));
        assertThat(bookModel.getAuthersName(), is(BOOK_AUTHER));
        assertThat(bookModel.getLender(), is(BOOK_LENDER));
    }

    public void testTransformRequestedBook() {
        BooksRequestedToUser booksRequested = createFakeRequestedBook();
        BooksRequestedToUserModel bookRequestedModel = bookModelDataMapper.transform(booksRequested);

        assertThat(bookRequestedModel, is(instanceOf(BooksRequestedToUserModel.class)));
        assertThat(bookRequestedModel.getBookName(), is(BOOK_NAME));
        assertThat(bookRequestedModel.getBookAuther(), is(BOOK_AUTHER));
        assertThat(bookRequestedModel.getRequestedBy(), is(BOOK_REQUESTED_BY));
    }

    public void testTransformNotification() {
        Notification notification = createFakeNotification();
        NotificationModel notificationModel = bookModelDataMapper.transform(notification);

        assertThat(notificationModel, is(instanceOf(NotificationModel.class)));
        assertThat(notificationModel.getBookName(), is(BOOK_NAME));
        assertThat(notificationModel.getReturnDueToOrFrom(), is(BOOK_DUE_TO_FROM));
        assertThat(notificationModel.getDueToOrFrom(), is(DUE));
    }

    public void testTransformNotificationCollection() {
        Notification notification1 = mock(Notification.class);
        Notification notification2 = mock(Notification.class);

        List<Notification> notificationList = new ArrayList<Notification>(5);
        notificationList.add(notification1);
        notificationList.add(notification2);

        Collection<NotificationModel> notificationModels = bookModelDataMapper.transformNotifications(notificationList);

        assertThat(notificationModels.toArray()[0], is(instanceOf(NotificationModel.class)));
        assertThat(notificationModels.toArray()[1], is(instanceOf(NotificationModel.class)));
        assertThat(notificationModels.size(), is(2));
    }

    public void testTransformBookCollection() {
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

    public void testTransformRequestedBookCollection() {
        BooksRequestedToUser mockBookRequestedOne = mock(BooksRequestedToUser.class);
        BooksRequestedToUser mockBookRequestedTwo = mock(BooksRequestedToUser.class);

        List<BooksRequestedToUser> bookRequestedList = new ArrayList<BooksRequestedToUser>(5);
        bookRequestedList.add(mockBookRequestedOne);
        bookRequestedList.add(mockBookRequestedTwo);

        Collection<BooksRequestedToUserModel> bookrequestedModelList = bookModelDataMapper.transformRequestedBooks(bookRequestedList);

        assertThat(bookrequestedModelList.toArray()[0], is(instanceOf(BooksRequestedToUserModel.class)));
        assertThat(bookrequestedModelList.toArray()[1], is(instanceOf(BooksRequestedToUserModel.class)));
        assertThat(bookrequestedModelList.size(), is(2));
    }



    private Book createFakeBook() {
        Book book = new Book(BOOK_NAME, BOOK_AUTHER, BOOK_REQUESTED_BY, "MoyalSahab", "Ashutosh", "Akash", "MoyalSahab", "Ashutosh", "Akash", "shakti");


        return book;
    }

    private BooksRequestedToUser createFakeRequestedBook() {
        BooksRequestedToUser book = new BooksRequestedToUser(BOOK_NAME, BOOK_AUTHER, BOOK_REQUESTED_BY, "09 Nov");


        return book;
    }

    private Notification createFakeNotification() {
        Notification notification = new Notification(BOOK_NAME, BOOK_DUE_TO_FROM, DUE, "09 May");

        return notification;
    }
}
