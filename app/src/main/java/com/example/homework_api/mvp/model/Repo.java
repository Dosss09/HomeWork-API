package com.example.homework_api.mvp.model;

import com.example.homework_api.mvp.MvpContract;

public class Repo implements MvpContract.Model {
    private final RepoCallback callback;

    public Repo(RepoCallback callback) {
        this.callback = callback;
    }

    /**
     * метод вызывает retrofit и отправляет асинхронный запрос, в асинхронный запрос
     * в коллбэке использует ModelListener.onSuccess и ModelListener.onError
     */
    public void asyncGetTextRequest() {
        //вызывает retrofit
    }
}
