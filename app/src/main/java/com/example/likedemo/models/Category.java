package com.example.likedemo.models;

import java.util.Date;

public class Category {
    Integer id;
    String display_name_ar;
    String display_name_en;
    String status;
    Date created_at;
    Date updated_at;

    public Category() {
    }

    public Category(Integer id, String display_name_ar, String display_name_en, String status, Date created_at, Date updated_at) {
        this.id = id;
        this.display_name_ar = display_name_ar;
        this.display_name_en = display_name_en;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDisplay_name_ar() {
        return display_name_ar;
    }

    public void setDisplay_name_ar(String display_name_ar) {
        this.display_name_ar = display_name_ar;
    }

    public String getDisplay_name_en() {
        return display_name_en;
    }

    public void setDisplay_name_en(String display_name_en) {
        this.display_name_en = display_name_en;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", display_name_ar='" + display_name_ar + '\'' +
                ", display_name_en='" + display_name_en + '\'' +
                ", status='" + status + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
