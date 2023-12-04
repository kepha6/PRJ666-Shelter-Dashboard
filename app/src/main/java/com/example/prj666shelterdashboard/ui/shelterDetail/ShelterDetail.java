package com.example.prj666shelterdashboard.ui.shelterDetail;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.prj666shelterdashboard.R;
import com.example.prj666shelterdashboard.databinding.FragmentShelterDetailBinding;
import com.example.prj666shelterdashboard.databinding.FragmentSlideshowBinding;
import com.example.prj666shelterdashboard.ui.Shelter;
import com.example.prj666shelterdashboard.ui.slideshow.SlideshowViewModel;

import org.w3c.dom.Text;

public class ShelterDetail extends Fragment {

    private Shelter shelter;
    private FragmentShelterDetailBinding binding;
    TextView ID,Name,Address,Postal,City,Group,Org,Program,Sector,Model,Capacity_Type,Actual_Beds,Funding_Bed_Label,Capacity_Unoccupied_Bed,Capacity_Unavailable_Bed_Label2;
    ProgressBar Funding_Beds,Occupied_Beds,Unoccupied_Beds,Unavailable_Bed;

    Button DeleteBtn;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ShelterDetailViewModel shelterDetailViewModel =
                new ViewModelProvider(this).get(ShelterDetailViewModel.class);

        binding = FragmentShelterDetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Bundle b = getArguments();
        shelter = b.getParcelable("Shelter");

        ID = root.findViewById(R.id.LocationID);
        Name = root.findViewById(R.id.LocationName);
        Address = root.findViewById(R.id.ShelterLocationAddress);
        Postal = root.findViewById(R.id.LocationPostalCode);
        City = root.findViewById(R.id.LocationCity);
        Group = root.findViewById(R.id.ShelterGroup);
        Org = root.findViewById(R.id.OrganizationName);
        Program = root.findViewById(R.id.ProgramName);
        Sector = root.findViewById(R.id.Sector);
        Model = root.findViewById(R.id.ProgramModel);
        Capacity_Type = root.findViewById(R.id.CapacityType);
        Actual_Beds = root.findViewById(R.id.Capacity_Actual_Bed);
        Funding_Bed_Label = root.findViewById(R.id.Capacity_Funding_Bed_Label);
        Funding_Beds = root.findViewById(R.id.Capacity_Funding_Bed);
        Unoccupied_Beds = root.findViewById(R.id.Capacity_Occupied_Bed);
        Capacity_Unoccupied_Bed = root.findViewById(R.id.Capacity_Unoccupied_Bed);
        Unavailable_Bed = root.findViewById(R.id.Capacity_Unavailable_Bed);
        Capacity_Unavailable_Bed_Label2 = root.findViewById(R.id.Capacity_Unavailable_Bed_Label2);

        DeleteBtn = root.findViewById(R.id.DeleteBtn);
        DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ID.setText("ID:"+ String.valueOf(shelter.getShelterId()));
        Name.setText(shelter.getLocationName());
        Address.setText(shelter.getLocationAddress());
        Postal.setText(", " + shelter.getLocationPostalCode());
        City.setText(", " + shelter.getLocationCity());
        Group.setText(shelter.getProgramArea());
        Org.setText(shelter.getOrganizationName() + " | ");
        Program.setText(shelter.getProgramName());
        Sector.setText(shelter.getSector());
        Model.setText(shelter.getOvernightServiceType());
        Capacity_Type.setText(shelter.getCapacityType());

        Capacity_Unoccupied_Bed.setText(String.valueOf(shelter.getUnoccupiedRooms()));
        Actual_Beds.setText(String.valueOf(shelter.getCapacityActualRoom()));
        Funding_Bed_Label.setText(String.valueOf(shelter.getCapacityFundingRoom()));
        Capacity_Unavailable_Bed_Label2.setText(String.valueOf(shelter.getUnavailableRooms()));

        Funding_Beds.setMax(shelter.getCapacityFundingRoom());
        Funding_Beds.setProgress(shelter.getCapacityActualRoom());


        Unoccupied_Beds.setMax(shelter.getCapacityFundingRoom());
        Unoccupied_Beds.setProgress(shelter.getUnoccupiedRooms());


        //Unoccupied_Beds.setProgress(shelter.getUnoccupiedRooms());

        Unavailable_Bed.setMax(shelter.getCapacityFundingRoom());
        Unavailable_Bed.setProgress(shelter.getUnavailableRooms());

        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}