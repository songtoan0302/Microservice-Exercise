//package org.aibles.orderservice.model;
//
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "cart_detail")
//public class CartDetail {
//    @Id
//    @Column(name = "id")
//    private String id;
//    @OneToMany
//    @JoinColumn(name = "shopping_cart_id",referencedColumnName = "id")
//    private ShoppingCart shoppingCart;
//
//    public CartDetail() {
//    }
//
//    public CartDetail(String id, ShoppingCart shoppingCart) {
//        this.id = id;
//        this.shoppingCart = shoppingCart;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public ShoppingCart getShoppingCart() {
//        return shoppingCart;
//    }
//
//    public void setShoppingCart(ShoppingCart shoppingCart) {
//        this.shoppingCart = shoppingCart;
//    }
//}
