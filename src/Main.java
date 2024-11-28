package src;

import src.Controller.*;
import src.Model.*;
import src.View.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<Pemesanan> daftarPemesanan = new ArrayList<>();
        
        // Inisialisasi
        AdminController adminController = new AdminController();
        UserController userController = new UserController();
        ProductView productView = new ProductView(adminController);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("=== Menu Aplikasi ===");
            System.out.println("1. Login");
            System.out.println("2. Keluar");
            System.out.print("Pilih opsi: ");
            
            int option = 0;
            boolean validInput = false;
            
            // Verifikasi Input
            while (!validInput) {
                try {
                    option = scanner.nextInt();
                    scanner.nextLine(); // Membersihkan Buffer
                    validInput = true; // Keluar dari loop
                } catch (InputMismatchException e) {
                    System.out.println("Input tidak valid. Harap masukkan angka yang sesuai.");
                    scanner.nextLine(); // Membersihkan Buffer
                }
            }

            switch (option) {
                case 1: // Login
                    System.out.print("Masukkan Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Masukkan Password: ");
                    String password = scanner.nextLine();

                    if (userController.login(username, password)) {
                        boolean userSession = true;

                        if (username.equals("admin")) {
                            // Admin menu
                            while (userSession) {
                                System.out.println("\n=== Menu Admin ===");
                                System.out.println("1. Lihat Semua Pengguna");
                                System.out.println("2. Kelola Produk");
                                System.out.println("3. Keluar");
                                System.out.print("Pilih opsi: ");
                                
                                int adminOption = 0;
                                validInput = false;
                                
                                // Verifikasi Input
                                while (!validInput) {
                                    try {
                                        adminOption = scanner.nextInt();
                                        scanner.nextLine(); // Membersihkan Buffer
                                        validInput = true; // Keluar dari loop
                                    } catch (InputMismatchException e) {
                                        System.out.println("Input tidak valid. Harap masukkan angka yang sesuai.");
                                        scanner.nextLine(); // Membersihkan Buffer
                                    }
                                }

                                switch (adminOption) {
                                    case 1:
                                        userController.displayAllUsers();
                                        break;
                                    case 2: // Kelola Produk
                                        manageProducts(scanner, adminController, productView);
                                        break;
                                    case 3:
                                        userSession = false;
                                        System.out.println("Anda telah logout dari admin.");
                                        System.out.println();
                                        break;
                                    default:
                                        System.out.println("Opsi tidak valid. Silakan coba lagi.");
                                        System.out.println();
                                        break;
                                }
                            }
                        } else {
                            // User menu
                            while (userSession) {
                                System.out.println("\n=== Menu Pengguna ===");
                                System.out.println("1. Edit Profil");
                                System.out.println("2. Lihat Katalog");
                                System.out.println("3. Logout");
                                System.out.print("Pilih opsi: ");
                                
                                int userOption = 0;
                                validInput = false;

                                // Verifikasi Input
                                while (!validInput) {
                                    try {
                                        userOption = scanner.nextInt();
                                        scanner.nextLine(); // Membersihkan Buffer
                                        validInput = true; // Keluar dari loop
                                    } catch (InputMismatchException e) {
                                        System.out.println("Input tidak valid. Harap masukkan angka yang sesuai.");
                                        scanner.nextLine(); // Membersihkan Buffer
                                    }
                                }

                                switch (userOption) {
                                    case 1:
                                        System.out.println("Fitur edit profil belum tersedia.");
                                        break;
                                    case 2:
                                        viewCatalog(scanner, adminController, productView, userController, username, daftarPemesanan);
                                        break;
                                    case 3:
                                        userSession = false;
                                        System.out.println("Anda telah logout.");
                                        System.out.println();
                                        break;
                                    default:
                                        System.out.println("Opsi tidak valid. Silakan coba lagi.");
                                        System.out.println();
                                        break;
                                }
                            }
                        }
                    } else {
                        System.out.println("Login gagal. Silakan coba lagi.");
                        System.out.println();
                    }
                    break;
                case 2: // Keluar
                    running = false;
                    System.out.println("Terima kasih telah menggunakan aplikasi.");
                    break;
                default:
                    System.out.println("Opsi tidak valid. Silakan coba lagi.");
                    break;
            }
        }
    }

    private static void manageProducts(Scanner scanner, AdminController adminController, ProductView productView) {
        boolean productSession = true;

        while (productSession) {
            System.out.println("\n=== Menu Kelola Produk ===");
            System.out.println("1. Lihat Semua Produk");
            System.out.println("2. Tambah Produk");
            System.out.println("3. Hapus Produk");
            System.out.println("4. Edit Produk");
            System.out.println("5. Kembali");
            System.out.print("Pilih opsi: ");
            
            int productOption = 0;
            boolean validInput = false;

            // Verifikasi Input
            while (!validInput) {
                try {
                    productOption = scanner.nextInt();
                    scanner.nextLine(); // Membersihkan Buffer
                    validInput = true; // Keluar dari loop
                } catch (InputMismatchException e) {
                    System.out.println("Input tidak valid. Harap masukkan angka yang sesuai.");
                    scanner.nextLine(); // Membersihkan Buffer
                }
            }

            switch (productOption) {
                case 1:
                    productView.displayAllProduk();
                    break;
                case 2:
                    System.out.print("Masukkan Katalog (iPhone/Android): ");
                    String katalog = scanner.nextLine();
                    System.out.print("Masukkan ID Produk: ");
                    String id = scanner.nextLine();
                    System.out.print("Masukkan Nama Produk: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan Harga Produk: ");
                    int harga = 0;
                    validInput = false;

                    // Verifikasi Input
                    while (!validInput) {
                        try {
                            harga = scanner.nextInt();
                            scanner.nextLine(); // Membersihkan Buffer
                            validInput = true; // Keluar dari loop
                        } catch (InputMismatchException e) {
                            System.out.println("Input tidak valid. Harap masukkan harga dalam format angka.");
                            scanner.nextLine(); // Membersihkan Buffer
                        }
                    }

                    System.out.print("Masukkan Stok Produk: ");
                    int stok = 0;
                    validInput = false;

                    // Verifikasi Input
                    while (!validInput) {
                        try {
                            stok = scanner.nextInt();
                            scanner.nextLine(); // Membersihkan Buffer
                            validInput = true; // Keluar dari loop
                        } catch (InputMismatchException e) {
                            System.out.println("Input tidak valid. Harap masukkan stok dalam format angka.");
                            scanner.nextLine(); // Membersihkan Buffer
                        }
                    }

                    adminController.addProduk(id, katalog, nama, harga, stok);
                    break;
                case 3:
                    System.out.print("Masukkan ID Produk yang ingin dihapus: ");
                    String idToDelete = scanner.nextLine();
                    adminController.removeProduk(idToDelete);
                    break;
                case 4:
                    System.out.print("Masukkan ID Produk yang ingin diedit: ");
                    String idToEdit = scanner.nextLine();
                    System.out.print("Masukkan Nama Baru: ");
                    String newName = scanner.nextLine();
                    System.out.print("Masukkan Harga Baru: ");
                    int newPrice = 0;
                    validInput = false;

                    // Verifikasi Input
                    while (!validInput) {
                        try {
                            newPrice = scanner.nextInt();
                            scanner.nextLine(); // Membersihkan Buffer
                            validInput = true; // Keluar dari Loop
                        } catch (InputMismatchException e) {
                            System.out.println("Input tidak valid. Harap masukkan harga dalam format angka.");
                            scanner.nextLine(); // Membersihkan Buffer
                        }
                    }

                    System.out.print("Masukkan Stok Baru: ");
                    int newStock = 0;
                    validInput = false;

                    // Verifikasi Input
                    while (!validInput) {
                        try {
                            newStock = scanner.nextInt();
                            scanner.nextLine(); // Membersihkan Buffer
                            validInput = true; // Keluar dari Loop
                        } catch (InputMismatchException e) {
                            System.out.println("Input tidak valid. Harap masukkan stok dalam format angka.");
                            scanner.nextLine(); // Membersihkan Buffer
                        }
                    }

                    adminController.editProduk(idToEdit, newName, newPrice, newStock);
                    break;
                case 5:
                    productSession = false;
                    break;
                default:
                    System.out.println("Opsi tidak valid. Silakan coba lagi.");
                    break;
            }
        }
    }

    private static void viewCatalog(Scanner scanner, AdminController adminController, ProductView productView, UserController userController, String username, List<Pemesanan> daftarPemesanan) {
        boolean catalogSession = true;

        while (catalogSession) {
            System.out.println("\n=== Katalog ===");
            System.out.println("1. iPhone");
            System.out.println("2. Android");
            System.out.println("3. Kembali");
            System.out.print("Pilih opsi: ");
        
            int catalogOption = 0;
            boolean validInput = false;

            // Verifikasi Input
            while (!validInput) {
                try {
                    catalogOption = scanner.nextInt();
                    scanner.nextLine(); // Membersihkan Buffer
                    validInput = true; // Keluar dari loop jika input valid
                } catch (InputMismatchException e) {
                    System.out.println("Input tidak valid. Harap masukkan angka yang sesuai.");
                    scanner.nextLine(); // Membersihkan Buffer dari input yang salah
                }
            }

            switch (catalogOption) {
                case 1:
                case 2:
                    String catalogType = (catalogOption == 1) ? "iPhone" : "Android";
                    productView.displayProdukByCatalog(catalogType);

                    System.out.print("Apakah Anda ingin menyewa produk? (ya/tidak): ");
                    String sewaOption = scanner.nextLine();
                    if (sewaOption.equalsIgnoreCase("ya")) {
                        System.out.print("Masukkan ID produk yang ingin disewa: ");
                        String idProdukDisewa = scanner.nextLine();
                        System.out.print("Masukkan tanggal pinjam (format: YYYY-MM-DD): ");
                        String tglPinjam = scanner.nextLine();
                        
                        if (!isValidDate(tglPinjam)) {
                            System.out.println("Format tanggal pinjam tidak valid.");
                            return;  // Keluar atau minta pengguna memasukkan ulang
                        }

                        System.out.print("Masukkan tanggal kembali (format: YYYY-MM-DD): ");
                        String tglKembali = scanner.nextLine();
                        
                        if (!isValidDate(tglKembali)) {
                            System.out.println("Format tanggal kembali tidak valid.");
                            return;  // Keluar atau minta pengguna memasukkan ulang
                        }

                        // Mengurangi stok
                        boolean stockUpdated = adminController.decreaseProductStock(idProdukDisewa);

                        if (stockUpdated) {
                            // Membuat pemesanan baru
                            Pemesanan pemesananBaru = new Pemesanan(idProdukDisewa, username, catalogType, tglPinjam, tglKembali);
                            daftarPemesanan.add(pemesananBaru);
                            System.out.println("Pemesanan untuk produk dengan id '" + idProdukDisewa + "' berhasil dibuat.");
                        } else {
                            System.out.println("Gagal memperbarui stok. Pemesanan tidak dapat dilakukan.");
                        }
                    }
                break;
                case 3:
                    catalogSession = false;
                break;
                default:
                    System.out.println("Opsi tidak valid. Silakan coba lagi.");
                break;
            }
        }
    }

        // Validasi format tanggal
    private static boolean isValidDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);  // Menonaktifkan parsing yang longgar
        try {
            sdf.parse(dateStr);
            return true;  // Tanggal valid
        } catch (Exception e) {
            return false;  // Tanggal tidak valid
        }
    }
}
