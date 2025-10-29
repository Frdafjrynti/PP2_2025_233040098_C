/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040098.modul5;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Firda
 */
public class latihan4 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable () {
           public void run() {
               JFrame frame = new JFrame("Contoh BorderLayout");
               frame.setSize(400,300);
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               
               frame.setLayout(new BorderLayout());
               
               JLabel label = new JLabel("ILabel ada di Atas (NORTH)");
               JButton button = new JButton("Tombol ada di Bawah (SOUTH)");
               
                button.addActionListener(e -> {
                    label.setText("Tombol di SOUTH diklik!");
                });
               
               frame.add(label, BorderLayout.NORTH);
               frame.add(button, BorderLayout.SOUTH);
               frame.add(new JButton("WEST"), BorderLayout.WEST);
               frame.add(new JButton("EAST"), BorderLayout.EAST);
               frame.add(new JButton("CENTER"), BorderLayout.CENTER);
               
               frame.setVisible(true);
           } 
        });
    }
    
}
