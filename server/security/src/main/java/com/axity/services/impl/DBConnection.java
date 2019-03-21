package com.axity.services.impl;
import com.axity.services.*;
public class DBConnection implements ConnectionStrategy {
    @Override
    public void connectTo() {
        System.out.println("conexion DB");
    }
}
