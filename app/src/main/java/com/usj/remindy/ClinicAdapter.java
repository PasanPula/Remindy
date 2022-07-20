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

public class ClinicAdapter extends RecyclerView.Adapter<ClinicAdapter.ModelViewHolder> {
    Context context;
    ArrayList<ClinicDataModel>modelArrayList=new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;

    //genereate Constructor

    public ClinicAdapter(Context context, int clinicdatasingle, ArrayList<ClinicDataModel> modelArrayList, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.modelArrayList = modelArrayList;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public ClinicAdapter.ModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.clinicdatasingle,null);

        return new ModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClinicAdapter.ModelViewHolder holder, int position) {
        final ClinicDataModel model=modelArrayList.get(position);
        holder.txtdetail.setText(model.getDetail());
        holder.txthospital.setText(model.getHospital());
        holder.txtdoctor.setText(model.getDoctor());
        holder.txtdate.setText(model.getDate());
        holder.txttime.setText(model.getTime());

        //click button to go to ClinicReport Reminder
        holder.btedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putInt("id",model.getId());
                bundle.putString("detail",model.getDetail());
                bundle.putString("hospital",model.getHospital());
                bundle.putString("doctor",model.getDoctor());
                bundle.putString("date",model.getDate());
                bundle.putString("time",model.getTime());
                Intent i = new Intent(context,ClinicReportReminder.class);
                i.putExtra("clinicdata",bundle);
                context.startActivity(i);
            }
        });

        holder.btdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClinicReportDatabaseHelper clinicReportDatabaseHelper= new ClinicReportDatabaseHelper(context);
                sqLiteDatabase=clinicReportDatabaseHelper.getReadableDatabase();
                long recdelete=sqLiteDatabase.delete("clinic","id="+model.getId(),null);
                if(recdelete!=-1){
                    Toast.makeText(context,"Data Deleted!",Toast.LENGTH_LONG).show();
                    modelArrayList.remove(position);
                    notifyDataSetChanged();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ModelViewHolder extends RecyclerView.ViewHolder {
        TextView txtdetail,txthospital,txtdoctor,txtdate,txttime;
        Button btedit,btdelete;
        public ModelViewHolder(@NonNull View itemView) {
            super(itemView);
            txtdetail=(TextView) itemView.findViewById(R.id.Detailsingle);
            txthospital=(TextView) itemView.findViewById(R.id.Hospitalsingle);
            txtdoctor=(TextView) itemView.findViewById(R.id.Doctorsingle);
            txtdate=(TextView) itemView.findViewById(R.id.Datesingle);
            txttime=(TextView) itemView.findViewById(R.id.Timesingle);
            btedit=(Button) itemView.findViewById(R.id.edit);
            btdelete=(Button) itemView.findViewById(R.id.delete);

        }
    }
}
