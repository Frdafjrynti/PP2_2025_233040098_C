package id.ac.unpas.pp2_c_233040098.TugasModul10.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MahasiswaView extends JFrame{
        // Komponen GUI (public agar bisa diakses Controller)
        public JTextField txtNama, txtNIM, txtJurusan, txtCari;
        public JButton btnSimpan, btnEdit, btnHapus, btnClear, btnCari;
        public JTable tableMahasiswa;
        public DefaultTableModel model;

        public MahasiswaView() {
            initComponents();
        }

        private void initComponents() {
            // Setup Frame
            setTitle("Aplikasi CRUD Mahasiswa MVC");
            setSize(600, 500);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            // Panel Form
            JPanel panelForm = new JPanel(new GridLayout(3, 2, 10, 10));
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

            // Panel Pencarian
            JPanel panelPencarian = new JPanel(new FlowLayout(FlowLayout.LEFT));
            panelPencarian.add(new JLabel("Cari Nama:"));
            txtCari = new JTextField(20);
            btnCari = new JButton("Cari");
            panelPencarian.add(txtCari);
            panelPencarian.add(btnCari);

            // Gabungkan Panel
            JPanel panelAtas = new JPanel(new BorderLayout());
            panelAtas.add(panelForm, BorderLayout.NORTH);
            panelAtas.add(panelTombol, BorderLayout.CENTER);
            panelAtas.add(panelPencarian, BorderLayout.SOUTH);

            add(panelAtas, BorderLayout.NORTH);

            // Tabel
            model = new DefaultTableModel();
            model.addColumn("No");
            model.addColumn("Nama");
            model.addColumn("NIM");
            model.addColumn("Jurusan");

            tableMahasiswa = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(tableMahasiswa);
            add(scrollPane, BorderLayout.CENTER);

            // Event klik tabel
            tableMahasiswa.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int row = tableMahasiswa.getSelectedRow();
                    txtNama.setText(model.getValueAt(row, 1).toString());
                    txtNIM.setText(model.getValueAt(row, 2).toString());
                    txtJurusan.setText(model.getValueAt(row, 3).toString());
                }
            });
        }

        // Method untuk menampilkan pesan
        public void showMessage(String message) {
            JOptionPane.showMessageDialog(this, message);
        }

        // Method untuk kosongkan form
        public void clearForm() {
            txtNama.setText("");
            txtNIM.setText("");
            txtJurusan.setText("");
        }
}
