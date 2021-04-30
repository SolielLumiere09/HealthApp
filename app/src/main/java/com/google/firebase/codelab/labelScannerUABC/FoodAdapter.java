package com.google.firebase.codelab.labelScannerUABC;

/*
*   Adaptador para el RecyclerView que despliega los alimentos almacenados localmente, mayormente boilerplate
*
*/

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.codelab.labelScannerUABC.Class.FoodItem;
import com.google.firebase.codelab.mlkitUABC.NutrientsActivity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import static android.view.View.GONE;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    List<FoodItem> FoodList;
    FoodItem foodItem;
    Context context;

    public FoodAdapter(List<FoodItem> FoodList)
    {
        this.FoodList = FoodList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        foodItem = FoodList.get(position);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(foodItem.getDateAdded());

        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        int mHour = calendar.get(Calendar.HOUR_OF_DAY);
        int mMinute = calendar.get(Calendar.MINUTE);
        String formattedMinute = String.valueOf(mMinute);

        if (mMinute < 10)
            formattedMinute = "0"+String.valueOf(mMinute);

        holder.nameTextView.setText(foodItem.getProduct_name());
        holder.sizeTextView.setText(foodItem.getPortion_size() * foodItem.getPortions()  + " gr/ml");
        holder.dateAdded.setText(mDay + "/" + mMonth + "/" +mYear + "  " + mHour + ":" + formattedMinute);
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper mydb = new DBHelper(context);
                System.out.println(FoodList);
                Toast.makeText(context,"El producto " + FoodList.get(position).getProduct_name() + " fue borrado." ,Toast.LENGTH_SHORT).show();
                mydb.deleteFood(FoodList.get(position).getId());
                holder.cv.setVisibility(GONE);
            }
        });

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(FoodList);
                Intent intent = new Intent(context, NutrientsActivity.class);
                intent.putExtra("foodItem", (Serializable) FoodList.get(position));
                System.out.println(foodItem.getProduct_name());
                context.startActivity(intent);
                //Toast.makeText(context,"The position is:"+position,Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return FoodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView nameTextView;
        TextView sizeTextView;
        TextView dateAdded;
        ImageButton deleteButton;
        CardView cv;

        public ViewHolder(View itemView)
        {
            super(itemView);
            nameTextView = (TextView)itemView.findViewById(R.id.nameTextView);
            sizeTextView = (TextView)itemView.findViewById(R.id.sizeTextView);
            dateAdded = (TextView)itemView.findViewById(R.id.dateAdded);
            deleteButton = (ImageButton)itemView.findViewById(R.id.deleteButton);
            cv = (CardView)itemView.findViewById(R.id.cv);
        }

    }
}