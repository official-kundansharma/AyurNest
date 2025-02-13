package com.example.ayurnest;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<CartItem> cartItems;

    private CartManager() {
        cartItems = new ArrayList<>();
    }

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void addToCart(Product product) {
        CartItem item = findCartItem(product);
        if (item != null) {
            item.setQuantity(item.getQuantity() + 1);
        } else {
            cartItems.add(new CartItem(product, 1));
        }
    }

    private CartItem findCartItem(Product product) {
        for (CartItem item : cartItems) {
            if (item.getProduct().equals(product)) {
                return item;
            }
        }
        return null;
    }
}
