package com.example.homework_api.ui.dashboard;

public class DashboardPresenter implements DashboardContract.Presenter, FactListener {

    private DashboardContract.View view;
    private DashboardContract.Repository repository;
    private static String URL = "https://catfact.ninja/fact";

    public DashboardPresenter(DashboardContract.View view) {
        this.view = view;
        this.repository = new FactRepository(this);
    }

    @Override
    public void uploadLinks() {
        repository.loadFact(URL);
    }

    @Override
    public void onSuccess(String fact) {
        //имплементируем метод слушателя
        //получаем текст, который вернулся в ответе, передаём его дальше на вью
        System.out.println("Facts " + fact);
        view.showResult(fact);
    }

    @Override
    public void onError() {
        //handle error
    }
}
