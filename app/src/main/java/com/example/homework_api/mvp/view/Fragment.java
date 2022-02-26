package com.example.homework_api.mvp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.homework_api.mvp.MvpContract;
import com.example.homework_api.mvp.presenter.Presenter;

public class Fragment extends androidx.fragment.app.Fragment implements MvpContract.View {
    /**
     * Пример работы с паттерном МВП
     */

    private Presenter presenter;
    Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        presenter = new Presenter(this);
        button.setOnClickListener((v) -> {
            presenter.getText();
        });
        return null;
    }

    @Override
    public void showText(String text) {
        //показывать текст пользователю
    }

    @Override
    public void showError() {

    }
}
