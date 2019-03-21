package com.axity.services.impl;
import com.axity.services.*;
public class JiraConnection implements ConnectionStrategy{
    @Override
    public void connectTo() {
        System.out.println("conexion jira");
    }
}
