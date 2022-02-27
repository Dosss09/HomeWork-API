package com.example.homework_api.ui.dashboard;

public class DashboardPresenter implements DashboardContract.Presenter {

    private DashboardContract.View view;
    private DashboardContract.Repository repository;
    private static String URL = "https://catfact.ninja/fact";
    private String facts;

    public DashboardPresenter(DashboardContract.View view) {
        this.view = view;
        this.repository = new FactRepository();
    }

    public DashboardPresenter(DashboardContract.Repository factRepository) {

    }

    @Override
    public void uploadLinks() {
        repository.loadFact(URL);
    }

    @Override
    public void downloadFact(String fact) {
        System.out.println("Facts " + fact);
        view.showResult(fact);
    }
}
