package com.mycompany.smartlibrary7;

public abstract class Koleksi {
    private String judul;
    private String penulis;
    private int tahunTerbit;

    public static int totalKoleksi = 0;

    public Koleksi(String judul, String penulis, int tahunTerbit) throws Exception {
        if (tahunTerbit < 0) {
            throw new Exception("Tahun terbit tidak valid (tidak boleh nol atau negatif).");
        }
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

    public void tampilkanInfo() {
        System.out.println("Judul        : " + judul);
        System.out.println("Penulis      : " + penulis);
        System.out.println("Tahun Terbit : " + tahunTerbit);
    }

    public void caraPinjam() {
        System.out.println("Prosedur peminjaman standar perpustakaan.");
    }

    public abstract void hitungDendaKeterlambatan();
}