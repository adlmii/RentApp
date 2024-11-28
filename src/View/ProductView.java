package src.View;

import src.Controller.AdminController;
import src.Model.Produk;
import java.util.List;

public class ProductView {

    private AdminController adminController;

    // Konstruktor untuk menerima AdminController
    public ProductView(AdminController adminController) {
        this.adminController = adminController;
    }

    // Menampilkan semua produk
    public void displayAllProduk() {
        List<Produk> produkList = adminController.getAllProduk();
        
        if (produkList.isEmpty()) {
            System.out.println("Tidak ada produk yang tersedia.");
        } else {
            System.out.println("-------------------------------");
            for (Produk produk : produkList) {
                System.out.println("ID: " + produk.getId() + "\n" +
                                   "Katalog: " + produk.getKatalog() + "\n" +
                                   "Nama: " + produk.getNama() + "\n" +
                                   "Harga: " + produk.getHarga() + "\n" +
                                   "Stok: " + produk.getStok() + "\n" +
                                   "-------------------------------");
            }
        }
    }

    // Menampilkan produk berdasarkan katalog
    public void displayProdukByCatalog(String catalogType) {
        List<Produk> produkList = adminController.getProdukByCatalog(catalogType);
        
        if (produkList.isEmpty()) {
            System.out.println("Tidak ada produk yang tersedia pada katalog " + catalogType + ".");
        } else {
            System.out.println("-------------------------------");
            for (Produk produk : produkList) {
                System.out.println("ID: " + produk.getId() + "\n" +
                                   "Katalog: " + produk.getKatalog() + "\n" +
                                   "Nama: " + produk.getNama() + "\n" +
                                   "Harga: " + produk.getHarga() + "\n" +
                                   "Stok: " + produk.getStok() + "\n" + 
                                   "-------------------------------");
            }
        }
    }
}
