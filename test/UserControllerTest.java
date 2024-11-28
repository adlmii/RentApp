package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import src.Controller.UserController;

import org.junit.After;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.*;

public class UserControllerTest {

    private UserController userController;
    private Connection conn;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalSystemOut = System.out;

    @Before
    public void setUp() throws Exception {
        // Set up koneksi ke database untuk pengujian
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_sars", "root", "");  // Sesuaikan dengan kredensial Anda
        userController = new UserController();

        // Menyiapkan output stream untuk menangkap hasil print ke console
        System.setOut(new PrintStream(outputStream));

        // Membuat tabel userTest sebelum tes dimulai
        String createTableSQL = "CREATE TABLE IF NOT EXISTS userTest ("
                + "username VARCHAR(255) PRIMARY KEY, "
                + "password VARCHAR(255) NOT NULL, "
                + "nama VARCHAR(255), "
                + "email VARCHAR(255), "
                + "alamat VARCHAR(255), "
                + "telepon VARCHAR(15)"
                + ");";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createTableSQL);
        }

        // Menambahkan data test ke tabel userTest
        String sqlInsert = "INSERT INTO userTest (username, password, nama, email, alamat, telepon) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {
            stmt.setString(1, "user1");
            stmt.setString(2, "password123");
            stmt.setString(3, "User One");
            stmt.setString(4, "user1@example.com");
            stmt.setString(5, "Alamat 1");
            stmt.setString(6, "081234567890");
            stmt.executeUpdate();

            stmt.setString(1, "user2");
            stmt.setString(2, "password456");
            stmt.setString(3, "User Two");
            stmt.setString(4, "user2@example.com");
            stmt.setString(5, "Alamat 2");
            stmt.setString(6, "081234567891");
            stmt.executeUpdate();
        }
    }

    @Test
    public void testDisplayAllUsers() {
        // Panggil metode untuk menampilkan semua pengguna
        userController.displayAllUsersTest();
    
        // Ambil output yang dihasilkan
        String output = outputStream.toString();
        
        // Debug: tampilkan output yang dihasilkan
        System.out.println("Output yang dihasilkan:");
        System.out.println(output);  // Menampilkan output yang dihasilkan ke konsol
    
        // Verifikasi output (mengandung informasi pengguna yang ditambahkan)
        assertTrue(output.contains("Username: user1"));
        assertTrue(output.contains("Nama: User One"));
        assertTrue(output.contains("Email: user1@example.com"));
        assertTrue(output.contains("Nomor Telepon: 081234567890"));
    
        assertTrue(output.contains("Username: user2"));
        assertTrue(output.contains("Nama: User Two"));
        assertTrue(output.contains("Email: user2@example.com"));
        assertTrue(output.contains("Nomor Telepon: 081234567891"));
    }
    

    @After
    public void tearDown() throws Exception {
        // Menghapus tabel userTest setelah tes selesai
        String dropTableSQL = "DROP TABLE IF EXISTS userTest";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(dropTableSQL);
        }

        // Menutup koneksi setelah tes selesai
        if (conn != null) {
            conn.close();
        }

        // Mengembalikan System.out ke keadaan semula
        System.setOut(originalSystemOut);
    }
}
