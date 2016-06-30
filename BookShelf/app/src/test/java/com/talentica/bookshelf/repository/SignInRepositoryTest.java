package com.talentica.bookshelf.repository;

import com.talentica.data.networking.DummyRestApi;
import com.talentica.data.repository.SignInRepository;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

public class SignInRepositoryTest {


    private SignInRepository signInRepository;

    @Mock
    private DummyRestApi dummyRestApi;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        signInRepository = new SignInRepository(dummyRestApi);

    }

    @Test
    public void testForSignIn() {

        given(dummyRestApi.dummyLoginModule(anyString(), anyString())).willReturn(Observable.just(new JSONObject()));

        signInRepository.tryForSignIn("reshakt", "SHAmoy123");

        verify(dummyRestApi).dummyLoginModule("reshakt", "SHAmoy123");
//        verify(bookEntityDataMapper).transform(bookList);
    }


}
