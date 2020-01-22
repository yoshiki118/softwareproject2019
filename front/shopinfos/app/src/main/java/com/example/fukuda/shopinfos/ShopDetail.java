package com.example.fukuda.shopinfos;

import android.graphics.Bitmap;

public class ShopDetail {

    private String name;
    private String tel;
    private Bitmap image;
    private String address;
    private String open;
    private String holiday;
    private String budget;
    private String station;
    private String walk;
    private String credit_card;
    private String e_monay;
    private int m_qupon;
    private int p_qupon;

    // name
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    // tel
    public void setTel(String tel) { this.tel = tel; }

    public String getTel() {return this.tel;}

    // image
    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage() { return this.image; }

    // address
    public void setAddress(String address) { this.address = address; }

    public String getAddress() { return this.address; }

    // open
    public void setOpen(String open) { this.open = open; }

    public String getOpen () { return this.open; }

    // holiday
    public void setHoliday(String holiday) { this.holiday = holiday; }

    public String getHoliday () { return this.holiday; }

    // budget
    public void setBudget(String budget) { this.budget = budget; }

    public String getBudget () { return this.budget; }

    // station
    public void setStation(String station) { this.station = station; }

    public String getStation () { return this.station; }

    // walk
    public void setWalk(String walk) { this.walk = walk; }

    public String getWalk () { return this.walk; }

    // credit_card
    public void setCcard(String credit_card) { this.credit_card = credit_card; }

    public String getCcard () { return this.credit_card; }

    //e_monay
    public void setEmonay(String e_monay) { this.e_monay = e_monay; }

    public String getEmonay () { return this.e_monay; }

    // qupon
    public void setMqupon(int m_qupon) { this.m_qupon = m_qupon; }

    public int getMqupon() { return this.m_qupon; }

    public void setPqupon(int p_qupon) { this.p_qupon = p_qupon; }

    public int getPqupon() { return this.p_qupon; }

}

