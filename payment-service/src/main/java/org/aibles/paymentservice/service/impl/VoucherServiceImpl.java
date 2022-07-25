package org.aibles.paymentservice.service.impl;

import java.util.List;
import org.aibles.paymentservice.dto.VoucherDTO;
import org.aibles.paymentservice.model.Voucher;
import org.aibles.paymentservice.service.VoucherService;
import org.springframework.stereotype.Service;

/**
 * @author toanns
 */
@Service
public class VoucherServiceImpl implements VoucherService {

  @Override
  public List<Voucher> getVouchers(String condition) {
    return null;
  }

  @Override
  public VoucherDTO createVoucher(VoucherDTO voucherDTO) {
    return null;
  }

  @Override
  public VoucherDTO updateVoucher(String id, VoucherDTO voucherDTO) {
    return null;
  }

  @Override
  public VoucherDTO deleteVoucher(String id) {
    return null;
  }

  @Override
  public VoucherDTO getVoucher(String id) {
    return null;
  }
}
