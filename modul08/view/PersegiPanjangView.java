/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040098.modul08.view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Firda
 */
public class PersegiPanjangView extends JFrame{
   // Komponen UI sebagai atribut
private JTextField txtPanjang = new JTextField(10);
private JTextField txtLebar = new JTextField(10);
private JLabel lblHasil = new JLabel("-");
private JButton btnHitung = new JButton("Hitung");
//// latihan 2: menambahkan label unntuk keliling
private JLabel lblKeliling = new JLabel("-");
//latihan 3: menambahkan tombol reset
private JButton btnReset = new JButton("Reset");

public PersegiPanjangView() {

    // Inisialisasi UI
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(300, 250);
    this.setLayout(new GridLayout(6, 2, 10, 10)); // Grid 4 baris
    this.setTitle("MVC Kalkulator");

    // Baris input panjang
    this.add(new JLabel("Panjang:"));
    this.add(txtPanjang);
    // Baris input lebar
    this.add(new JLabel("Lebar:"));
    this.add(txtLebar);

    // Baris hasil
    this.add(new JLabel("Hasil Luas:"));
    this.add(lblHasil);
    
    // latihan 2: baris hasil keliling
    this.add(new JLabel("Hasil keliling:"));
    this.add(lblKeliling);
    
    
     // Baris tombol hitung
    this.add(btnHitung);  
    //latihan 3: baris tombol reset
    this.add(btnReset);
}

// latihan 3: method untuk reset/menghapus input dan hasil
public void resetInput() {
    txtPanjang.setText("");
    txtLebar.setText("");
    lblHasil.setText("-");
    lblKeliling.setText("-");
}

// Mengambil nilai panjang dari TextField
public double getPanjang() {
    return Double.parseDouble(txtPanjang.getText());
}

// Mengambil nilai lebar dari TextField
public double getLebar() {
    return Double.parseDouble(txtLebar.getText());
}

// menampilkan hasil ke label
public void setHasil(double hasil) {
    lblHasil.setText(String.valueOf(hasil));
}

// latihan 2: menampilkan hasil  keliling ke label
public void setKeliling(double keliling) {
    lblKeliling.setText(String.valueOf(keliling));
}

// menampilkan pesan error jika input bukan angka
public void tampilkanPesanError(String pesan) {
    JOptionPane.showMessageDialog(this, pesan);
}

// mendaftarkan listener untuk tombol controller yang akan memberikan aksinya
public void addHitungListener(ActionListener listener) {
    btnHitung.addActionListener(listener);
}

// --- TAMBAHAN UNTUK TOMBOL RESET ---
    // Mendaftarkan listener untuk tombol Reset
    public void addResetListener(ActionListener listener) {
        btnReset.addActionListener(listener);
    }

}
