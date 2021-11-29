package com.example.bakeryshop;

public class dataholder2 {
    String address,quantity,pincode,phone;

    public dataholder2(String address, String quantity, String pincode, String phone) {
        this.address = address;
        this.quantity = quantity;
        this.pincode = pincode;
        this.phone = phone;

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
