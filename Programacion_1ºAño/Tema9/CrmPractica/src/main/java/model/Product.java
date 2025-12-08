package model;

import java.time.LocalDate;

public class Product {
    private String code;
    private String client;
    private int units;
    private String description;
    private double total;
    private String comment;

    public Product(String code, String client, int units, String description, double total, String comment) {
        this.code = code;
        this.client = client;
        this.units = units;
        this.description = description;
        this.total = total;
        this.comment = comment;
    }

    public Product() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}

