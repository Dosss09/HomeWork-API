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
    String uploadText;

    public DashboardPresenter(DashboardContract.View view) {
        this.view = view;
    }

    @Override
    public void uploadText() {

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
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        view.showResult(uploadText);
    }
}
