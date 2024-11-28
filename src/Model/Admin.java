package src.Model;

public class Admin extends User {
    private String nama; // Nama admin

    // Konstruktor untuk inisialisasi Admin
    public Admin(String username, String password, String nama) {
        super(username, password);
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}