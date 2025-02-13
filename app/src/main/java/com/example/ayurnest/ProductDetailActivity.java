package com.example.ayurnest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView productImage;
    private TextView productName, productPrice, productDescription;
    private Button addToCartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        initializeViews();
        Product product = (Product) getIntent().getSerializableExtra("product");
        setProductDetails(product);

        addToCartButton.setOnClickListener(view -> {
            CartManager.getInstance().addToCart(product);
            Toast.makeText(this, product.getName() + " added to cart", Toast.LENGTH_SHORT).show();
            finish();  // Optionally finish this activity after adding to cart
        });
    }

    private void initializeViews() {
        productImage = findViewById(R.id.productDetailImage);
        productName = findViewById(R.id.productDetailName);
        productPrice = findViewById(R.id.productDetailPrice);
        productDescription = findViewById(R.id.productDetailDescription);
        addToCartButton = findViewById(R.id.addToCartButton);
    }

    private void setProductDetails(Product product) {
        if (product != null) {
            productImage.setImageResource(product.getImageResource());
            productName.setText(product.getName());
            productPrice.setText("₹" + product.getPrice());
            productDescription.setText("Description: " + product.getDescription());
        }
    }
}
