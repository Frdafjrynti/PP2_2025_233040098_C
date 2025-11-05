/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040098.modul6;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Firda
 */
public class KonverterSuhu {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Konverter Suhu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 100);
        frame.setLayout(new GridLayout(3, 2, 5, 5));
        
        JLabel labelCelcius = new JLabel("Celcius:");
        JTextField textCelcius = new JTextField(8);
        JButton buttonKonversi = new JButton("Konversi");
        // untuk mengisi grid kosong di sebelah tombol
        JLabel labelKosong = new JLabel(""); 
        JLabel labelFahrenheit = new JLabel("Farhenheit:");
        // baris baru di bawah Fahrenheit
        JLabel pesanError = new JLabel(""); 
//        JLabel labelHasil = new JLabel("...");
        JTextField textFahrenheit = new JTextField(8);
        //agar kotak hasil tidak bisa di ketik
        textFahrenheit.setEditable(false);
     
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textCelcius.getText().trim();

                // Reset teks sebelumnya
                textFahrenheit.setText("");
                pesanError.setText("");

                // Cek apakah input kosong
                if (input.isEmpty()) {
                    textFahrenheit.setText("Error");
                    pesanError.setText("Input harus berupa angka!");
                    return;
                }

                try {
                    double celcius = Double.parseDouble(input);
                    double fahrenheit = (celcius * 9.0 / 5.0) + 32.0;
                    textFahrenheit.setText(String.format("%.2f °F", fahrenheit));
                } catch (NumberFormatException ex) {
                    textFahrenheit.setText("Error");
                    pesanError.setText("Input harus berupa angka!");
                }
            }
        };

        
        buttonKonversi.addActionListener(listener);
        
        frame.add(labelCelcius);
        frame.add(textCelcius); 
        frame.add(buttonKonversi);
        frame.add(labelKosong);
        frame.add(labelFahrenheit);
        frame.add(textFahrenheit);
         // baris tambahan untuk pesan error
        frame.add(new JLabel("")); // kolom kiri kosong
        frame.add(pesanError);     // kolom kanan isi pesan error

        
        frame.setVisible(true);
    }
    
}
