package com.mycompany.smartlibrary7;

public class Ebook extends Koleksi implements DapatDinilai {
    private double ukuranFileMB;

    public Ebook(String judul, String penulis, int tahunTerbit, double ukuranFileMB) throws Exception {
        super(judul, penulis, tahunTerbit);
        if (ukuranFileMB < 0) {
            throw new Exception("Ukuran file tidak boleh negatif.");
        }
        this.ukuranFileMB = ukuranFileMB;
    }

    public double getUkuranFileMB() {
        return ukuranFileMB;
    }

    public void setUkuranFileMB(double ukuranFileMB) {
        this.ukuranFileMB = ukuranFileMB;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Ukuran File    : " + ukuranFileMB + " MB");
    }

    @Override
    public void caraPinjam() {
        System.out.println("Akses dan unduh e-book ini melalui aplikasi digital library.");
    }

    @Override
    public void hitungDendaKeterlambatan() {
        System.out.println("-> Aturan Denda: Tidak ada denda (Akses otomatis dicabut jika masa pinjam habis).");
    }

    @Override
    public void beriRating(int bintang) {
        System.out.println("-> [ULASAN E-BOOK] Buku digital ini mendapat rating " + bintang + "/5 Bintang di aplikasi.");
    }
}