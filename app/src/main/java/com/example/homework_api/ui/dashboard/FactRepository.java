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

    private DashboardContract.Presenter presenter;

    private static final String TAG = "";
    private String uploadFact;

    @Override
    public void loadFact(String url) {

        presenter = new DashboardPresenter(this);

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
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

                        Log.d(TAG, "Fact: " + factJSON.fact);
                        uploadFact = factJSON.fact;

                        presenter.downloadFact(uploadFact);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
