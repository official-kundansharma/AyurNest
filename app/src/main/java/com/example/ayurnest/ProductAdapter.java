package com.example.ayurnest;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> productList;
    private OnAddToCartListener addToCartListener;

    public interface OnAddToCartListener {
        void onAddToCart(Product product);
    }

    public ProductAdapter(List<Product> productList, OnAddToCartListener listener) {
        this.productList = productList;
        this.addToCartListener = listener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;
        private TextView productName, productPrice;
        private Button addToCartButton;

        public ProductViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image); // Fixed ID reference
            productName = itemView.findViewById(R.id.product_name); // Fixed ID reference
            productPrice = itemView.findViewById(R.id.product_price); // Fixed ID reference
            addToCartButton = itemView.findViewById(R.id.add_to_cart_button); // Fixed ID reference

            addToCartButton.setOnClickListener(view -> {
                Product product = productList.get(getAdapterPosition());
                addToCartListener.onAddToCart(product);
            });

            itemView.setOnClickListener(view -> {
                Product product = productList.get(getAdapterPosition());
                Intent intent = new Intent(view.getContext(), ProductDetailActivity.class);
                intent.putExtra("product", product);
                view.getContext().startActivity(intent);
            });
        }

        public void bind(Product product) {
            productImage.setImageResource(product.getImageResource());
            productName.setText(product.getName());
            productPrice.setText("₹" + product.getPrice());
        }
    }
}
