package com.example.homework_api.ui.home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.homework_api.databinding.FragmentHomeBinding;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment implements HomeTaskContract.View {

    private FragmentHomeBinding binding;
    private EditText etView;
    private ImageView imageView;
    private Button btn;

    HomeTaskContract.Presenter presenter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        presenter = new HomeTaskPresenter(this);

        etView = binding.txtSend;
        btn = binding.btnSend;
        imageView = binding.imgAccept;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = etView.getText().toString();
                presenter.sendText(text);
            }
        });

        homeViewModel.getText().observe(getViewLifecycleOwner(), etView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onSuccess(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showImage(String text) {
        Picasso.with(getActivity()).load(text).into(imageView);
    }
}