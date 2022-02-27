package com.example.homework_api.ui.dashboard;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FactRepository implements DashboardContract.Repository {
    private final FactListener listener;//создаем и добавляем слушатель для запросов

    public FactRepository(FactListener listener) {
        this.listener = listener;//получаем его в конструкторе из презентера
    }

    @Override
    public void loadFact(String url) {

        // presenter = new DashboardPresenter(this);
        // тут создавался новый объект презентера, это неправильно, так как ссылка будет уже на другой презентер
        // который не имел ссылки на вью, поэтому текст не сеттился на вью
        // модель не должна иметь ссылку на презентер, она независима от него, в неё можно передавать
        // лишь интерфейсы для коллбэков

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listener.onError();//вызываем метод в слушателя, говорящий об ошибке
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    try {
                        FactJSON factJSON = new Gson().fromJson(response.body().string(), FactJSON.class);
                        Log.d("TAG", "Fact: " + factJSON.fact);
                        listener.onSuccess(factJSON.fact);//вызываем метод слушателя и передаём туда
                        // текст вернувшийся в ответе
                    } catch (Exception e) {
                        listener.onError();
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
