package com.google.firebase.codelab.labelScannerUABC.Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.codelab.labelScannerUABC.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter {

    ArrayList<FoodItem> products;
    Context context;
    ConsumedCalories consumedCalories;
    private boolean clickable = false;
    private TextView consumedCaloriesTV, remainingCaloriesTV;
    private int dailyCalories;

    public ProductAdapter(ArrayList<FoodItem> products, Context context) {
        this.products = products;
        this.context = context;
    }

    public ProductAdapter(ArrayList<FoodItem> products, Context context, ConsumedCalories consumedCalories, TextView consumedCaloriesTV, TextView remainingCaloriesTV, int dailyCalories) {
        clickable = true;
        this.products = products;
        this.context = context;
        this.consumedCalories = consumedCalories;
        this.consumedCaloriesTV = consumedCaloriesTV;
        this.remainingCaloriesTV = remainingCaloriesTV;
        this.dailyCalories = dailyCalories;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product, parent, false);
        ProductHolder productHolder = new ProductHolder(view);
        return productHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
         ProductHolder productHolder = (ProductHolder)holder;
         productHolder.productName.setText(products.get(position).getProduct_name());
         productHolder.productCalories.setText(String.valueOf( (int)products.get(position).getCalories())+" calorias");
         if(clickable) {
             holder.itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {

                 }
             });
         }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder {

        TextView productName;
        TextView productCalories;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productNameTextView);
            productCalories = itemView.findViewById(R.id.productCaloriesTextView);



        }
    }

    class AddProductHolder extends RecyclerView.ViewHolder{
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