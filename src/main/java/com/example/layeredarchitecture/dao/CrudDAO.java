package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.CustomerDTO;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T>  extends SuperDAO{
    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

    public boolean save(T DTO) throws SQLException, ClassNotFoundException ;

    public boolean update(T DTO) throws SQLException, ClassNotFoundException ;

    public boolean delete(String id) throws SQLException, ClassNotFoundException ;

    public String generateNewID() throws SQLException, ClassNotFoundException;

    public boolean exist(String id) throws SQLException, ClassNotFoundException;

    public T find(String id) throws SQLException, ClassNotFoundException;
}
