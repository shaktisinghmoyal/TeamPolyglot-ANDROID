package com.talentica.domain.repository;

import rx.Observable;

public interface ISignUpRepository {
    Observable<String> tryForSignUp();
}
