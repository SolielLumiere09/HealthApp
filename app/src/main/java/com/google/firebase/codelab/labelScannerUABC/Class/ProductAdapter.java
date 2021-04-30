package com.google.firebase.codelab.labelScannerUABC.Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.codelab.labelScannerUABC.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter {

    ArrayList<String> productNames;
    Context context;

    public ProductAdapter(ArrayList<String> productNames, Context context) {
        this.productNames = productNames;
        this.context = context;
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
         productHolder.productName.setText(productNames.get(position));
         holder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Toast.makeText(view.getContext(), productNames.get(position), Toast.LENGTH_LONG).show();
             }
         });
    }

    @Override
    public int getItemCount() {
        return productNames.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {

        TextView productName;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productNameTextView);
        }
    }
}