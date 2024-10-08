package za.ac.cput.factory;

import za.ac.cput.domain.Orders;
import za.ac.cput.domain.Users;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

/**
 * OrderFactory.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 26-Jul-24
 */

public class OrderFactory {

    public static Orders buildOrder(Users user, Double total_amount,
                                    LocalDate order_date, String status, String shipping_address,
                                    String billing_address, String payment_method,
                                    LocalDate created_at, LocalDate updated_at) {
        if (total_amount == null || order_date == null ||
                Helper.isNullOrEmpty(status) ||
                Helper.isNullOrEmpty(shipping_address) ||
                Helper.isNullOrEmpty(billing_address) ||
                Helper.isNullOrEmpty(payment_method)) {
            return null;
        }

        return new Orders.Builder()
                .setUser(user) // Pass the Users object instead of user_id
                .setTotal_amount(total_amount)
                .setOrder_date(order_date)
                .setStatus(status)
                .setShipping_address(shipping_address)
                .setBilling_address(billing_address)
                .setPayment_method(payment_method)
                .setCreated_at(created_at)
                .setUpdated_at(updated_at)
                .build();
    }
}
