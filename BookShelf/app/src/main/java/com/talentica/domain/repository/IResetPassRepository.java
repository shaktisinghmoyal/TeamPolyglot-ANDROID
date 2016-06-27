package com.talentica.domain.repository;

import rx.Observable;

public interface IResetPassRepository {
    Observable<String> tryForResetPass();
}
