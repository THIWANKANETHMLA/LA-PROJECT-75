package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO {
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException;

    public boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException ;

    public boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException ;

    public boolean delete(String id) throws SQLException, ClassNotFoundException ;

    public String generateNewID() throws SQLException, ClassNotFoundException;

    public boolean exist(String id) throws SQLException, ClassNotFoundException;

    public CustomerDTO find(String id) throws SQLException, ClassNotFoundException;
}
