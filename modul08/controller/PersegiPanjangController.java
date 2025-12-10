/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040098.modul08.controller;

import id.ac.unpas.pp2_c_233040098.modul08.model.PersegiPanjangModel;
import id.ac.unpas.pp2_c_233040098.modul08.view.PersegiPanjangView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Firda
 */
public class PersegiPanjangController {
    // model dan view sebagai atribut kelas
    private PersegiPanjangModel model;
    private PersegiPanjangView view;
    
    public PersegiPanjangController(PersegiPanjangModel model, PersegiPanjangView view) {
        this.model = model;
        this.view = view;
        
        // menghhubungkan tombol di view dengan logic di controller
        this.view.addHitungListener(new HitungListener());
        
        // latihan 3: mengubungkan tombol reset dengan logic di controller
        this.view.addResetListener(new ResetListener());
    }

   // Inner class untuk menangani event klik tombol
class HitungListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // 1. Ambil data dari View
            double p = view.getPanjang();
            double l = view.getLebar();

            // 2. Kirim data ke Model
            model.setPanjang(p);
            model.setLebar(l);

            // 3. Jalankan logika bisnis di Model
            model.hitungLuas();
            
            // latihan 2: hitung keliling juga
            model.hitungKeliling();

            // 4. Ambil hasil dari Model dan tampilkan kembali ke View
            double hasil = model.getLuas();
            view.setHasil(hasil);
            
            // latihan 2: ambil asil keliling dan tampilkan ke view
            double keliling = model.getkeliling();
            view.setKeliling(keliling);

        } catch (NumberFormatException ex) {
            // Handle jika input bukan angka
            view.tampilkanPesanError("Masukkan angka yang valid!");
        }
    }
}

// latihan 3: inner class untuk menangani evemt klik tombol reset
class ResetListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Panggil method reset di View
        view.resetInput();
    }
}

}
