package com.mycompany.smartlibrary7;

public class BukuCetak extends Koleksi implements DapatDipinjam, DapatDinilai {
    private int jumlahHalaman;

    public BukuCetak(String judul, String penulis, int tahunTerbit, int jumlahHalaman) {
        super(judul, penulis, tahunTerbit);
        this.jumlahHalaman = jumlahHalaman;
    }

    public int getJumlahHalaman() {
        return jumlahHalaman;
    }

    public void setJumlahHalaman(int jumlahHalaman) {
        this.jumlahHalaman = jumlahHalaman;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Jumlah Halaman : " + jumlahHalaman + " halaman");
    }

    @Override
    public void caraPinjam() {
        System.out.println("Pinjam buku fisik ini langsung di meja sirkulasi.");
    }

    // Wajib mengimplementasikan abstract method dari Abstract Class Koleksi
    @Override
    public void hitungDendaKeterlambatan() {
        System.out.println("-> Aturan Denda: Rp 2.000 / hari keterlambatan.");
    }

    // Wajib mengimplementasikan method dari Interface DapatDipinjam
    @Override
    public void prosesPinjamFisik() {
        System.out.println("-> [PROSES PINJAM] Anggota menyerahkan buku fisik & kartu perpus ke Kasir.");
    }

    // Wajib mengimplementasikan method dari Interface DapatDinilai
    @Override
    public void beriRating(int bintang) {
        System.out.println("-> [ULASAN BUKU] Buku fisik ini mendapat rating " + bintang + "/5 Bintang.");
    }
}