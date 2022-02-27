package com.example.homework_api.ui.dashboard;

import android.widget.Toast;

import com.google.gson.Gson;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class DashboardPresenter implements DashboardContract.Presenter {

    DashboardContract.View view;
    final String URL = "https://catfact.ninja/fact";
    String uploadText;//прочитать про модификаторы доступы и их применение в java

    public DashboardPresenter(DashboardContract.View view) {
        this.view = view;
    }

    @Override
    public void uploadText() {
        // Запросы должны быть вынесены в отдельную модель, например FactsRepository
        // MVP работает следующим образом:
        // Model это класс, отвечающий за отправку запросов/работу с бд/обработку данных
        // View это activity/fragment, вью должно обрабатывать элементы для представления пользователю
        // например обработку нажатия на кнопку, установку картинок/кнопок
        // Presenter - это простой объект, связующее звено между вью и моделью
        // презентер вызывается из вью, обрабатывает логику, вызывает модель для запросов,
        // получает ответ и передаёт его на вью

        //MVP https://javarush.ru/groups/posts/505-mvp-v-android-dlja-samihkh-malenjhkikh
        //модификаторы доступа https://javarush.ru/groups/posts/1988-modifikatorih-dostupa-private-protected-default-public
        //для того, чтобы доделать задание нужно добавить модификаторы доступа туда, где это нужно
        //и немного лучше применить мвп шаблон, в данном случае тут не хватает модели


        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                if(response.isSuccessful()) {
                    try {
                        FactJSON factJSON = new Gson().fromJson(response.body().string(), FactJSON.class);
                        uploadText = factJSON.fact;
                        //view.showResult(factJSON.fact)
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        view.showResult(uploadText);//метод вызывается не правильно, текст может не успеть подгрузиться в onResponse
        //поэтому view.showResult должен вызываться только тогда, когда пришёл ответ в onResponse
    }
}
