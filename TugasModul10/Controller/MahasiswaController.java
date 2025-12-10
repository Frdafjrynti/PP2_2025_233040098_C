package id.ac.unpas.pp2_c_233040098.TugasModul10.Controller;
import id.ac.unpas.pp2_c_233040098.TugasModul10.Model.Mahasiswa;
import id.ac.unpas.pp2_c_233040098.TugasModul10.View.MahasiswaView;

import java.sql.SQLException;
import java.util.List;

public class MahasiswaController {
        private MahasiswaView view;

        public MahasiswaController(MahasiswaView view) {
            this.view = view;

            // Setup event listener untuk tombol-tombol
            this.view.btnSimpan.addActionListener(e -> simpanData());
            this.view.btnEdit.addActionListener(e -> editData());
            this.view.btnHapus.addActionListener(e -> hapusData());
            this.view.btnClear.addActionListener(e -> view.clearForm());
            this.view.btnCari.addActionListener(e -> cariData());

            // Load data pertama kali
            loadData();
        }

        // READ - Load semua data ke tabel
        public void loadData() {
            view.model.setRowCount(0);
            try {
                List<Mahasiswa> list = Mahasiswa.getAll();
                int no = 1;
                for (Mahasiswa mhs : list) {
                    view.model.addRow(new Object[]{
                            no++,
                            mhs.getNama(),
                            mhs.getNim(),
                            mhs.getJurusan()
                    });
                }
            } catch (SQLException e) {
                view.showMessage("Error Load Data: " + e.getMessage());
            }
        }

        // CREATE - Simpan data baru
        private void simpanData() {
            String nama = view.txtNama.getText().trim();
            String nim = view.txtNIM.getText().trim();
            String jurusan = view.txtJurusan.getText().trim();

            // Validasi input kosong
            if (nama.isEmpty() || nim.isEmpty()) {
                view.showMessage("Data tidak boleh kosong!");
                return;
            }

            try {
                // Cek NIM duplikat
                if (Mahasiswa.isNIMExists(nim)) {
                    view.showMessage("NIM sudah terdaftar! Gunakan NIM yang berbeda.");
                    return;
                }

                // Simpan data
                Mahasiswa mhs = new Mahasiswa(nama, nim, jurusan);
                if (mhs.simpan()) {
                    view.showMessage("Data Berhasil Disimpan");
                    loadData();
                    view.clearForm();
                } else {
                    view.showMessage("Gagal menyimpan data");
                }
            } catch (SQLException e) {
                view.showMessage("Error: " + e.getMessage());
            }
        }

        // UPDATE - Edit data
        private void editData() {
            String nama = view.txtNama.getText().trim();
            String nim = view.txtNIM.getText().trim();
            String jurusan = view.txtJurusan.getText().trim();

            try {
                Mahasiswa mhs = new Mahasiswa(nama, nim, jurusan);
                if (mhs.ubah()) {
                    view.showMessage("Data Berhasil Diubah");
                    loadData();
                    view.clearForm();
                } else {
                    view.showMessage("Gagal mengubah data");
                }
            } catch (SQLException e) {
                view.showMessage("Error: " + e.getMessage());
            }
        }

        // DELETE - Hapus data
        private void hapusData() {
            String nim = view.txtNIM.getText().trim();

            try {
                if (Mahasiswa.hapus(nim)) {
                    view.showMessage("Data Berhasil Dihapus");
                    loadData();
                    view.clearForm();
                } else {
                    view.showMessage("Gagal menghapus data");
                }
            } catch (SQLException e) {
                view.showMessage("Error: " + e.getMessage());
            }
        }

        // SEARCH - Cari data
        private void cariData() {
            view.model.setRowCount(0);
            String keyword = view.txtCari.getText().trim();

            try {
                List<Mahasiswa> list = Mahasiswa.cari(keyword);
                int no = 1;
                for (Mahasiswa mhs : list) {
                    view.model.addRow(new Object[]{
                            no++,
                            mhs.getNama(),
                            mhs.getNim(),
                            mhs.getJurusan()
                    });
                }
            } catch (SQLException e) {
                view.showMessage("Error Cari Data: " + e.getMessage());
            }
        }
}
