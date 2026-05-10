package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.entity.BaseEntity;
import com.food.ordering.system.order.service.domain.valueobject.OrderItemId;
import com.food.ordering.system.valueobject.Money;
import com.food.ordering.system.valueobject.OrderId;

public class OrderItem extends BaseEntity<OrderItemId> {

    private OrderId orderId;
    private final Product product;
    private final int quantity;
    private final Money price;
    private final Money subTotal;

    private OrderItem(Builder builder) {
        product = builder.product;
        quantity = builder.quantity;
        price = builder.price;
        subTotal = builder.subTotal;
        super.setId(builder.id);
    }

    boolean isPriceValid() {
        return price != null && price.isGreaterThanZero()
                && price.equals(product.getPrice())
                && price.multiply(quantity).equals(subTotal);
    }

    void initializeOrderItem(OrderId orderId, OrderItemId orderItemId) {
        this.orderId = orderId;
        super.setId(orderItemId);
    }


    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getPrice() {
        return price;
    }

    public Money getSubTotal() {
        return subTotal;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Product product;
        private int quantity;
        private Money price;
        private Money subTotal;
        private OrderItemId id;

        private Builder() {
        }

        public Builder product(Product val) {
            product = val;
            return this;
        }

        public Builder quantity(int val) {
            quantity = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder subTotal(Money val) {
            subTotal = val;
            return this;
        }

        public Builder orderItemId(OrderItemId val) {
            id = val;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
