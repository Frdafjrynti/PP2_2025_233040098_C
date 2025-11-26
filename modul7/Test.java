/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040098.modul7;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
class Test extends JFrame{
     // Komponen Input
    private JTextField txtNama;
    private JTextField txtNilai;
    private JComboBox<String> cmbMataKuliah;
    
    // Komponen Output
    private JTable table;
    private DefaultTableModel tableModel;
    
    // Tab Container
    private JTabbedPane tabbedPane;
    
    // Method untuk membuat panel input
    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Label dan TextField untuk Nama
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Nama Siswa:"), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        txtNama = new JTextField(20);
        panel.add(txtNama, gbc);
        
        // Label dan ComboBox untuk Mata Kuliah
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        panel.add(new JLabel("Mata Kuliah:"), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        String[] mataKuliah = {"Matematika Dasar", "Bahasa Indonesia", "Algoritma dan pemograman I", 
                               "Praktikum Pemograman II"};
        cmbMataKuliah = new JComboBox<>(mataKuliah);
        panel.add(cmbMataKuliah, gbc);
        
        // Label dan TextField untuk Nilai
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        panel.add(new JLabel("Nilai:"), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        txtNilai = new JTextField(20);
        panel.add(txtNilai, gbc);
        
        // Tombol Simpan
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        JButton btnSimpan = new JButton("Simpan Data");
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesSimpan();
            }
        });
        panel.add(btnSimpan, gbc);
        
        // Tombol Reset
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNama.setText("");
                txtNilai.setText("");
                cmbMataKuliah.setSelectedIndex(0);
            }
        });
        panel.add(btnReset, gbc);
        
        return panel;
    }
    
    // Method untuk membuat panel tabel
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Membuat model tabel dengan kolom
        String[] columnNames = {"Nama", "Mata Kuliah", "Nilai", "Grade"};
        tableModel = new DefaultTableModel(columnNames, 0);
        
        // Membuat JTable dengan model
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel untuk tombol hapus
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnHapus = new JButton("Hapus Data");
        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow > -1) {
                    int confirm = JOptionPane.showConfirmDialog(
                        Test.this,
                        "Apakah Anda yakin ingin menghapus data ini?",
                        "Konfirmasi Hapus",
                        JOptionPane.YES_NO_OPTION
                    );
                    
                    if (confirm == JOptionPane.YES_OPTION) {
                        tableModel.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(
                            Test.this,
                            "Data berhasil dihapus!",
                            "Sukses",
                            JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                } else {
                    JOptionPane.showMessageDialog(
                        Test.this,
                        "Pilih baris yang ingin dihapus!",
                        "Peringatan",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });
        buttonPanel.add(btnHapus);
        
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    // Method untuk memproses penyimpanan data
    private void prosesSimpan() {
        // Ambil data dari komponen input
        String nama = txtNama.getText();
        String mataKuliah = (String) cmbMataKuliah.getSelectedItem();
        String strNilai = txtNilai.getText();
        
        // Validasi: Nama tidak boleh kosong
        if (nama.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Nama siswa tidak boleh kosong!",
                "Error Validasi",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        // Validasi: Nama minimal 3 karakter
        if (nama.trim().length() < 3) {
            JOptionPane.showMessageDialog(
                this,
                "Nama siswa minimal terdiri dari 3 karakter!",
                "Error Validasi",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        // Validasi: Nilai harus berupa angka
        int nilai;
        try {
            nilai = Integer.parseInt(strNilai);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                this,
                "Nilai harus berupa angka!",
                "Error Validasi",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        // Validasi: Nilai harus dalam rentang 0-100
        if (nilai < 0 || nilai > 100) {
            JOptionPane.showMessageDialog(
                this,
                "Nilai harus berada dalam rentang 0-100!",
                "Error Validasi",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        // Menentukan grade menggunakan switch case
        String grade;
        switch (nilai / 10) {
            case 10:
            case 8:
                grade = "AB";
                break;
            case 7:
                grade = "B";
                break;
            case 6:
                grade = "BC";
                break;
            case 5:
                grade = "C";
                break;
            default:
                grade = "D";
                break;
        }
        
        // Tambahkan data ke tabel
        Object[] rowData = {nama, mataKuliah, nilai, grade};
        tableModel.addRow(rowData);
        
        // Tampilkan pesan sukses
        JOptionPane.showMessageDialog(
            this,
            "Data berhasil disimpan!",
            "Sukses",
            JOptionPane.INFORMATION_MESSAGE
        );
        
        // Bersihkan input setelah simpan
        txtNama.setText("");
        txtNilai.setText("");
        cmbMataKuliah.setSelectedIndex(0);
        
        // Pindah ke tab Daftar Nilai
        tabbedPane.setSelectedIndex(1);
    }
    
    // Konstruktor
    public Test() {
        // Pengaturan dasar JFrame
        setTitle("Aplikasi Manajemen Nilai Siswa");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Membuat TabbedPane
        tabbedPane = new JTabbedPane();
        
        // Menambahkan tab Input Data
        JPanel inputPanel = createInputPanel();
        tabbedPane.addTab("Input Data", inputPanel);
        
        // Menambahkan tab Daftar Nilai
        JPanel tablePanel = createTablePanel();
        tabbedPane.addTab("Daftar Nilai", tablePanel);
        
        // Menambahkan TabbedPane ke JFrame
        add(tabbedPane);
    }
    
    // Method main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ManajemenNilaiSiswaApp app = new ManajemenNilaiSiswaApp();
                app.setVisible(true);
            }
        });
    }
}
