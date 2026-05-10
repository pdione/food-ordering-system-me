package com.food.ordering.system.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money {

    private final BigDecimal amount;

    public static final Money ZERO = new Money(BigDecimal.ZERO);

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public boolean isGreaterThanZero() {
        return this.amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isGreaterThan(Money other) {
        return this.amount != null && other.amount != null && amount.compareTo(other.amount) > 0;
    }

    public Money add(Money other) {
        return new Money(setScale(this.amount.add(other.amount)));
    }

    public Money subtract(Money other) {
        return new Money(setScale(this.amount.subtract(other.amount)));
    }

    public Money multiply(int factor) {
        return new Money(setScale(this.amount.multiply(BigDecimal.valueOf(factor))));
    }

    private BigDecimal setScale(BigDecimal input) {
        return input.setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }
}
