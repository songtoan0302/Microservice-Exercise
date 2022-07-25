package org.aibles.paymentservice.constant;

/**
 * @author toanns
 */
public enum TypeVoucher {
  //discount voucher
DISCOUNT_VOUCHER(0,"Discount voucher");

private int code;
private String message;

  TypeVoucher(int code, String message) {
    this.code = code;
    this.message = message;
  }
}
