package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.bo.custom.CustomerBO;
import com.example.layeredarchitecture.bo.custom.impl.CustomerBOImpl;
import com.example.layeredarchitecture.bo.custom.impl.ItemBOImpl;
import com.example.layeredarchitecture.bo.custom.impl.PlaceOrderBoImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {}

    public static BOFactory getInstance(){
        return boFactory == null ? new BOFactory() : boFactory;
    }

    public enum BOType{
        CUSTOMER,
        ITEM,
        PLACE_ORDER
    }

    public SuperBO getBO(BOType boType){
        switch (boType){
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case PLACE_ORDER:
                return new PlaceOrderBoImpl();
            default:
                return null;
        }
    }
}
