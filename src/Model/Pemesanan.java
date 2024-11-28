package src.Model;

public class Pemesanan {
    private String id;
    private String nama;
    private String item;
    private String tglPinjam;
    private String tglKembali;

    // Constructor
    public Pemesanan(String id, String nama, String item, String tglPinjam, String tglKembali) {
        this.id = id;
        this.nama = nama;
        this.item = item;
        this.tglPinjam = tglPinjam;
        this.tglKembali = tglKembali;
    }

    // Getter dan Setter
    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getItem() {
        return item;
    }

    public String getTglPinjam() {
        return tglPinjam;
    }

    public String getTglKembali() {
        return tglKembali;
    }

    @Override
    public String toString() {
        return "Pemesanan\n" +
                "id='" + id + '\'' + '\n'+
                "Nama= " + nama +'\n'+
                "Alamat= " + 
                "item=" + item +'\n'+
                "tanggal Pinjam= '" + tglPinjam + '\'' + '\n'+
                "tanggal Kembali= '" + tglKembali + '\''
                ;
    }


}