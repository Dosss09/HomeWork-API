package com.example.homework_api.ui.dashboard;

public interface DashboardContract {
    interface View {
        void onSuccess(String message);
        void onError(String message);
        void showResult(String uploadText);
    }

    interface Presenter {
        void uploadText();
    }
}
