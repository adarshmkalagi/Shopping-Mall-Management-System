package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartService {
    
    @Autowired
    private CartRepo cartRepository;
    
    public List<CartItem> getAllCartItems() {
        return cartRepository.findAll();
    }

    public Optional<CartItem> getCartItemById(int itemId) {
        return cartRepository.findById(itemId);
    }

    public CartItem addCartItem(CartItem cartItem) {
        return cartRepository.save(cartItem);
    }

    public CartItem updateCartItem(int itemId, CartItem cartItem) {
        cartItem.setCartId(itemId);
        return cartRepository.save(cartItem);
    }
    
    public void deleteCartItem(int itemId) {
        cartRepository.deleteById(itemId);
    }
}