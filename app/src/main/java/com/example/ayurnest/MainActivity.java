package com.example.ayurnest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ProductAdapter.OnAddToCartListener {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private TextView cartCount;
    private ImageView cartIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        setupRecyclerView();
        addSampleProducts();
        setupCartIconListener();
    }

    private void initializeViews() {
        recyclerView = findViewById(R.id.recyclerView);
        cartCount = findViewById(R.id.cartCount);
        cartIcon = findViewById(R.id.cartIcon);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(productList, this);
        recyclerView.setAdapter(productAdapter);
    }

    private void addSampleProducts() {
        // Add sample products (In a real app, this data would come from a database or API)
        productList.add(new Product("Ashwagandha", 299, R.drawable.product1));
        productList.add(new Product("Chyawanprash", 349, R.drawable.product2));
        productList.add(new Product("Triphala", 199, R.drawable.product3));
        productList.add(new Product("Brahmi", 249, R.drawable.product1));
        productList.add(new Product("Giloy", 179, R.drawable.product2));

        // Notify adapter about data change
        productAdapter.notifyDataSetChanged();
    }

    private void setupCartIconListener() {
        cartIcon.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        });
    }

    public void updateCartCount() {
        int totalItems = 0;
        for (CartItem item : CartManager.getInstance().getCartItems()) {
            totalItems += item.getQuantity();
        }
        cartCount.setText(String.valueOf(totalItems));
    }

    @Override
    public void onAddToCart(Product product) {
        // Add product to cart
        CartManager.getInstance().addToCart(product);
        updateCartCount();
        productAdapter.notifyDataSetChanged();
        Toast.makeText(this, product.getName() + " added to cart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCartCount();
        // Refresh product list
        productAdapter.notifyDataSetChanged();
    }

    public void onProductClick(Product product) {
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra("product", product);
        startActivity(intent);
    }
}