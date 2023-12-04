package com.example.prj666shelterdashboard.ui.gallery;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.example.prj666shelterdashboard.R;
import com.example.prj666shelterdashboard.ui.Shelter;
import com.example.prj666shelterdashboard.ui.home.ShelterAdapter;

public class ShelterListAdapter extends RecyclerView.Adapter<ShelterListAdapter.ViewHolder> {
    private List<Shelter> shelter;
    private OnItemClickListener listener;

    public ShelterListAdapter(List<Shelter> shelter, OnItemClickListener onItemClickListener) {
        this.shelter = shelter;
        this.listener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shelter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Shelter shelter = this.shelter.get(position);
        holder.bind(shelter);
    }

    @Override
    public int getItemCount() {
        return shelter.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textOrganizationName;
        private TextView textLocationAddress;
        private TextView textLocationCity;
        private TextView textCapacityFundingRoom,textDescription;
        private Button buttonMoreInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textOrganizationName = itemView.findViewById(R.id.textShelterName);
            textLocationAddress = itemView.findViewById(R.id.textAddress);
            textLocationCity = itemView.findViewById(R.id.textCity);
            textCapacityFundingRoom = itemView.findViewById(R.id.textBedsAvailable);
            buttonMoreInfo = itemView.findViewById(R.id.buttonMoreInfo);
            textDescription = itemView.findViewById(R.id.textDescription);
            buttonMoreInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

        public void bind(Shelter shelter) {
            textOrganizationName.setText(shelter.getLocationName());
            textLocationAddress.setText(shelter.getLocationAddress());
            textDescription.setText(shelter.getProgramArea());
            textLocationCity.setText(", " + shelter.getLocationCity());
            textCapacityFundingRoom.setText("Funded Capacity: " + shelter.getCapacityFundingRoom());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
