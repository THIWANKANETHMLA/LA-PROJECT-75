package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.CRUDUtil;
import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dto.CustomerDTO;
import com.example.layeredarchitecture.entity.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst= CRUDUtil.execute("SELECT * FROM Customer");
        ArrayList<Customer> customers=new ArrayList<Customer>();
        while (rst.next()) {
            String id=rst.getString("id");
            String name=rst.getString("name");
            String address=rst.getString("address");
            Customer entity=new Customer(id,name,address);
            customers.add(entity);
        }
        return customers;
    }
    @Override
    public boolean save(Customer customer) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",customer.getId(),customer.getName(),customer.getAddress());
    }
    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?",customer.getId(),customer.getName(),customer.getAddress());
    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("DELETE FROM Customer WHERE id=?",id);

    }
    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst =CRUDUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }
    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst=CRUDUtil
                .execute("SELECT * FROM Customer WHERE id=?", id);
        return rst.next();
    }
    @Override
    public Customer find(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CRUDUtil.execute("SELECT * FROM Customer WHERE id=?",id);
        if (rst.next()) {
            return new Customer(
                    rst.getString("id"),
                    rst.getString("name"),
                    rst.getString("address")
            );
        }
        return null;
    }

}
