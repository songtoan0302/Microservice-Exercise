//package org.aibles.orderservice.model;
//
//import javax.persistence.Column;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
//
//public class Order {
//    @Column(name = "id")
//    private String id;
//    @OneToOne
//    @JoinColumn(name = "cart_detail_id" ,referencedColumnName = "id")
//    private CartDetail cartDetail;
//    private boolean statusPayment;
//}
