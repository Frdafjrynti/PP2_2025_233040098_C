/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040098.modul7;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Firda
 */
public class TugasModul7 extends JFrame{
    // variabel global
    private JTextField txtNama;
    private JTextField txtNilai;
    private JComboBox<String> cmbMatkul;
    private JTable tableData;
    private DefaultTableModel tableModel;
    private JTabbedPane tabbedPane;

    // --- Method untuk membuat desain Tab Input (Termasuk tombol Reset Input) ---
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 1. komponen nama
        panel.add(new JLabel("Nama Siswa:"));
        txtNama = new JTextField();
        panel.add(txtNama);

        // 2. komponen mata kuliah 
        panel.add(new JLabel("Mata Pelajaran:"));
        String[] matkul = {"Matematika Dasar", "Bahasa Indonesia",
            "Algoritma dan Pemrograman I", "Praktikum Pemrograman II"};
        cmbMatkul = new JComboBox<>(matkul);
        panel.add(cmbMatkul);

        // 3. komponen nilai
        panel.add(new JLabel("Nilai (0-100):"));
        txtNilai = new JTextField();
        panel.add(txtNilai);

        // baris untuk tombol simpan
        panel.add(new JLabel("")); 
        JButton btnSimpan = new JButton("Simpan Data");
        panel.add(btnSimpan);

        // baris untuk tombol reset 
        panel.add(new JLabel("")); // Placeholder agar tombol Reset di kanan
        JButton btnReset = new JButton("Reset Input"); // Tugas 4: Tombol Reset
        panel.add(btnReset);


        // event handling tombol simpan
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesSimpan();
            }
        });

        // event handling tombol reset 
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNama.setText("");
                txtNilai.setText("");
                cmbMatkul.setSelectedIndex(0);
            }
        });

        return panel;
    }

    // method untuk membuat desain Tab Tabel
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // setup model tabel 
        String[] kolom = {"Nama Siswa", "Mata Pelajaran", "Nilai", "Grade"};
        tableModel = new DefaultTableModel(kolom, 0);
        tableData = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(tableData);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Di method createTablePanel()
        JButton btnHapus = new JButton("Hapus Data Terpilih");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnHapus);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Event handling tombol hapus 
        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableData.getSelectedRow(); 
                if (selectedRow >= 0) {
                    // Konfirmasi sebelum menghapus
                    int confirm = JOptionPane.showConfirmDialog(
                        TugasModul7.this,
                        "Apakah Anda yakin ingin menghapus data ini?",
                        "Konfirmasi Hapus",
                        JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        tableModel.removeRow(selectedRow); 
                        JOptionPane.showMessageDialog(TugasModul7.this, 
                            "Data berhasil dihapus!",
                            "Sukses",
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(TugasModul7.this, 
                        "Pilih baris yang akan dihapus!", 
                        "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        return panel;
    }

    // logika validasi dan penyimpanan data 
    private void prosesSimpan() {
        // 1. ambil data dari input
        String nama = txtNama.getText(); 
        String matkul = (String) cmbMatkul.getSelectedItem(); 
        String strNilai = txtNilai.getText();

        // 2. VALIDASI INPUT
        // validasi 1: cek apakah nama kosong
        if (nama.trim().isEmpty()) { 
            JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!", 
                    "Error Validasi", JOptionPane.ERROR_MESSAGE); 
            return; 
        }

        // validasi minimal 3 karakter untuk nama
        if (nama.trim().length() < 3) { // 
             JOptionPane.showMessageDialog(this, "Nama siswa minimal harus 3 karakter!",
                    "Error Validasi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // validasi 2: cek apakah nilai berupa angka dan dalam range valid
        int nilai;
        try {
            nilai = Integer.parseInt(strNilai); 
            if (nilai < 0 || nilai > 100) { 
                JOptionPane.showMessageDialog(this, "Nilai harus antara 0-100!", 
                        "Error Validasi", JOptionPane.WARNING_MESSAGE); 
                return; // [cite: 122]
            }
        } catch (NumberFormatException e) { 
            JOptionPane.showMessageDialog(this, "Nilai harus berupa angka!", 
                    "Error Validasi", JOptionPane.ERROR_MESSAGE); 
            return; 
        }

        // 3. logika bisnis menentukan grade
        String grade;
        
        // menggunakan switch case TRADISIONAL dengan range nilai
        int rangeNilai = nilai / 10;
        
        switch (rangeNilai) {
            case 10:
            case 9:
            case 8: // Nilai 80-100
                grade = "A"; 
                break;
            case 7: // Nilai 70-79
                grade = "AB";
                break;
            case 6: // Nilai 60-69
                grade = "B"; 
                break;
            case 5: // Nilai 50-59
                grade = "BC"; 
                break;
            case 4: // Nilai 40-49
                grade = "C";
                break;
            case 3: // Nilai 30-39
                grade = "D";
                break;
            default: // Nilai 0-29
                grade = "E";
                break;
        }
        
        // 4. masukkan ke Tabel (Update Model)
        Object[] dataBaris = {nama, matkul, nilai, grade}; 
        tableModel.addRow(dataBaris); 

        // 5. reset form dan pindah tab
        txtNama.setText(""); 
        txtNilai.setText(""); 
        cmbMatkul.setSelectedIndex(0); 
        JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan!"); 
        tabbedPane.setSelectedIndex(1); 
    }

    // konstruktor 
    public TugasModul7() {
        // 1. konfigurasi frame utama 
        setTitle("Aplikasi Manajemen Nilai Siswa - Tugas Modul 7"); 
        setSize(550, 450); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocationRelativeTo(null); 

        // 2. inisialisasi tabbed pane 
        tabbedPane = new JTabbedPane(); 

        // 3. buat panel untuk tab 1 (Form Input) 
        JPanel panelInput = createInputPanel();
        tabbedPane.addTab("Input Data", panelInput);

        // 4. buat panel untuk tab 2 (Tabel Data) 
        JPanel panelTabel = createTablePanel();
        tabbedPane.addTab("Daftar Nilai", panelTabel); 

        // tambah TabbedPane ke frame 
        add(tabbedPane); // [cite: 160]
    }

    // Method main 
    public static void main(String[] args) { 
        SwingUtilities.invokeLater(() -> { 
            new TugasModul7().setVisible(true); 
        });
    }
}
