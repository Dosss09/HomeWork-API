package com.example.homework_api.ui.home;

import android.text.TextUtils;

public class HomeTaskPresenter implements HomeTaskContract.Presenter {

    HomeTaskContract.View view;
    final String catImageUrl = "https://cataas.com/cat?json";

    public HomeTaskPresenter(HomeTaskContract.View view) {
        this.view = view;
    }

    @Override
    public void sendText(String text) {
        if(TextUtils.isEmpty(text)) {
            view.onError("Недостаточно символов!!!");
        } else {
            view.onSuccess("Запрос успешно обработан!!!");
            view.showImage(catImageUrl);
        }
    }
}
