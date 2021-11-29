package com.example.bakeryshop;

public class dataholder {
    String password,phone,email;

    public dataholder(String password, String phone, String email){
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
