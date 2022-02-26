package com.example.homework_api.ui.home;

public interface HomeTaskContract {
    interface View {
        void onSuccess(String message);
        void onError(String message);
        void showImage(String text);
    }

    interface Presenter {
        void sendText(String text);
    }
}
