package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartControl {

    @Autowired
    private CartService cartService;
    
    @GetMapping
    public List<CartItem> getAllCartItems() {
        return cartService.getAllCartItems();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable int id) {
        Optional<CartItem> cartItem = cartService.getCartItemById(id);
        return cartItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<CartItem> addCartItem(@RequestBody CartItem cartItem) {
        return new ResponseEntity<>(cartService.addCartItem(cartItem), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable int id, @RequestBody CartItem cartItem) {
        return new ResponseEntity<>(cartService.updateCartItem(id, cartItem), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable int id) {
        cartService.deleteCartItem(id);
        return ResponseEntity.noContent().build();
    }
}