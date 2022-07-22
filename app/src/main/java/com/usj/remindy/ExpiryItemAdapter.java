package com.usj.remindy;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExpiryItemAdapter extends RecyclerView.Adapter<ExpiryItemAdapter.ModelViewHolder>{
    Context context;
    ArrayList<ExpiryItemModel> modelArrayList=new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;

    //genereate Constructor

    public ExpiryItemAdapter(Context context, int exp_item_design, ArrayList<ExpiryItemModel> modelArrayList, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.modelArrayList = modelArrayList;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public ExpiryItemAdapter.ModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.exp_item_design,null);

        return new ModelViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ExpiryItemAdapter.ModelViewHolder holder, int position) {
        final ExpiryItemModel model=modelArrayList.get(position);
        holder.ExtextViewName.setText(model.getItemName());
        holder.ExtextViewDesc.setText(model.getItemDesc());
        holder.ExtextViewDate.setText(model.getExpdate());



        //click button to go to ClinicReport Reminder
//        holder.btedit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Bundle bundle=new Bundle();
//                bundle.putInt("id",model.getId());
//                bundle.putString("name",model.getItemName());
//                bundle.putString("des",model.getItemDesc());
//                bundle.putString("date",model.getItemDesc());
//                Intent i = new Intent(context,ClinicReportReminder.class);
//                i.putExtra("expiredata",bundle);
//                context.startActivity(i);
//            }
//        });

//        holder.btdelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ExpireDateDatabaseHelper expireDateDatabaseHelper= new ExpireDateDatabaseHelper(context);
//                sqLiteDatabase=expireDateDatabaseHelper.getReadableDatabase();
//                long recdelete=sqLiteDatabase.delete("expire","id="+model.getId(),null);
//                if(recdelete!=-1){
//                    Toast.makeText(context,"Data Deleted!",Toast.LENGTH_LONG).show();
//                    modelArrayList.remove(position);
//                    notifyDataSetChanged();
//                }
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ModelViewHolder extends RecyclerView.ViewHolder {
        TextView ExtextViewName,ExtextViewDesc,ExtextViewDate;
        Button btedit,btdelete;
        public ModelViewHolder(@NonNull View itemView) {
            super(itemView);
            ExtextViewName=(TextView) itemView.findViewById(R.id.ExtextViewName);
            ExtextViewDesc=(TextView) itemView.findViewById(R.id.ExtextViewDesc);
            ExtextViewDate=(TextView) itemView.findViewById(R.id.ExtextViewDate);
            //btedit=(Button) itemView.findViewById(R.id.edit);
            //btdelete=(Button) itemView.findViewById(R.id.delete);

        }
    }
}