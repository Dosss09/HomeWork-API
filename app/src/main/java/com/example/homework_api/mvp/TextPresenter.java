package com.example.homework_api.mvp;

public class TextPresenter implements TextModelInterface {
    private final TextModel textModel;
    private final TextViewInterface listener;

    public TextPresenter(TextViewInterface listener) {
        this.listener = listener;
        this.textModel = new TextModel(this);
    }

    public void doLogic() {
        //send request
        textModel.sendAsyncRequest();
    }

    @Override
    public void onSuccessResponse(Object response) {
        listener.setText();
    }

    @Override
    public void onErrorResponse(Object response) {

    }
}
