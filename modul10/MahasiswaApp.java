/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040098.modul10;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Firda
 */
public class MahasiswaApp extends JFrame {
    // Komponen GUI
    JTextField txtNama, txtNIM, txtJurusan;
    JButton btnSimpan, btnEdit, btnHapus, btnClear;
    JTable tableMahasiswa;
    DefaultTableModel model;
    JTextField txtCari; // --- Latihan 3: Input Pencarian ---
    JButton btnCari;    // --- Latihan 3: Tombol Cari ---

    public MahasiswaApp() {
        // Setup Frame
        setTitle("Aplikasi CRUD Mahasiswa JDBC");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 1. Panel Form (Input Data)
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelForm.add(new JLabel("Nama:"));
        txtNama = new JTextField();
        panelForm.add(txtNama);

        panelForm.add(new JLabel("NIM:"));
        txtNIM = new JTextField();
        panelForm.add(txtNIM);

        panelForm.add(new JLabel("Jurusan:"));
        txtJurusan = new JTextField();
        panelForm.add(txtJurusan);

        // Panel Tombol CRUD
        JPanel panelTombol = new JPanel(new FlowLayout());
        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");

        panelTombol.add(btnSimpan);
        panelTombol.add(btnEdit);
        panelTombol.add(btnHapus);
        panelTombol.add(btnClear);

        // --- LATIHAN 3: Panel Pencarian ---
        JPanel panelPencarian = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelPencarian.add(new JLabel("Cari Nama:"));
        txtCari = new JTextField(20); // Lebar 20 karakter
        btnCari = new JButton("Cari");

        panelPencarian.add(txtCari);
        panelPencarian.add(btnCari);

        // Gabungkan Panel Form dan Tombol di bagian Atas (NORTH)
        // LATIHAN 3
        JPanel panelBawahForm = new JPanel(new BorderLayout());
        panelBawahForm.add(panelTombol, BorderLayout.NORTH);
        panelBawahForm.add(panelPencarian, BorderLayout.CENTER);

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelForm, BorderLayout.CENTER);
        panelAtas.add(panelBawahForm, BorderLayout.SOUTH);
        add(panelAtas, BorderLayout.NORTH);

        // 2. Tabel Data (Menampilkan Data)
        model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Nama");
        model.addColumn("NIM");
        model.addColumn("Jurusan");

        tableMahasiswa = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableMahasiswa);
        add(scrollPane, BorderLayout.CENTER);

        // --- Event Listeners ---

        // Listener Klik Tabel (Mengambil data saat baris diklik)
        tableMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableMahasiswa.getSelectedRow();
                txtNama.setText(model.getValueAt(row, 1).toString());
                txtNIM.setText(model.getValueAt(row, 2).toString());
                txtJurusan.setText(model.getValueAt(row, 3).toString());
            }
        });

        // Aksi Tombol
        btnSimpan.addActionListener(e -> tambahData());
        btnEdit.addActionListener(e -> ubahData());
        btnHapus.addActionListener(e -> hapusData());
        btnClear.addActionListener(e -> kosongkanForm());
        // --- LATIHAN 3: Event Listener Cari ---
        btnCari.addActionListener(e -> cariData());

        // Load data saat aplikasi pertama kali jalan
        loadData(); //
    }

    // 1. READ (Menampilkan Data)
    private void loadData() {
        model.setRowCount(0); // Reset tabel
        try {
            Connection conn = KoneksiDB.configDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM mahasiswa"); // [cite: 259]

            int no = 1;
            while (res.next()) {
                model.addRow(new Object[] {
                        no++,
                        res.getString("nama"),
                        res.getString("nim"),
                        res.getString("jurusan")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Load Data: " + e.getMessage());
        }
    }

    // 2. CREATE (Menambah Data) [cite: 310]
    private void tambahData() {
        // --- LATIHAN 2: Validasi Input ---
        // Memeriksa apakah Nama atau NIM kosong sebelum menyimpan
        if (txtNama.getText().trim().isEmpty() || txtNIM.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Data tidak boleh kosong!");
            return; // Batalkan penyimpanan (keluar dari method ini)
        }
        try {
            String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, txtNama.getText());
            pst.setString(2, txtNIM.getText());
            pst.setString(3, txtJurusan.getText());

            pst.execute();
            JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan");
            loadData();
            kosongkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Simpan: " + e.getMessage());
        }
    }

    // 3. UPDATE (Mengubah Data berdasarkan NIM) [cite: 326]
    private void ubahData() {
        try {
            String sql = "UPDATE mahasiswa SET nama = ?, jurusan = ? WHERE nim = ?";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, txtNama.getText());
            pst.setString(2, txtJurusan.getText());
            pst.setString(3, txtNIM.getText()); // Kunci update berdasarkan NIM

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Berhasil Diubah");
            loadData();
            kosongkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Edit: " + e.getMessage());
        }
    }

    // 4. DELETE (Menghapus Data) [cite: 330]
    private void hapusData() {
        try {
            String sql = "DELETE FROM mahasiswa WHERE nim = ?";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, txtNIM.getText());

            pst.execute();
            JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus");
            loadData();
            kosongkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Hapus: " + e.getMessage());
        }
    }

    // --- LATIHAN 3: Method Cari Data ---
    private void cariData() {
        model.setRowCount(0); // Reset tabel
        try {
            String sql = "SELECT * FROM mahasiswa WHERE nama LIKE ?"; // Query pencarian
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);

            // Mengisi parameter ? dengan %kata_kunci%
            pst.setString(1, "%" + txtCari.getText() + "%");

            ResultSet res = pst.executeQuery();

            int no = 1;
            while (res.next()) {
                model.addRow(new Object[] {
                        no++,
                        res.getString("nama"),
                        res.getString("nim"),
                        res.getString("jurusan")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Cari Data: " + e.getMessage());
        }
    }

    // Latihan 4: Metode Pengecekan Duplikasi NIM
    private boolean isNIMExists(String nim) {
        String sql = "SELECT COUNT(*) FROM mahasiswa WHERE nim = ?";
        try (Connection conn = KoneksiDB.configDB();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, nim);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    // Jika hasil COUNT(*) lebih dari 0, berarti NIM sudah ada
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error Cek NIM: " + e.getMessage());
            return true; // Jika error, anggap ada (supaya aman tidak insert)
        }
        return false;
    }

    // Method untuk mengosongkan form
    private void kosongkanForm() {
        txtNama.setText(null);
        txtNIM.setText(null);
        txtJurusan.setText(null);
    }

    public static void main(String[] args) {
        // Menjalankan Aplikasi
        SwingUtilities.invokeLater(() -> new MahasiswaApp().setVisible(true));
    }
}
