package com.talentica.bookshelf.repository;

import com.talentica.data.networking.DummyRestApi;
import com.talentica.data.repository.SignUpRepository;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

public class SignUpRepositoryTest {


    private SignUpRepository signUpRepository;

    @Mock private DummyRestApi dummyRestApi;



    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        signUpRepository = new SignUpRepository(dummyRestApi);

    }

    @Test
    public void testForSignUp() {

        given(dummyRestApi.dummyLoginModule(anyString(),anyString())).willReturn(Observable.just(new JSONObject()));

        signUpRepository.tryForSignUp("reshakt","SHAmoy123","Shakti Singh Moyal");

        verify(dummyRestApi).dummyLoginModule("reshakt","SHAmoy123");
//        verify(bookEntityDataMapper).transform(bookList);
    }

}
