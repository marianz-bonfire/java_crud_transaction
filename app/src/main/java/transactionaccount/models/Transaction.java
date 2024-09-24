/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transactionaccount.models;

import java.util.Date;

/**
 *
 * @author Tarsier
 */
public class Transaction {
    private int id;
    private String chkNumber;
    private Date date;
    private String memo;
    private String splits;
    private double debit;
    private double credit;
    private double balance;
    private String createdBy;
    private String lastEditedBy;
    private char clearFlag;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the chkNumber
     */
    public String getChkNumber() {
        return chkNumber;
    }

    /**
     * @param chkNumber the chkNumber to set
     */
    public void setChkNumber(String chkNumber) {
        this.chkNumber = chkNumber;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @param memo the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * @return the splits
     */
    public String getSplits() {
        return splits;
    }

    /**
     * @param splits the splits to set
     */
    public void setSplits(String splits) {
        this.splits = splits;
    }

    /**
     * @return the debit
     */
    public double getDebit() {
        return debit;
    }

    /**
     * @param debit the debit to set
     */
    public void setDebit(double debit) {
        this.debit = debit;
    }

    /**
     * @return the credit
     */
    public double getCredit() {
        return credit;
    }

    /**
     * @param credit the credit to set
     */
    public void setCredit(double credit) {
        this.credit = credit;
    }

    /**
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the lastEditedBy
     */
    public String getLastEditedBy() {
        return lastEditedBy;
    }

    /**
     * @param lastEditedBy the lastEditedBy to set
     */
    public void setLastEditedBy(String lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
    }

    /**
     * @return the clearFlag
     */
    public char getClearFlag() {
        return this.clearFlag;
    }

    /**
     * @param clearFlag the clearFlag to set
     */
    public void setClearFlag(char clearFlag) {
        this.clearFlag = clearFlag;
    }
}
