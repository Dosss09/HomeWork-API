package com.example.homework_api.mvp;

public class TextModel {
    private final TextModelInterface listener;

    public TextModel(TextModelInterface listener) {
        this.listener = listener;
    }

    public void sendAsyncRequest() {
        //OkHttpClient
        //send enqueue() {onsuccess, onerror}
    }
}
