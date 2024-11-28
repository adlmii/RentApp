package src.Controller;

import src.Model.User;
import src.Config.koneksi;
import src.Model.Pelanggan;
import src.Model.Pemesanan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserController {

    private Connection conn;
    private List<User> userList; // Daftar pengguna
    private List<Pemesanan> pemesananList; // Daftar pemesanan
    private int pemesananCounter; // Counter untuk ID pemesanan

    // Constructor untuk inisialisasi UserController
    public UserController() {
        conn = koneksi.getConnection();
        this.userList = new ArrayList<>(); // Inisialisasi list pengguna
        this.pemesananList = new ArrayList<>(); // Inisialisasi list pemesanan
        this.pemesananCounter = 1; // Mulai ID pemesanan dari 1
    }

    // Metode untuk login
    public boolean login(String username, String password) {   
        try {
            String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();
    
                if (rs.next()) {
                    System.out.println("Login berhasil untuk pengguna: " + username);
                    return true; // Login berhasil
                } else {
                    System.out.println("Login gagal. Username atau password salah.");
                    return false; // Login gagal
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Mencetak stack trace untuk debugging
            System.out.println("Terjadi kesalahan saat mengakses database: " + e.getMessage());
            return false; // Login gagal
        }
    }

    // Metode untuk mengupdate profil pengguna
    public void updateProfile(String username, String newPassword, String newName) {
        for (User  user : userList) {
            if (user.getUsername().equals(username)) {
                user.setPassword(newPassword); // Update password
                if (user instanceof Pelanggan) {
                    Pelanggan pelanggan = (Pelanggan) user;
                    pelanggan.setNama(newName); // Update nama untuk pelanggan
                }
                System.out.println("Profil pengguna " + username + " berhasil diperbarui.");
                return;
            }
        }
        System.out.println("Pengguna dengan username " + username + " tidak ditemukan.");
    }

    // Metode untuk membuat pemesanan
    public void createPemesanan(String username, String item, String tglPinjam, String tglKembali) {
        String id = "PMS-" + pemesananCounter++; // Membuat ID pemesanan unik
        Pemesanan pemesanan = new Pemesanan(id, username, item, tglPinjam, tglKembali);
        pemesananList.add(pemesanan);
        System.out.println("Pemesanan berhasil untuk pengguna: " + username + " dengan item: " + item + " dari tanggal " + tglPinjam + " hingga " + tglKembali);
    }

    // Metode untuk menampilkan semua pemesanan (untuk debugging)
    public void displayPemesanan() {
        System.out.println("Daftar Pemesanan:");
        for (Pemesanan pemesanan : pemesananList) {
            System.out.println(pemesanan);
        }
    }

// Metode untuk menampilkan semua pengguna (untuk admin)
// Metode untuk menampilkan semua pengguna dari database (selain admin)
    public void displayAllUsers() {
        System.out.println();
        try {
            String sql = "SELECT username, nama, email, alamat, telepon FROM user WHERE username != 'admin'"; // Pastikan ada kolom role di tabel user
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();

                System.out.println("======= Daftar Pengguna =======");
                System.out.println("-------------------------------");
                if (!rs.isBeforeFirst()) { // Mengecek apakah hasilnya kosong
                    System.out.println("Tidak ada pengguna terdaftar.");
                    return;
                }
                while (rs.next()) {
                    String username = rs.getString("username");
                    String nama = rs.getString("nama");
                    String email = rs.getString("email");
                    String alamat = rs.getString("alamat");
                    String noTlp = rs.getString("telepon");

                    System.out.println("Username: " + username);
                    System.out.println("Nama: " + nama);
                    System.out.println("Email: " + email);
                    System.out.println("Alamat: " + alamat);
                    System.out.println("Nomor Telepon: " + noTlp);
                    System.out.println("-------------------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Mencetak stack trace untuk debugging
            System.out.println("Terjadi kesalahan saat mengakses database: " + e.getMessage());
        }
    }

    public void displayAllUsersTest() {
        System.out.println();
        try {
            String sql = "SELECT username, nama, email, alamat, telepon FROM userTest WHERE username != 'admin'"; // Pastikan ada kolom role di tabel user
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();

                System.out.println("======= Daftar Pengguna =======");
                System.out.println("-------------------------------");
                if (!rs.isBeforeFirst()) { // Mengecek apakah hasilnya kosong
                    System.out.println("Tidak ada pengguna terdaftar.");
                    return;
                }
                while (rs.next()) {
                    String username = rs.getString("username");
                    String nama = rs.getString("nama");
                    String email = rs.getString("email");
                    String alamat = rs.getString("alamat");
                    String noTlp = rs.getString("telepon");

                    System.out.println("Username: " + username);
                    System.out.println("Nama: " + nama);
                    System.out.println("Email: " + email);
                    System.out.println("Alamat: " + alamat);
                    System.out.println("Nomor Telepon: " + noTlp);
                    System.out.println("-------------------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Mencetak stack trace untuk debugging
            System.out.println("Terjadi kesalahan saat mengakses database: " + e.getMessage());
        }
    }

    // Metode untuk menampilkan semua pengguna (untuk debugging)
    public void displayUsers() {
        System.out.println("Daftar Pengguna:");
        for (User  user : userList) {
            System.out.println("Username: " + user.getUsername());
        }
    }
    public Pelanggan getPelangganByUsername(String username) {
        for (User user : userList) {
            if (user instanceof Pelanggan && user.getUsername().equals(username)) {
                return (Pelanggan) user;
            }
        }
        return null;
    }
}