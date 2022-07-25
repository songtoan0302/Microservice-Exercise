package org.aibles.paymentservice.service;

import java.util.List;
import org.aibles.paymentservice.dto.VoucherDTO;
import org.aibles.paymentservice.model.Voucher;

/**
 * @author toanns
 */
public interface VoucherService {

  List<Voucher> getVouchers(String condition);

  VoucherDTO createVoucher(VoucherDTO voucherDTO);

  VoucherDTO updateVoucher(String id, VoucherDTO voucherDTO);

  VoucherDTO deleteVoucher(String id);

  VoucherDTO getVoucher(String id);
}
