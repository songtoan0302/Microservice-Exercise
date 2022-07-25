package org.aibles.paymentservice.repository;

import java.util.UUID;
import org.aibles.paymentservice.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author toanns
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, String> {

}
