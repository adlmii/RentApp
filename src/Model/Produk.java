package src.Model;

public class Produk {
    private String id;
    private String katalog;
    private String nama;
    private int harga;
    private int stok;

    public Produk(String id, String katalog, String nama, int harga, int stok) {
        this.id = id;
        this.katalog = katalog;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public String getId() {
        return id;
    }

    public String getKatalog() {
        return katalog;
    }

    public void setKatalog(String katalog) {
        this.katalog = katalog;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    @Override
    public String toString() {
        return "Produk ID\t: " + id + "\nKatalog\t\t: " + katalog + "\nNama\t\t: " + nama + "\nHarga\t\t: " + harga + "\nStok\t\t: " + stok + "\n----------------------------";
    }
}
