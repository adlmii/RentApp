package src.Controller;

import src.Config.koneksi;
import src.Model.Produk;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminController {
    
    private Connection conn;

    public AdminController() {
        conn = koneksi.getConnection();
    }
    
    // Menambah produk ke database
    public void addProduk(String id, String katalog, String nama, int harga, int stok) {
        String sql = "INSERT INTO produk (id, katalog, nama, harga, stok) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, katalog);
            pstmt.setString(3, nama);
            pstmt.setInt(4, harga);
            pstmt.setInt(5, stok);
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Produk berhasil ditambahkan");
            }
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan produk: " + e.getMessage());
        }
    }

    // Menambah produk ke database
    public void addProdukTest(String id, String katalog, String nama, int harga, int stok) {
        String sql = "INSERT INTO productTest (id, katalog, nama, harga, stok) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, katalog);
            pstmt.setString(3, nama);
            pstmt.setInt(4, harga);
            pstmt.setInt(5, stok);
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Produk berhasil ditambahkan");
            }
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan produk: " + e.getMessage());
        }
    }
    
    // Menghapus produk dari database
    public void removeProduk(String id) {
        String sql = "DELETE FROM produk WHERE id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Produk berhasil dihapus");
            } else {
                System.out.println("Produk dengan ID " + id + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println("Gagal menghapus produk: " + e.getMessage());
        }
    }
    
    // Mengedit produk di database
    public void editProduk(String id, String nama, int harga, int stok) {
        String sql = "UPDATE produk SET nama = ?, harga = ?, stok = ? WHERE id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nama);
            pstmt.setInt(2, harga);
            pstmt.setInt(3, stok);
            pstmt.setString(4, id);
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Produk berhasil diedit");
            } else {
                System.out.println("Produk dengan ID " + id + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengedit produk: " + e.getMessage());
        }
    }

    public void editProdukTest(String id, String nama, int harga, int stok) {
        String sql = "UPDATE productTest SET nama = ?, harga = ?, stok = ? WHERE id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nama);
            pstmt.setInt(2, harga);
            pstmt.setInt(3, stok);
            pstmt.setString(4, id);
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Produk berhasil diedit");
            } else {
                System.out.println("Produk dengan ID " + id + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengedit produk: " + e.getMessage());
        }
    }

    // Mengambil semua produk dari database
    public List<Produk> getAllProduk() {
        List<Produk> produkList = new ArrayList<>();
        String sql = "SELECT * FROM produk";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while (rs.next()) {
                String id = rs.getString("id");
                String katalog = rs.getString("katalog");
                String nama = rs.getString("nama");
                int harga = rs.getInt("harga");
                int stok = rs.getInt("stok");
                Produk produk = new Produk(id, katalog, nama, harga, stok);
                produkList.add(produk);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data produk: " + e.getMessage());
        }
        
        return produkList;
    }

    public List<Produk> getProdukByCatalog(String catalogType) {
        List<Produk> produkList = new ArrayList<>();
        String sql = "SELECT * FROM produk WHERE katalog = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, catalogType);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                String katalog = rs.getString("katalog");
                String nama = rs.getString("nama");
                int harga = rs.getInt("harga");
                int stok = rs.getInt("stok");
                produkList.add(new Produk(id, katalog, nama, harga, stok));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving products: " + e.getMessage());
        }

        return produkList;
    }
    
    public void displayAllProduk() {
        List<Produk> produkList = getAllProduk();
        
        if (produkList.isEmpty()) {
            System.out.println("Tidak ada produk yang tersedia.");
        } else {
            for (Produk produk : produkList) {
                System.out.println("ID: " + produk.getId() +
                                   ", Katalog: " + produk.getKatalog() +
                                   ", Nama: " + produk.getNama() +
                                   ", Harga: " + produk.getHarga() +
                                   ", Stok: " + produk.getStok());
            }
        }
    }

    public void displayProdukByCatalog(String catalogType) {
        List<Produk> produkList = getProdukByCatalog(catalogType);
        
        if (produkList.isEmpty()) {
            System.out.println("Tidak ada produk yang tersedia pada katalog " + catalogType + " catalog.");
        } else {
            for (Produk produk : produkList) {
                System.out.println("ID: " + produk.getId() +
                                   ", Katalog: " + produk.getKatalog() +
                                   ", Nama: " + produk.getNama() +
                                   ", Harga: " + produk.getHarga() +
                                   ", Stok: " + produk.getStok());
            }
        }
    }

    public boolean decreaseProductStock(String productId) {
        String sql = "UPDATE produk SET stok = stok - 1 WHERE id = ? AND stok > 0"; // Mengurangi stok produk
    
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, productId);
            int rowsUpdated = pstmt.executeUpdate();
            
            if (rowsUpdated > 0) {
                // System.out.println("Stok produk dengan ID " + productId + " berhasil dikurangi.");
                return true;
            } else {
                System.out.println("Stok produk dengan ID " + productId + " tidak cukup atau produk tidak ditemukan.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Gagal memperbarui stok produk: " + e.getMessage());
            return false;
        }
    }
    

}

