package com.example.homework_api.mvp.presenter;

import com.example.homework_api.mvp.MvpContract;
import com.example.homework_api.mvp.model.Repo;
import com.example.homework_api.mvp.model.RepoCallback;

public class Presenter implements MvpContract.Presenter, RepoCallback {
    private final Repo repo;
    private final MvpContract.View listener;

    public Presenter(MvpContract.View listener) {
        this.listener = listener;
        this.repo = new Repo(this);
    }

    public void getText() {
        repo.asyncGetTextRequest();
    }

    @Override
    public void onSuccess(String text) {
        listener.showText(text);
    }

    @Override
    public void onError() {
        listener.showError();
    }

}
