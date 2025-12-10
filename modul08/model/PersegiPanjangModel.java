/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040098.modul08.model;

import id.ac.unpas.pp2_c_233040098.modul08.controller.PersegiPanjangController;

/**
 *
 * @author Firda
 */
public class PersegiPanjangModel {
    private double panjang;
    private double lebar;
    private double luas;
    // latihan 2: menambahkan variable keliling
    private double keliling;
    
    // menghitung luas (logika bisnis)
    public void hitungLuas(){
        this.luas = this.panjang * this.lebar;
    }
    
    // latihan 2: menghitung keliling (logika bisnis)
    public void hitungKeliling() {
        this.keliling = 2 * (this.panjang + this.lebar);
    }
    
    // getters dan setter
    public void setPanjang(double panjang) {
        this.panjang = panjang;
    }
    public void setLebar(double lebar) {
        this.lebar = lebar;
    }
    public double getLuas() {
        return luas;
    }

    // latihan 2: gatter untuk keliling
    public double getkeliling() {
        return keliling;
    }

}
