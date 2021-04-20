package com.btw.ManageSystem.entity;

/**
 * @author: elvis.yue@i9i8.com
 * @create: 2021-04-20 11:39
 */
public class Operation {

    private String operation_id;
    private String operation_name;
    private String operation_grade;
    public void setOperation_id(String operation_id) {
        this.operation_id = operation_id;
    }
    public String getOperation_id() {
        return operation_id;
    }

    public void setOperation_name(String operation_name) {
        this.operation_name = operation_name;
    }
    public String getOperation_name() {
        return operation_name;
    }

    public void setOperation_grade(String operation_grade) {
        this.operation_grade = operation_grade;
    }
    public String getOperation_grade() {
        return operation_grade;
    }

}