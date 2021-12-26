package com.uietkuk.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uietkuk.app.model.Announcement;

import java.util.ArrayList;

public class listAdapter extends RecyclerView.Adapter<listViewHolder> {
    @NonNull
    private ArrayList<Announcement> dataModelAnnouncements;

    public listAdapter(ArrayList<Announcement> dataModelAnnouncements){
        //this will get all the data from the api
        this.dataModelAnnouncements=dataModelAnnouncements;
    }
    @Override
    public listViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.announcementslist, parent, false);

        return new listViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull listViewHolder holder, int position) {

        Announcement currItem=dataModelAnnouncements.get(position);
        holder.getTxtDate().setText(currItem.getDate().toString());
        holder.getTxtDesc().setText(currItem.getName());


    }

    @Override
    public int getItemCount() {
        return dataModelAnnouncements.size();
    }


}
class listViewHolder extends RecyclerView.ViewHolder{

    //declare and initalize all the list items here

    private final TextView txtDate;
    private final TextView txtDesc;

    public listViewHolder(@NonNull View itemView) {
        //initalize them
        super(itemView);
        txtDate=itemView.findViewById(R.id.txtDate);
        txtDesc=itemView.findViewById(R.id.txtDesc);
    }

    public TextView getTxtDate() {
        return txtDate;
    }

    public TextView getTxtDesc() {
        return txtDesc;
    }


}
