package src.Model;

public class Pelanggan extends User {
    public String nama;
    private String alamat;
    private String email;
    private String noTlp;

    // Konstruktor untuk inisialisasi Pelanggan
    public Pelanggan(String username, String password, String nama, String alamat, String email, String noTlp) {
        super(username, password);
        this.nama = nama;
        this.alamat = alamat;
        this.email = email;
        this.noTlp = noTlp;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoTlp() {
        return noTlp;
    }

    public void setNoTlp(String noTlp) {
        this.noTlp = noTlp;
    }
}