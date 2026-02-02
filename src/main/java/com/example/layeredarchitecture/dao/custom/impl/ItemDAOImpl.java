package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.CRUDUtil;
import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.model.ItemDTO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public ArrayList<ItemDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CRUDUtil.execute("SELECT * FROM Item");
        ArrayList<ItemDTO> items = new ArrayList<>();
        while (rst.next()) {
            String code = rst.getString("code");
            String description = rst.getString("description");
            BigDecimal unitPrice = rst.getBigDecimal("unitPrice");
            Integer qtyOnHand = rst.getInt("qtyOnHand");
            ItemDTO ItemDTO = new ItemDTO(code, description, unitPrice, qtyOnHand);
            items.add(ItemDTO);
        }
        return items;

    }

    @Override
    public boolean save(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)", itemDTO.getCode(), itemDTO.getDescription(), itemDTO.getUnitPrice(), itemDTO.getQtyOnHand());
    }

    @Override
    public boolean update(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {

        return CRUDUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?", itemDTO.getDescription(), itemDTO.getUnitPrice(), itemDTO.getQtyOnHand(), itemDTO.getCode());
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("DELETE FROM Item WHERE code=?",code);
    }

    @Override
    public boolean exist(String itemCode) throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtil.execute("SELECT code FROM Item WHERE code=?", itemCode);
        return rs.next();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = CRUDUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }

    @Override
    public ItemDTO find(String itemCode) throws SQLException, ClassNotFoundException {
        ResultSet rst = CRUDUtil.execute("SELECT * FROM Item WHERE code=?", itemCode);

        if (rst.next()) {
            return new ItemDTO(
                    rst.getString("code"),
                    rst.getString("description"),
                    rst.getBigDecimal("unitPrice"),
                    rst.getInt("qtyOnHand")
            );
        }
        return null;
    }

}


