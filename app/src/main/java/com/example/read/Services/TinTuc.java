package com.example.read.Services;

import java.io.Serializable;

public class TinTuc implements Serializable {
    public int id;
    public String tieuDe;
    public String hinhAnh;
    public String link;
    public String date;


    public TinTuc(int id, String tieuDe, String hinhAnh, String link, String date) {
        this.id = id;
        this.tieuDe = tieuDe;
        this.hinhAnh = hinhAnh;
        this.link = link;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TinTuc(String tieuDe, String hinhAnh, String link, String date) {
        this.tieuDe = tieuDe;
        this.hinhAnh = hinhAnh;
        this.link = link;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
