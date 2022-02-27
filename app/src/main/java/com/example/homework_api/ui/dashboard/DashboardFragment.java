package com.example.homework_api.ui.dashboard;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.homework_api.databinding.FragmentDashboardBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DashboardFragment extends Fragment implements DashboardContract.View {

    private static final String TAG = "";
    private FragmentDashboardBinding binding;
    private Button btn;
    private TextView textFact;

    private DashboardContract.Presenter presenter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        btn = binding.btnSend;
        textFact = binding.txtReceive;

        presenter = new DashboardPresenter(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.uploadLinks();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void showResult(String uploadFact) {
        System.out.println("FACTSSS: " + uploadFact);
        if (getActivity() != null) {
            getActivity().runOnUiThread(() -> {//запускаем UI thread для того, чтобы устанавливать элементы в UI потоке
                textFact.setText(uploadFact);
            });
        }
    }
}