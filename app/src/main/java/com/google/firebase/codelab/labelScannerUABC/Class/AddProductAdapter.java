package com.google.firebase.codelab.labelScannerUABC.Class;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.codelab.UI.DiaryActivity;
import com.google.firebase.codelab.labelScannerUABC.R;

import java.util.ArrayList;

public class AddProductAdapter extends RecyclerView.Adapter {

    ArrayList<FoodItem> products;
    Context context;
    ConsumedCalories consumedCalories;

    public AddProductAdapter(ArrayList<FoodItem> products, Context context, ConsumedCalories consumedCalories) {
        this.products = products;
        this.context = context;
        this.consumedCalories = consumedCalories;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addproduct, parent, false);
        AddProductHolder productHolder = new AddProductHolder(view);
        return productHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AddProductHolder productHolder = (AddProductHolder)holder;
        productHolder.productName.setText(products.get(position).getProduct_name());
        productHolder.productCalories.setText(String.valueOf( (int)products.get(position).getCalories())+" calorias");

        ((AddProductHolder) holder).btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumedCalories.addCalories(products.get(position));
                CaloriesLoader.writeConsumedCalories(context.getApplicationContext(), consumedCalories);
                Toast.makeText(context, String.valueOf(consumedCalories.getCalories()), Toast.LENGTH_LONG).show();
            }
        });
        /*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consumedCalories.addCalories(products.get(position));
                CaloriesLoader.writeConsumedCalories(context.getApplicationContext(), consumedCalories);
                Toast.makeText(view.getContext(), String.valueOf(consumedCalories.getCalories()), Toast.LENGTH_LONG).show();
            }
        });

         */
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class AddProductHolder extends RecyclerView.ViewHolder{
        TextView productName;
        TextView productCalories;
        Button btn;
        public AddProductHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.add_productNameTextView);
            productCalories = itemView.findViewById(R.id.add_productCaloriesTextView);
            btn = itemView.findViewById(R.id.add_button);
        }
    }
}