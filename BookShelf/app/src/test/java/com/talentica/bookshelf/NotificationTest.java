package com.talentica.bookshelf;

import com.talentica.domain.model.Notification;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NotificationTest {
    private Notification notification;

    @Before
    public void setUp() {
        notification = new Notification("Life Of Pie", "Shakti Singh Moyal on 13 may", true, "10 hour ago");
    }

    @Test
    public void testNotificationSimpleCase() {
        String bookName = notification.getBookName();

        assertThat(bookName, is("Life Of Pie"));
        assertThat(notification.getDueToOrFrom(), is(true));
    }
}
