package model.internal;

import java.util.ArrayList;

public class Account {

    private int balance;
    private final int accountNumber;
    private ArrayList<Integer> transactions;

    public Account(int balance, int accountNumber){
        this.balance = balance;
        this.accountNumber = accountNumber;
        transactions = new ArrayList<>();
    }

    public Account(int balance, int accountNumber, ArrayList<Integer> transactions){
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.transactions = transactions;
    }

    public int getBalance() {
        return balance;
    }

    public void modifyBalance(int amount){

        balance = balance + amount;
        transactions.add(amount);
    }

    public ArrayList<Integer> getTransactions() {
        return transactions;
    }
}
