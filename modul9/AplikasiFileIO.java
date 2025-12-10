/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040098.modul9;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 *
 * @author Firda
 */
public class AplikasiFileIO extends JFrame {
    // // Komponen UI
    private JTextArea textArea;
    private JButton btnOpenText, btnSaveText;
    private JButton btnSaveBinary, btnLoadBinary;
    private JButton btnAppendText; // Latihan 4: Tambah tombol Append
    private JFileChooser fileChooser;

    public AplikasiFileIO() {
        super("Tutorial File IO & Exception Handling");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // // Inisialisasi Komponen
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        fileChooser = new JFileChooser();

        // // Panel Tombol
        JPanel buttonPanel = new JPanel();
        btnOpenText = new JButton("Buka Text");
        btnSaveText = new JButton("Simpan Text (Overwrite)");
        btnAppendText = new JButton("Tambah Text (Append)"); // Latihan 4: Tombol Append
        btnSaveBinary = new JButton("Simpan Config (Binary)"); // Latihan 3: Simpan Objek
        btnLoadBinary = new JButton("Muat Config (Binary)"); // Latihan 1: Muat Binary (DataStream)

        buttonPanel.add(btnOpenText);
        buttonPanel.add(btnSaveText);
        buttonPanel.add(btnAppendText); // Latihan 4: Tambahkan ke panel
        buttonPanel.add(btnSaveBinary);
        buttonPanel.add(btnLoadBinary);

        // // Layout
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // // --- Event Handling ---
        
        // 1. MEMBACA FILE TEKS (Text Stream)
        btnOpenText.addActionListener(e -> bukaFileTeks());

        // 2. MENULIS FILE TEKS (Text Stream) - Overwrite
        btnSaveText.addActionListener(e -> simpanFileTeks());

        // Latihan 4. MENULIS FILE TEKS (Append)
        btnAppendText.addActionListener(e -> appendFileTeks());

        // 3. MENULIS FILE BINARY (Serialisasi Objek)
        btnSaveBinary.addActionListener(e -> simpanConfigBinary());

        // 4. MEMBACA FILE BINARY (Byte Stream)
        btnLoadBinary.addActionListener(e -> muatConfigBinary());

        // Latihan 2. Muat file saat aplikasi dibuka (Constructor)
        muatNotesOtomatis(); 
    }
    
    // // Latihan 4. Menambahkan Teks (Append)
    private void appendFileTeks() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            // Menggunakan konstruktor FileWriter(file, true) untuk Append
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                
                writer.newLine(); // Tambahkan baris baru
                writer.write(textArea.getText());
                
                JOptionPane.showMessageDialog(this, "Text berhasil ditambahkan (Append)!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menambahkan text: " + ex.getMessage());
            }
        }
    }

    // // Latihan 2. Metode untuk membaca last_notes.txt secara otomatis
    private void muatNotesOtomatis() {
        // Try-With-Resources untuk menutup stream otomatis
        try (BufferedReader reader = new BufferedReader(new FileReader("last_notes.txt"))) {
            textArea.setText(""); 
            String line;
            
            // Baca baris demi baris
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }
            
        } catch (FileNotFoundException ex) {
 
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal membaca last_notes.txt: " + ex.getMessage());
        }
    }

    // // Contoh: Membaca File Teks (Try-Catch-Finally Konvensional)
    private void bukaFileTeks() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            BufferedReader reader = null; 

            try {
                reader = new BufferedReader(new FileReader(file));
                textArea.setText(""); 
                String line;
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
                JOptionPane.showMessageDialog(this, "File berhasil dimuat!");
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "File tidak ditemukan: " + ex.getMessage());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal membaca file: " + ex.getMessage());
            } finally {
                // Pastikan stream ditutup
                try {
                    if (reader != null) {
                        reader.close(); 
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    // // Contoh: Menulis File Teks (Overwrite - Try-With-Resources)
    private void simpanFileTeks() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            // Konstruktor default FileWriter (tanpa 'true') = Overwrite
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) { 
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "File berhasil disimpan!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan file: " + ex.getMessage());
            }
        }
    }

    // // Latihan 3: Simpan Objek (Serialisasi)
    private void simpanConfigBinary() {
        String username = JOptionPane.showInputDialog(this, "Masukkan Username:");
        if (username != null) {
            int fontSize = textArea.getFont().getSize(); 
            UserConfig config = new UserConfig();
            config.setUsername(username);
            config.setFontsize(fontSize);

            // Menyimpan objek utuh ke file
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user_config.dat"))) {
                oos.writeObject(config);
                JOptionPane.showMessageDialog(this, "Objek konfigurasi untuk '" + username + "' berhasil disimpan!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan objek: " + ex.getMessage());
            }
        }
    }
    
    // // Contoh: Membaca Binary (DataStream)
    private void muatConfigBinary() {
        // Membaca data primitif (int) dari file binary
        try (DataInputStream dis = new DataInputStream(new FileInputStream("config.bin"))) {
            int fontSize = dis.readInt();
            
            textArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize));
            JOptionPane.showMessageDialog(this, "Font diubah menjadi ukuran: " + fontSize);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "File config.bin belum dimuat!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal membaca binary: " + ex.getMessage());
        }
    }

    // // Metode utama (Entry Point) aplikasi
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AplikasiFileIO().setVisible(true);
        });
    }
}
