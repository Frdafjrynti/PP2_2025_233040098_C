package id.ac.unpas.pp2_c_233040098.TugasModul10.Model;
import id.ac.unpas.pp2_c_233040098.modul10.KoneksiDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Mahasiswa {
        private int id;
        private String nama;
        private String nim;
        private String jurusan;

        // Constructor
        public Mahasiswa() {}

        public Mahasiswa(String nama, String nim, String jurusan) {
            this.nama = nama;
            this.nim = nim;
            this.jurusan = jurusan;
        }

        // Getter Setter
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public String getNama() { return nama; }
        public void setNama(String nama) { this.nama = nama; }

        public String getNim() { return nim; }
        public void setNim(String nim) { this.nim = nim; }

        public String getJurusan() { return jurusan; }
        public void setJurusan(String jurusan) { this.jurusan = jurusan; }

        // ===== METHOD DATABASE =====

        // READ - Ambil semua data
        public static List<Mahasiswa> getAll() throws SQLException {
            List<Mahasiswa> list = new ArrayList<>();
            String sql = "SELECT * FROM mahasiswa";

            try (Connection conn = KoneksiDB.configDB();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    Mahasiswa mhs = new Mahasiswa();
                    mhs.setId(rs.getInt("id"));
                    mhs.setNama(rs.getString("nama"));
                    mhs.setNim(rs.getString("nim"));
                    mhs.setJurusan(rs.getString("jurusan"));
                    list.add(mhs);
                }
            }
            return list;
        }

        // CREATE - Simpan data
        public boolean simpan() throws SQLException {
            String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
            try (Connection conn = KoneksiDB.configDB();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, this.nama);
                pst.setString(2, this.nim);
                pst.setString(3, this.jurusan);

                return pst.executeUpdate() > 0;
            }
        }

        // UPDATE - Ubah data
        public boolean ubah() throws SQLException {
            String sql = "UPDATE mahasiswa SET nama = ?, jurusan = ? WHERE nim = ?";
            try (Connection conn = KoneksiDB.configDB();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, this.nama);
                pst.setString(2, this.jurusan);
                pst.setString(3, this.nim);

                return pst.executeUpdate() > 0;
            }
        }

        // DELETE - Hapus data
        public static boolean hapus(String nim) throws SQLException {
            String sql = "DELETE FROM mahasiswa WHERE nim = ?";
            try (Connection conn = KoneksiDB.configDB();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, nim);
                return pst.executeUpdate() > 0;
            }
        }

        // SEARCH - Cari berdasarkan nama
        public static List<Mahasiswa> cari(String keyword) throws SQLException {
            List<Mahasiswa> list = new ArrayList<>();
            String sql = "SELECT * FROM mahasiswa WHERE nama LIKE ?";

            try (Connection conn = KoneksiDB.configDB();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, "%" + keyword + "%");
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    Mahasiswa mhs = new Mahasiswa();
                    mhs.setId(rs.getInt("id"));
                    mhs.setNama(rs.getString("nama"));
                    mhs.setNim(rs.getString("nim"));
                    mhs.setJurusan(rs.getString("jurusan"));
                    list.add(mhs);
                }
            }
            return list;
        }

        // CHECK - Cek NIM duplikat
        public static boolean isNIMExists(String nim) throws SQLException {
            String sql = "SELECT COUNT(*) FROM mahasiswa WHERE nim = ?";
            try (Connection conn = KoneksiDB.configDB();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, nim);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
            return false;
        }
}
