package com.azubike.ellipsis.hibernate_example;

import com.azubike.ellipsis.hibernate_example.annotaions.Column;
import com.azubike.ellipsis.hibernate_example.annotaions.PrimaryKey;

public class TransactionHistory {
    @PrimaryKey
    private int transactionId;
    @Column
    private int accountNumber;
    @Column
    private String name;
    @Column
    private String transactionType;
    @Column
    private int amount;

    public TransactionHistory() {
    }

    public TransactionHistory(final int accountNumber, final String name, final String transactionType, final int amount) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(final int transactionId) {
        this.transactionId = transactionId;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(final int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(final String transactionType) {
        this.transactionType = transactionType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(final int amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        return "TransactionHistory{" +
                "transactionId=" + transactionId +
                ", accountNumber=" + accountNumber +
                ", name='" + name + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                '}';
    }
}
