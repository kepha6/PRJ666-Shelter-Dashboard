package com.example.prj666shelterdashboard.ui.shelterDetail;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prj666shelterdashboard.R;
import com.example.prj666shelterdashboard.RetrofitClient;
import com.example.prj666shelterdashboard.databinding.FragmentEditShelterDetailsBinding;
import com.example.prj666shelterdashboard.databinding.FragmentShelterDetailBinding;
import com.example.prj666shelterdashboard.ui.Shelter;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditShelterDetails extends Fragment {

    private Shelter shelter;

    private FragmentEditShelterDetailsBinding binding;

    private EditShelterDetailsViewModel mViewModel;

    EditText Name,Address,Postal,City,Group,Org,Program,Sector,Model,Capacity_Type;
    TextView ID,Capacity_Count,Capacity_Unavailable_Bed_LabelCount,Capacity_UnOccupied_Bed_Count,Capacity_Actual_BedCount;
    ProgressBar Occupied_Beds,Unoccupied_Beds,Unavailable_Bed;
    Button CancelBtn,SaveBtn,Capacity_Funding_Bed_BtnM,Capacity_Funding_Bed_Btnp,Capacity_Unoccupied_Bed_BtnM,Capacity_Unoccupied_Bed_BtnP,Capacity_Unavailable_Bed_BtnM,Capacity_Unavailable_Bed_BtnP,Capacity_Actual_Bed_BtnM,Capacity_Actual_Bed_BtnP;

    int TotalCapacity,CurrentCapacity,UnoccupiedCapacity,UnavailableCapacity;
    public static EditShelterDetails newInstance() {
        return new EditShelterDetails();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EditShelterDetailsViewModel EditShelterDetails =
                new ViewModelProvider(this).get(EditShelterDetailsViewModel.class);
        binding = FragmentEditShelterDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Bundle b = getArguments();
        shelter = b.getParcelable("Shelter");

        TotalCapacity = shelter.getCapacityFundingRoom();
        CurrentCapacity = shelter.getCapacityActualRoom();
        UnoccupiedCapacity = shelter.getUnoccupiedRooms();
        UnavailableCapacity = shelter.getUnavailableRooms();

        ID = root.findViewById(R.id.LocationID);
        ID.setText("ID:"+ String.valueOf(shelter.get_id()));

        Name = root.findViewById(R.id.LocationName);
        Name.setText(shelter.getLocationName());

        Address = root.findViewById(R.id.ShelterLocationAddress);
        Address.setText(shelter.getLocationAddress());

        Postal = root.findViewById(R.id.LocationPostalCode);
        Postal.setText(shelter.getLocationPostalCode());

        City = root.findViewById(R.id.LocationCity);
        City.setText(shelter.getLocationCity());

        Group = root.findViewById(R.id.ShelterGroup);
        Group.setText(shelter.getProgramArea());

        Org = root.findViewById(R.id.OrganizationName);
        Org.setText(shelter.getOrganizationName());

        Program = root.findViewById(R.id.ProgramName);
        Program.setText(shelter.getProgramName());

        Sector = root.findViewById(R.id.Sector);
        Sector.setText(shelter.getSector());

        Model = root.findViewById(R.id.ProgramModel);
        Model.setText(shelter.getOvernightServiceType());

        Capacity_Type = root.findViewById(R.id.CapacityType);
        Capacity_Type.setText(shelter.getCapacityType());


        //Total Capacity Section
        Capacity_Funding_Bed_BtnM = root.findViewById(R.id.Capacity_Funding_Bed_BtnM);
        Capacity_Funding_Bed_BtnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CurrentCapacity + UnavailableCapacity <= TotalCapacity){
                    if (UnoccupiedCapacity > 0){
                        UnoccupiedCapacity--;
                        Capacity_UnOccupied_Bed_Count.setText(String.valueOf(UnoccupiedCapacity));
                        Unoccupied_Beds.setProgress(UnoccupiedCapacity);
                        TotalCapacity--;
                        Capacity_Actual_BedCount.setText(String.valueOf(TotalCapacity));
                    }else{
                        Toast toast = Toast.makeText(getContext(),"Cannot Remove, Capacity Check Current or Unavailable", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }else{
                    Toast toast = Toast.makeText(getContext(),"Cannot Remove, Capacity Check Values", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        Capacity_Actual_BedCount = root.findViewById(R.id.Capacity_Actual_BedCount);
        Capacity_Actual_BedCount.setText(String.valueOf(TotalCapacity));
        Capacity_Funding_Bed_Btnp = root.findViewById(R.id.Capacity_Funding_Bed_Btnp);
        Capacity_Funding_Bed_Btnp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TotalCapacity++;
                Capacity_Actual_BedCount.setText(String.valueOf(TotalCapacity));
                UnoccupiedCapacity++;
                Capacity_UnOccupied_Bed_Count.setText(String.valueOf(UnoccupiedCapacity));
                Unoccupied_Beds.setProgress(UnoccupiedCapacity);

            }
        });


        //Unoccupied Bed Section
        Unoccupied_Beds = root.findViewById(R.id.Capacity_Occupied_Bed);
        Unoccupied_Beds.setMax(TotalCapacity);
        Unoccupied_Beds.setProgress(UnoccupiedCapacity);
        Capacity_UnOccupied_Bed_Count = root.findViewById(R.id.Capacity_UnOccupied_Bed_Count);
        Capacity_UnOccupied_Bed_Count.setText(String.valueOf(UnoccupiedCapacity));


        //Unavailable Bed Section
        Unavailable_Bed = root.findViewById(R.id.Capacity_Unavailable_Bed);
        Unavailable_Bed.setMax(TotalCapacity);
        Unavailable_Bed.setProgress(UnavailableCapacity);
        Capacity_Unavailable_Bed_BtnM = root.findViewById(R.id.Capacity_Unavailable_Bed_BtnM);
        Capacity_Unavailable_Bed_BtnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UnavailableCapacity > 0){
                    if (UnoccupiedCapacity >= 0){
                        UnoccupiedCapacity++;
                        Capacity_UnOccupied_Bed_Count.setText(String.valueOf(UnoccupiedCapacity));
                        Unoccupied_Beds.setProgress(UnoccupiedCapacity);

                    }
                    UnavailableCapacity--;
                    Capacity_Unavailable_Bed_LabelCount.setText(String.valueOf(UnavailableCapacity));
                    Unavailable_Bed.setProgress(UnavailableCapacity);

                }else{
                    Toast toast = Toast.makeText(getContext(),"Cannot Be Less Than 0", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        Capacity_Unavailable_Bed_LabelCount = root.findViewById(R.id.Capacity_Unavailable_Bed_LabelCount);
        Capacity_Unavailable_Bed_LabelCount.setText(String.valueOf(UnavailableCapacity));
        Capacity_Unavailable_Bed_BtnP = root.findViewById(R.id.Capacity_Unavailable_Bed_BtnP);
        Capacity_Unavailable_Bed_BtnP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UnavailableCapacity < (TotalCapacity-CurrentCapacity)){
                    if (UnoccupiedCapacity > 0){
                        UnoccupiedCapacity--;
                        Capacity_UnOccupied_Bed_Count.setText(String.valueOf(UnoccupiedCapacity));
                        Unoccupied_Beds.setProgress(UnoccupiedCapacity);

                    }
                    UnavailableCapacity++;
                    Capacity_Unavailable_Bed_LabelCount.setText(String.valueOf(UnavailableCapacity));
                    Unavailable_Bed.setProgress(UnavailableCapacity);
                }else{
                    Toast toast = Toast.makeText(getContext(),"At Max Capacity", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });



        //Beds Occupied Section
        Occupied_Beds = root.findViewById(R.id.Capacity_Bed2);
        Occupied_Beds.setMax(TotalCapacity);
        Occupied_Beds.setProgress(CurrentCapacity);
        Capacity_Actual_Bed_BtnM = root.findViewById(R.id.Capacity_Actual_Bed_BtnM);
        Capacity_Actual_Bed_BtnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CurrentCapacity > 0){
                    CurrentCapacity--;
                    Capacity_Count.setText(String.valueOf(CurrentCapacity));
                    Occupied_Beds.setProgress(CurrentCapacity);

                    UnoccupiedCapacity++;
                    Capacity_UnOccupied_Bed_Count.setText(String.valueOf(UnoccupiedCapacity));
                    Unoccupied_Beds.setProgress(UnoccupiedCapacity);

                }else{
                    Toast toast = Toast.makeText(getContext(),"Cannot Be Less Than 0", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        Capacity_Count = root.findViewById(R.id.Capacity_Count);
        Capacity_Count.setText(String.valueOf(CurrentCapacity));
        Capacity_Actual_Bed_BtnP = root.findViewById(R.id.Capacity_Actual_Bed_BtnP);
        Capacity_Actual_Bed_BtnP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CurrentCapacity < (TotalCapacity - UnavailableCapacity)){
                    CurrentCapacity++;
                    Capacity_Count.setText(String.valueOf(CurrentCapacity));
                    Occupied_Beds.setProgress(CurrentCapacity);

                    if (UnoccupiedCapacity > 0){
                        UnoccupiedCapacity--;
                        Capacity_UnOccupied_Bed_Count.setText(String.valueOf(UnoccupiedCapacity));
                        Unoccupied_Beds.setProgress(UnoccupiedCapacity);

                    }
                }else{
                    Toast toast = Toast.makeText(getContext(),"At Max Capacity", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        CancelBtn = root.findViewById(R.id.CancelBtn);
        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
                navController.popBackStack();

            }
        });
        SaveBtn = root.findViewById(R.id.SaveBtn);
        SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shelter.setCapacityActualRoom(CurrentCapacity);
                shelter.setCapacityFundingRoom(TotalCapacity);
                shelter.setUnavailableRooms(UnavailableCapacity);
                shelter.setUnoccupiedRooms(UnoccupiedCapacity);
                shelter.setLocationName(String.valueOf(Name.getText()));
                shelter.setLocationAddress(String.valueOf(Address.getText()));
                shelter.setLocationCity(String.valueOf(City.getText()));
                shelter.setLocationPostalCode(String.valueOf(Postal.getText()));
                shelter.setProgramArea(String.valueOf(Group.getText()));
                shelter.setOrganizationName(String.valueOf(Org.getText()));
                shelter.setProgramName(String.valueOf(Program.getText()));
                shelter.setSector(String.valueOf(Sector.getText()));
                shelter.setOvernightServiceType(String.valueOf(Model.getText()));
                shelter.setCapacityType(String.valueOf(Capacity_Type.getText()));


                JSONObject jsonShelter = null;
                try {
                    jsonShelter = new JSONObject(new Gson().toJson(shelter));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                saveShelter(jsonShelter);
            }
        });

        return root;
    }

    private void saveShelter(JSONObject shelterJSON){
        Call<ResponseBody> call = RetrofitClient.getInstance().getMyApi().updateShelter(shelterJSON.toString());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    Log.d("Save Shelter", "Successful API Call");
                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
                    navController.popBackStack();

                }else{
                    Log.e("Save Shelter", "Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Save Shelter", "Error: " + t.getMessage());
            }
        });
    }

}