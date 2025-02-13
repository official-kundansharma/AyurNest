package com.example.ayurnest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    private LinearLayout cartContainer;
    private TextView cartTotal;
    private Button checkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartContainer = findViewById(R.id.cartContainer);
        cartTotal = findViewById(R.id.cartTotal);
        checkoutButton = findViewById(R.id.checkoutButton);

        loadCartItems();

        checkoutButton.setOnClickListener(v -> {
            Toast.makeText(this, "Proceeding to Checkout!", Toast.LENGTH_SHORT).show();
        });
    }

    private void loadCartItems() {
        cartContainer.removeAllViews();
        List<CartItem> cartItems = CartManager.getInstance().getCartItems();
        double totalPrice = 0;

        for (CartItem item : cartItems) {
            View itemView = getLayoutInflater().inflate(R.layout.item_cart, cartContainer, false);
            TextView productName = itemView.findViewById(R.id.cartProductName);
            TextView productPrice = itemView.findViewById(R.id.cartProductPrice);
            TextView quantityText = itemView.findViewById(R.id.cartQuantity);
            Button increaseBtn = itemView.findViewById(R.id.btnIncrease);
            Button decreaseBtn = itemView.findViewById(R.id.btnDecrease);
            ImageView productImage = itemView.findViewById(R.id.cartProductImage);

            productName.setText(item.getProduct().getName());
            productPrice.setText("₹" + item.getProduct().getPrice());
            quantityText.setText(String.valueOf(item.getQuantity()));

            productImage.setImageResource(item.getProduct().getImageResource());


            // Increase button action
            increaseBtn.setOnClickListener(v -> {
                item.incrementQuantity();
                quantityText.setText(String.valueOf(item.getQuantity()));
                updateTotalPrice();
            });

// Decrease button action
            decreaseBtn.setOnClickListener(v -> {
                item.decrementQuantity();
                quantityText.setText(String.valueOf(item.getQuantity()));
                updateTotalPrice();
            });


            cartContainer.addView(itemView);
            totalPrice += item.getProduct().getPrice() * item.getQuantity();
        }

        cartTotal.setText("Total: ₹" + totalPrice);
    }

    private void updateTotalPrice() {
        double totalPrice = 0;
        List<CartItem> cartItems = CartManager.getInstance().getCartItems();

        for (CartItem item : cartItems) {
            totalPrice += item.getProduct().getPrice() * item.getQuantity();
        }

        // Update the total price
        cartTotal.setText("Total: ₹" + totalPrice);
    }

}