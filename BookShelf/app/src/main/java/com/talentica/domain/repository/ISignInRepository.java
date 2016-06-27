package com.talentica.domain.repository;

import rx.Observable;

public interface ISignInRepository {

    Observable<String> tryForSignIn(String username, String password);

}
