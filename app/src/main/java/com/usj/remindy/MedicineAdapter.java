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

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.ModelViewHolder> {
    Context context;
    ArrayList<MedicineDataModel> modelArrayList = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;

    //generate constrcutor
    public MedicineAdapter(Context context, int medicinedatasingle, ArrayList<MedicineDataModel> modelArrayList, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.modelArrayList = modelArrayList;
        this.sqLiteDatabase = sqLiteDatabase;
    }


    @NonNull
    @Override
    public MedicineAdapter.ModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.medicinedatasingle, null);//have to create datasingle

        return new ModelViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MedicineAdapter.ModelViewHolder holder, int position) {
        final MedicineDataModel model = modelArrayList.get(position);
        holder.txttype.setText(model.getType());
        holder.txtname.setText(model.getMedicine());
        holder.txtdose.setText(model.getDose());
        holder.txttime.setText(model.getTime());


        //click button to go to ClinicReport Reminder
        holder.btedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", model.getId());
                bundle.putString("type", model.getType());
                bundle.putString("medicine", model.getMedicine());
                bundle.putString("dose", model.getDose());
                bundle.putString("time", model.getTime());
                Intent i = new Intent(context, MedicineReminder.class);
                i.putExtra("medicinedata", bundle);
                context.startActivity(i);
            }
        });

        holder.btdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MedicineReminderDatabaseHelper medicineReminderDatabaseHelper = new MedicineReminderDatabaseHelper(context);
                sqLiteDatabase = medicineReminderDatabaseHelper.getReadableDatabase();
                long recdelete = sqLiteDatabase.delete("medicine", "id=" + model.getId(), null);
                if (recdelete != -1) {
                    Toast.makeText(context, "Data Deleted!", Toast.LENGTH_LONG).show();
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
        TextView txttype, txtname, txtdose, txttime;
        Button btedit, btdelete;

        public ModelViewHolder(@NonNull View itemView) {
            super(itemView);
            txttype = (TextView) itemView.findViewById(R.id.Detailsingle);//have to change R.id
            txtname = (TextView) itemView.findViewById(R.id.Hospitalsingle);
            txtdose = (TextView) itemView.findViewById(R.id.Doctorsingle);
            txttime = (TextView) itemView.findViewById(R.id.Timesingle);
            btedit = (Button) itemView.findViewById(R.id.edit);
            btdelete = (Button) itemView.findViewById(R.id.delete);

        }
    }
}
