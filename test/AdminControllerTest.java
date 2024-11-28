package test;

import java.sql.*;
import org.junit.*;

import src.Controller.AdminController;

public class AdminControllerTest {

    private static Connection conn;
    private AdminController adminController;


    @BeforeClass
    public static void setupDatabase() {
        String url = "jdbc:mysql://localhost:3306/db_sars";
        String user = "root";
        String password = "";

        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Koneksi ke database berhasil!");

            // Membuat tabel productTest untuk pengujian
            String createTableSQL = "CREATE TABLE IF NOT EXISTS productTest (" +
                                    "id VARCHAR(50) PRIMARY KEY, " +
                                    "katalog VARCHAR(50), " +
                                    "nama VARCHAR(100), " +
                                    "harga INT, " +
                                    "stok INT)";
            Statement stmt = conn.createStatement();
            stmt.execute(createTableSQL);
            System.out.println("Tabel 'productTest' berhasil dibuat.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Inisialisasi AdminController sebelum tiap tes dijalankan
    @Before
    public void setup() {
        adminController = new AdminController();
    }

    // Test case untuk menambah produk iPhone
    @Test
    public void testAddProduct_Iphone() {
        String id = "17";
        String katalog = "iPhone";
        String nama = "iPhone 15";
        int harga = 500000;
        int stok = 3;

        // Menambah produk
        adminController.addProdukTest(id, katalog, nama, harga, stok);

        // Verifikasi produk ditambahkan
        try {
            String query = "SELECT * FROM productTest WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Assert.assertEquals(katalog, rs.getString("katalog"));
                Assert.assertEquals(nama, rs.getString("nama"));
                Assert.assertEquals(harga, rs.getInt("harga"));
                Assert.assertEquals(stok, rs.getInt("stok"));
            } else {
                Assert.fail("Produk tidak ditemukan");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail("Terjadi kesalahan saat mengakses database.");
        }
    }

    // Test case untuk menambah produk Android
    @Test
    public void testAddProduct_Android() {
        String id = "15";
        String katalog = "Android";
        String nama = "Samsung Galaxy S24";
        int harga = 500000;
        int stok = 15;

        // Menambah produk
        adminController.addProdukTest(id, katalog, nama, harga, stok);

        // Verifikasi produk ditambahkan
        try {
            String query = "SELECT * FROM productTest WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Assert.assertEquals(katalog, rs.getString("katalog"));
                Assert.assertEquals(nama, rs.getString("nama"));
                Assert.assertEquals(harga, rs.getInt("harga"));
                Assert.assertEquals(stok, rs.getInt("stok"));
            } else {
                Assert.fail("Produk tidak ditemukan");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail("Terjadi kesalahan saat mengakses database.");
        }
    }

    // Test case untuk menghapus produk
    @Test
    public void testRemoveProduct() {
        String id = "15";

        // Menghapus produk
        adminController.removeProduk(id);

        // Verifikasi produk sudah dihapus
        try {
            String query = "SELECT * FROM productTest WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Assert.fail("Produk masih ada setelah dihapus");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail("Terjadi kesalahan saat mengakses database.");
        }
    }

    // Test case untuk mengedit produk
    @Test
    public void testEditProduct() {
        String id = "P001";
        String nama = "Samsung Galaxy S24 Ultra";
        int harga = 500000;
        int stok = 5;

        // Mengedit produk
        adminController.editProdukTest(id, nama, harga, stok);

        // Verifikasi produk sudah diperbarui
        try {
            String query = "SELECT * FROM productTest WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Assert.assertEquals(nama, rs.getString("nama"));
                Assert.assertEquals(harga, rs.getInt("harga"));
                Assert.assertEquals(stok, rs.getInt("stok"));
            } else {
                Assert.fail("Produk tidak ditemukan setelah diedit");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail("Terjadi kesalahan saat mengakses database.");
        }
    }

    // Test case untuk mengambil produk berdasarkan katalog
    @Test
    public void testGetProductsByCatalog() {
        String catalogType = "iPhone";

        // Mendapatkan produk berdasarkan katalog
        adminController.displayProdukByCatalog(catalogType);

        // Verifikasi jika produk dengan katalog iPhone ada
        try {
            String query = "SELECT COUNT(*) AS productCount FROM productTest WHERE katalog = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, catalogType);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int productCount = rs.getInt("productCount");
                Assert.assertTrue(productCount > 0);
            } else {
                Assert.fail("Tidak ada produk yang ditemukan untuk katalog " + catalogType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail("Terjadi kesalahan saat mengakses database.");
        }
    }

    // Teardown method untuk menghapus tabel setelah semua tes selesai
    @AfterClass
    public static void tearDownDatabase() {
        try {
            // Drop tabel productTest setelah tes selesai
            String dropTableSQL = "DROP TABLE IF EXISTS productTest";
            Statement stmt = conn.createStatement();
            stmt.execute(dropTableSQL);
            System.out.println("Tabel 'productTest' berhasil dihapus.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();  // Pastikan koneksi ditutup setelah selesai
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
