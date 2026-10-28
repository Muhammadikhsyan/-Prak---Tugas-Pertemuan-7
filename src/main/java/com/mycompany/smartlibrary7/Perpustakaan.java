package com.mycompany.smartlibrary7;

import java.util.ArrayList;
import java.util.HashMap;

public class Perpustakaan {
    private String namaPerpus;
    private RuangBaca ruangBacaUtama; // Komposisi
    private ArrayList<Koleksi> daftarKoleksi; // Agregasi
    private HashMap<String, Anggota> dataAnggota; // Agregasi

    public Perpustakaan(String namaPerpus) {
        this.namaPerpus = namaPerpus;
        this.ruangBacaUtama = new RuangBaca("Ruang Baca Utama", 50); // Komposisi internal
        this.daftarKoleksi = new ArrayList<>();
        this.dataAnggota = new HashMap<>();
    }

    public void bukaPerpustakaan() {
        System.out.println("\n*** Menginisialisasi Sistem " + this.namaPerpus + " ***");
        ruangBacaUtama.bukaRuangan();
    }

    public void tambahKoleksi(Koleksi k) {
        daftarKoleksi.add(k);
        System.out.println("Sukses! Koleksi ditambahkan. (Total Koleksi: " + daftarKoleksi.size() + ")");
    }

    public void tambahAnggota(Anggota a) {
        dataAnggota.put(a.getNpm(), a);
        System.out.println("Berhasil menambahkan anggota!");
    }

    public void lihatSemuaKoleksi() {
        if (daftarKoleksi.isEmpty()) {
            System.out.println("Belum ada koleksi yang tersimpan.");
            return;
        }
        for (int i = 0; i < daftarKoleksi.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarKoleksi.get(i).tampilkanInfo();
            System.out.println();
        }
    }

    public void cariKoleksi(String judul) throws KoleksiTidakDitemukanException {
        System.out.println("Mencari Koleksi dengan Judul: " + judul);
        boolean ditemukan = false;
        for (Koleksi k : daftarKoleksi) {
            if (k.getJudul().equalsIgnoreCase(judul)) {
                k.tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            throw new KoleksiTidakDitemukanException("Gagal: Koleksi dengan judul '" + judul + "' tidak terdaftar di sistem.");
        }
    }

    public void cariAnggota(String npm) {
        if (dataAnggota.containsKey(npm)) {
            System.out.println("Ditemukan! Nama: " + dataAnggota.get(npm).getNama());
        } else {
            System.out.println("Anggota dengan NPM tersebut tidak terdaftar.");
        }
    }

    public void lihatSemuaAnggota() {
        System.out.println("Total Anggota Terdaftar: " + dataAnggota.size());
        for (Anggota a : dataAnggota.values()) {
            System.out.println("- NPM: " + a.getNpm() + " | Nama: " + a.getNama());
        }
    }

    public Anggota getAnggota(String npm) { 
        return dataAnggota.get(npm); 
    }

    public Koleksi getKoleksiInt(int index) { 
        return daftarKoleksi.get(index); 
    }

    public int getJumlahKoleksi() { 
        return daftarKoleksi.size(); 
    }
}