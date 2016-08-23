package com.talentica.bookshelf.repository;

import com.talentica.data.repository.NotificationRepository;
import com.talentica.data.storage.DataBase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class NotificationRepositoryTest {

    private NotificationRepository repository;
    @Mock
    private DataBase dataBase;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        repository = new NotificationRepository(dataBase);

    }

    @Test
    public void testForBooksRequested() {
        repository.getNotifications();
        verify(dataBase).makeRequestedBookList();

    }
}
