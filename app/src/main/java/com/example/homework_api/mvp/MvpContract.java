package com.example.homework_api.mvp;

public interface MvpContract {
    interface Presenter {
        void getText();
    }

    interface View {
        void showText(String text);
        void showError();
    }

    interface Model {
        void asyncGetTextRequest();
    }
}
