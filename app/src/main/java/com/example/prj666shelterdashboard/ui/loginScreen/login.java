package com.example.prj666shelterdashboard.ui.loginScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.prj666shelterdashboard.MainActivity;
import com.example.prj666shelterdashboard.R;
import com.example.prj666shelterdashboard.RetrofitClient;
import com.example.prj666shelterdashboard.ShelterApiService;
import com.example.prj666shelterdashboard.databinding.FragmentHomeBinding;
import com.example.prj666shelterdashboard.databinding.FragmentLoginBinding;
import com.example.prj666shelterdashboard.ui.SignInRequest;
import com.example.prj666shelterdashboard.ui.UserResponse;
import com.example.prj666shelterdashboard.ui.home.HomeViewModel;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class login extends Fragment {
    private FragmentLoginBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        MaterialButton loginbtn = (MaterialButton) root.findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignIn();
            }
        });

        return root;
    }

    private void SignIn() {
        View root = binding.getRoot();

        TextView username =(TextView) root.findViewById(R.id.username);
        TextView pass =(TextView) root.findViewById(R.id.password);

        String email = username.getText().toString();
        String password = pass.getText().toString();

        SignInRequest signInRequest = new SignInRequest(email, password);

        Call<UserResponse> call = RetrofitClient.getInstance().getMyApi().signIn(signInRequest);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    UserResponse userResponse = response.body();
                    Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();

                    MainActivity activity = (MainActivity) getActivity();
                    activity.updateUser(userResponse.getFirstName(),userResponse.getLastName(),userResponse.getEmail());
                    activity.setAccessLevel(userResponse.getAccess());
                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
                    navController.navigate(R.id.action_LoginFragment_to_nav_home);

                } else {
                    Toast.makeText(getActivity(), "Sign-in failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }



}