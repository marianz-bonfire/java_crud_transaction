package transactionaccount.controllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import transactionaccount.core.DatabaseConnection;
import transactionaccount.models.Transaction;

/**
 *
 * @author Tarsier
 */
public class TransactionController {

    private Connection connection;

    public TransactionController() {
        this.connection = DatabaseConnection.getConnection();
    }

    // Create a new transaction
    public void addTransaction(Transaction transaction) throws SQLException {
        String query = "INSERT INTO transactions (chk_number, date, memo, splits, debit, credit, balance, created_by, last_edited_by, clear_flag) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, transaction.getChkNumber());
        stmt.setDate(2, new java.sql.Date(transaction.getDate().getTime()));
        stmt.setString(3, transaction.getMemo());
        stmt.setString(4, transaction.getSplits());
        stmt.setDouble(5, transaction.getDebit());
        stmt.setDouble(6, transaction.getCredit());
        stmt.setDouble(7, transaction.getBalance());
        stmt.setString(8, transaction.getCreatedBy());
        stmt.setString(9, transaction.getLastEditedBy());
        stmt.setString(10, String.valueOf(transaction.getClearFlag()));
        stmt.executeUpdate();
    }

    // Retrieve all transactions
    public List<Transaction> getAllTransactions() throws SQLException {
        String query = "SELECT * FROM transactions";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        List<Transaction> transactions = new ArrayList<>();
        while (rs.next()) {
            Transaction transaction = new Transaction();
            transaction.setId(rs.getInt("id"));
            transaction.setChkNumber(rs.getString("chk_number"));
            transaction.setDate(rs.getDate("date"));
            transaction.setMemo(rs.getString("memo"));
            transaction.setSplits(rs.getString("splits"));
            transaction.setDebit(rs.getDouble("debit"));
            transaction.setCredit(rs.getDouble("credit"));
            transaction.setBalance(rs.getDouble("balance"));
            transaction.setCreatedBy(rs.getString("created_by"));
            transaction.setLastEditedBy(rs.getString("last_edited_by"));
            transaction.setClearFlag(rs.getString("clear_flag").charAt(0));
            transactions.add(transaction);
        }
        return transactions;
    }

    // Update a transaction
    public void updateTransaction(Transaction transaction) throws SQLException {
        String query = "UPDATE transactions SET chk_number=?, date=?, memo=?, splits=?, debit=?, credit=?, balance=?, created_by=?, last_edited_by=?, clear_flag=? WHERE id=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, transaction.getChkNumber());
        stmt.setDate(2, new java.sql.Date(transaction.getDate().getTime()));
        stmt.setString(3, transaction.getMemo());
        stmt.setString(4, transaction.getSplits());
        stmt.setDouble(5, transaction.getDebit());
        stmt.setDouble(6, transaction.getCredit());
        stmt.setDouble(7, transaction.getBalance());
        stmt.setString(8, transaction.getCreatedBy());
        stmt.setString(9, transaction.getLastEditedBy());
        stmt.setString(10, String.valueOf(transaction.getClearFlag()));
        stmt.setInt(11, transaction.getId());
        stmt.executeUpdate();
    }

    // Delete a transaction
    public void deleteTransaction(int id) throws SQLException {
        String query = "DELETE FROM transactions WHERE id=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
    // Delete a transaction

    public Transaction getLatestTransaction() throws SQLException {
        String query = "SELECT * FROM transactions ORDER BY id DESC LIMIT 1";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        Transaction transaction = new Transaction();
        while (rs.next()) {

            transaction.setId(rs.getInt("id"));
            transaction.setChkNumber(rs.getString("chk_number"));
            transaction.setDate(rs.getDate("date"));
            transaction.setMemo(rs.getString("memo"));
            transaction.setSplits(rs.getString("splits"));
            transaction.setDebit(rs.getDouble("debit"));
            transaction.setCredit(rs.getDouble("credit"));
            transaction.setBalance(rs.getDouble("balance"));
            transaction.setCreatedBy(rs.getString("created_by"));
            transaction.setLastEditedBy(rs.getString("last_edited_by"));
            transaction.setClearFlag(rs.getString("clear_flag").charAt(0));
        }
        return transaction;
    }

}
