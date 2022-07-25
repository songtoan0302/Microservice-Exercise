package org.aibles.paymentservice.repository;

import org.aibles.paymentservice.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author toanns
 */
@Repository
public interface VoucherRepository extends JpaRepository<Voucher,String> {

}
