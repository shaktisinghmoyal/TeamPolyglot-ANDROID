package com.talentica.bookshelf.repository;

import com.talentica.data.networking.DummyRestApi;
import com.talentica.data.repository.ResetPassRepository;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

public class ResetPassRepositoryTest {

    private ResetPassRepository resetPassRepository;

    @Mock
    private DummyRestApi dummyRestApi;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        resetPassRepository = new ResetPassRepository(dummyRestApi);

    }

    @Test
    public void testForForgotPass() {

        given(dummyRestApi.dummyLoginModule(anyString(), anyString())).willReturn(Observable.just(new JSONObject()));

        resetPassRepository.tryForResetPass("shakti.singh0708@gmail.com");

        verify(dummyRestApi).dummyLoginModule("reshakt", "SHAmoy123");
//        verify(bookEntityDataMapper).transform(bookList);
    }

}
