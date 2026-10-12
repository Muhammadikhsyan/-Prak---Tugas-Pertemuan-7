package com.mycompany.smartlibrary7;

public abstract class Koleksi {
    // Abstract class dapat mendeklarasikan variabel biasa (bisa diubah nilainya)
    private String judul;
    private String penulis;
    private int tahunTerbit;

    public static int totalKoleksi = 0;

    public Koleksi(String judul, String penulis, int tahunTerbit) {
        this.judul = judul;
        this.penulis = penulis;
        this.tahunTerbit = tahunTerbit;
        totalKoleksi++;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public int getTahunTerbit() {
        return tahunTerbit;
    }

    public void setTahunTerbit(int tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }

    // Method biasa yang memiliki implementasi/blok logika
    public void tampilkanInfo() {
        System.out.println("Judul        : " + judul);
        System.out.println("Penulis      : " + penulis);
        System.out.println("Tahun Terbit : " + tahunTerbit);
    }

    public void caraPinjam() {
        System.out.println("Prosedur peminjaman standar perpustakaan.");
    }

    // Abstract Method: Hanya deklarasi tanpa tubuh (tanpa kurung kurawal {})
    // Menjadi aturan wajib yang harus di-override oleh seluruh Subclass
    public abstract void hitungDendaKeterlambatan();
}