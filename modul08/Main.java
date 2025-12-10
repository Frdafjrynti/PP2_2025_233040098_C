/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040098.modul08;

import id.ac.unpas.pp2_c_233040098.modul08.controller.PersegiPanjangController;
import id.ac.unpas.pp2_c_233040098.modul08.model.PersegiPanjangModel;
import id.ac.unpas.pp2_c_233040098.modul08.view.PersegiPanjangView;

/**
 *
 * @author Firda
 */
public class Main {
    public static void main(String[] args) {
        // instansiasi model
        PersegiPanjangModel model = new PersegiPanjangModel();
        
        // instansiasi view
        PersegiPanjangView view = new PersegiPanjangView();
        
        // instansiasi controller hubungkan model dan view
        PersegiPanjangController controller = new PersegiPanjangController(model, view);
        
        // tampilkan view
        view.setVisible(true);
    }
    
}
