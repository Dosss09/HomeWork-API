package com.example.homework_api.ui.dashboard;

public interface DashboardContract {
    interface View {
        void showResult(String uploadText);
    }

    interface Presenter {
        void uploadLinks();
    }

    interface Repository {
        void loadFact(String fact);
    }
}
