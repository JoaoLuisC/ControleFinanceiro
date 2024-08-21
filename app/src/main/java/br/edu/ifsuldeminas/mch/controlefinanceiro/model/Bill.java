package br.edu.ifsuldeminas.mch.controlefinanceiro.model;

import android.util.Log;

import java.io.Serializable;

public class Bill implements Serializable {

    private Integer id;
    private String description, category;
    private Double value;

    public Bill(Integer id, String description, String category, Double value) {
        this.id = id;
        setDescription(description);
        setCategory(category);
        setValue(value);
    }


    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
