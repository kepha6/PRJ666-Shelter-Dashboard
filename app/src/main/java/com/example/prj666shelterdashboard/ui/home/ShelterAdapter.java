package com.example.prj666shelterdashboard.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prj666shelterdashboard.R;

import java.util.List;

public class ShelterAdapter extends RecyclerView.Adapter<ShelterAdapter.ViewHolder> {

    private List<Shelter> shelters;
    private OnItemClickListener listener;

    public ShelterAdapter(List<Shelter> shelters, OnItemClickListener listener) {
        this.shelters = shelters;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shelter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Shelter shelter = shelters.get(position);
        holder.bind(shelter);
    }

    @Override
    public int getItemCount() {
        return shelters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textShelterName;
        private TextView textAddress;
        private TextView textCity;
        private TextView textBedsAvailable;
        private Button buttonMoreInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textShelterName = itemView.findViewById(R.id.textShelterName);
            textAddress = itemView.findViewById(R.id.textAddress);
            textCity = itemView.findViewById(R.id.textCity);
            textBedsAvailable = itemView.findViewById(R.id.textBedsAvailable);
            buttonMoreInfo = itemView.findViewById(R.id.buttonMoreInfo);

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
            textShelterName.setText(shelter.getName());
            textAddress.setText(shelter.getAddress());
            textCity.setText(shelter.getCity());
            textBedsAvailable.setText("Beds Available: " + shelter.getBedsAvailable());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
