package com.example.prj666shelterdashboard.ui.gallery;

import static androidx.navigation.fragment.FragmentKt.findNavController;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentKt;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prj666shelterdashboard.MainActivity;
import com.example.prj666shelterdashboard.R;
import com.example.prj666shelterdashboard.RetrofitClient;
import com.example.prj666shelterdashboard.databinding.FragmentGalleryBinding;
import com.example.prj666shelterdashboard.ui.Shelter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryFragment extends Fragment  {

    private FragmentGalleryBinding binding;
    private RecyclerView recyclerView;
    private ShelterListAdapter shelterAdapter;
    private List<Shelter> shelters;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recyclerViewShelterList);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        shelters = new ArrayList<>(); // Initialize the list

        getShelters();


        return root;
    }

    private void getShelters() {
        // Make the asynchronous API call
        Call<List<Shelter>> call = RetrofitClient.getInstance().getMyApi().getShelters(50);
        call.enqueue(new Callback<List<Shelter>>() {
            @Override
            public void onResponse(Call<List<Shelter>> call, Response<List<Shelter>> response) {
                if (response.isSuccessful()) {
                    Log.d("Shelter Fragment", "Successful API Call");
                    shelters = response.body();
                    shelterAdapter = new ShelterListAdapter(shelters, new ShelterListAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Shelter selectedShelter = shelters.get(position);
                            // Create a bundle to pass data to the ShelterDetail fragment if needed
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("Shelter",selectedShelter);
                            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
                            navController.navigate(R.id.action_nav_shelter_to_nav_shelterDetail,bundle);
                        }
                    });
                    recyclerView.setAdapter(shelterAdapter);

                } else {
                    Log.e("Shelter Fragment Response", "Error: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<List<Shelter>> call, Throwable t) {
                Log.e("Shelter Fragment", "Error: " + t.getMessage());
            }
        });

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}