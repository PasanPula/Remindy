package com.usj.remindy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExpiryItemAdapter extends RecyclerView.Adapter<ExpiryItemAdapter.ExpireViewHolder>{

    private List<ExpiryItemModel> ExpireItemList;
    public ExpiryItemAdapter(List<ExpiryItemModel> list)
    {
        this.ExpireItemList =list;
    }

    @NonNull
    @Override
    public ExpiryItemAdapter.ExpireViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exp_item_design,parent,false);
        return new ExpireViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpiryItemAdapter.ExpireViewHolder holder, int position) {
        final ExpiryItemModel model=ExpireItemList.get(position);
        String ItemName = model.getItemName();
        String ItemDescrption = model.getItemDesc();
        String Expdate = model.getExpdate();

        holder.setData(ItemName,ItemDescrption,Expdate);
    }

    @Override
    public int getItemCount() {
        return  ExpireItemList.size();
    }

    public class ExpireViewHolder extends RecyclerView.ViewHolder
    {
        private TextView name;
        private TextView desc;
        private TextView date;

        public ExpireViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ExtextViewName);
            desc = itemView.findViewById(R.id.ExtextViewDesc);
            date = itemView.findViewById(R.id.ExtextViewDate);
        }

        public void setData(String itemName, String itemDescrption, String expdate) {
            name.setText(itemName);
            desc.setText(itemDescrption);
            date.setText(expdate);
        }
    }


}


