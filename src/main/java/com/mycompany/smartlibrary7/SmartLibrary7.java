package com.mycompany.smartlibrary7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SmartLibrary7 {

    public static void cariKoleksi(String judul, ArrayList<Koleksi> daftarKoleksi) throws KoleksiTidakDitemukanException {
        System.out.println("Mencari Koleksi dengan Judul: " + judul);
        boolean ditemukan = false;
        for (int i = 0; i < daftarKoleksi.size(); i++) {
            if (daftarKoleksi.get(i).getJudul().equalsIgnoreCase(judul)) {
                daftarKoleksi.get(i).tampilkanInfo();
                ditemukan = true;
                break;
            }
        }
        if (!ditemukan) {
            throw new KoleksiTidakDitemukanException("Gagal: Koleksi dengan judul '" + judul + "' tidak terdaftar di sistem.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        ArrayList<Koleksi> daftarKoleksi = new ArrayList<>();
        
        HashMap<String, String> dataAnggota = new HashMap<>();

        boolean isRunning = true;

        System.out.println("==================================");
        System.out.println("   Selamat Datang di Smart Library");
        System.out.println("==================================");

        while (isRunning) {
            System.out.println("\n=== Menu Utama ===");
            System.out.println("1. Tambah Koleksi");
            System.out.println("2. Lihat Daftar Koleksi");
            System.out.println("3. Cari Koleksi (Demo Custom Exception)");
            System.out.println("4. Manajemen Anggota (Demo HashMap)");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu (1-5): ");

            try {
                int pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1:
                        System.out.println("\n--- Pilih Jenis Koleksi ---");
                        System.out.println("1. Buku Cetak");
                        System.out.println("2. E-Book");
                        System.out.print("Pilih (1-2): ");
                        int jenis = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Masukkan Judul        : ");
                        String judul = scanner.nextLine();
                        System.out.print("Masukkan Penulis      : ");
                        String penulis = scanner.nextLine();
                        System.out.print("Masukkan Tahun Terbit : ");
                        int tahunTerbit = scanner.nextInt();

                        if (jenis == 1) {
                            System.out.print("Masukkan Jumlah Halaman: ");
                            int jumlahHalaman = scanner.nextInt();
                            scanner.nextLine();

                            daftarKoleksi.add(new BukuCetak(judul, penulis, tahunTerbit, jumlahHalaman));
                            System.out.println("Sukses! Buku Cetak ditambahkan. (Total: " + daftarKoleksi.size() + ")");

                        } else if (jenis == 2) {
                            System.out.print("Masukkan Ukuran File (MB): ");
                            double ukuranFileMB = scanner.nextDouble();
                            scanner.nextLine();

                            daftarKoleksi.add(new Ebook(judul, penulis, tahunTerbit, ukuranFileMB));
                            System.out.println("Sukses! E-Book ditambahkan. (Total: " + daftarKoleksi.size() + ")");

                        } else {
                            System.out.println("Pilihan jenis koleksi tidak valid.");
                        }
                        break;

                    case 2:
                        System.out.println("\n--- Daftar Koleksi di Perpustakaan ---");
                        if (daftarKoleksi.isEmpty()) {
                            System.out.println("Belum ada koleksi yang tersimpan.");
                        } else {
                            for (int i = 0; i < daftarKoleksi.size(); i++) {
                                System.out.print((i + 1) + ". ");
                                daftarKoleksi.get(i).tampilkanInfo();
                                System.out.println();
                            }
                        }
                        break;

                    case 3:
                        System.out.println("\n--- Fitur Cari Koleksi ---");
                        System.out.print("Masukkan Judul: ");
                        String kataKunci = scanner.nextLine();
                        
                        try {
                            cariKoleksi(kataKunci, daftarKoleksi);
                        } catch (KoleksiTidakDitemukanException e) {
                            System.out.println("Peringatan: " + e.getMessage());
                        } finally {
                            System.out.println("-> Sesi pencarian selesai dieksekusi.");
                        }
                        break;

                    case 4:
                        System.out.println("\n--- Kelola Anggota (HashMap) ---");
                        System.out.println("1. Tambah Anggota Baru");
                        System.out.println("2. Cari Anggota via NPM");
                        System.out.println("3. Tampilkan Semua Anggota");
                        System.out.print("Pilih: ");
                        int menuAnggota = scanner.nextInt();
                        scanner.nextLine();

                        if (menuAnggota == 1) {
                            System.out.print("Masukkan NPM (Key): ");
                            String npm = scanner.nextLine();
                            System.out.print("Masukkan Nama Lengkap (Value): ");
                            String nama = scanner.nextLine();

                            dataAnggota.put(npm, nama);
                            System.out.println("Berhasil menambahkan anggota!");
                        } else if (menuAnggota == 2) {
                            System.out.print("Masukkan NPM yang dicari: ");
                            String searchNpm = scanner.nextLine();

                            if (dataAnggota.containsKey(searchNpm)) {
                                System.out.println("Ditemukan! Nama: " + dataAnggota.get(searchNpm));
                            } else {
                                System.out.println("Anggota dengan NPM tersebut tidak terdaftar.");
                            }
                        } else if (menuAnggota == 3) {
                            System.out.println("Total Anggota Terdaftar: " + dataAnggota.size());
                            for (String keyNpm : dataAnggota.keySet()) {
                                System.out.println("- NPM: " + keyNpm + " | Nama: " + dataAnggota.get(keyNpm));
                            }
                        } else {
                            System.out.println("Pilihan menu anggota tidak valid.");
                        }
                        break;

                    case 5:
                        System.out.println("Terima kasih telah menggunakan Smart Library!");
                        isRunning = false;
                        break;

                    default:
                        System.out.println("Pilihan menu tidak valid.");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("TERJADI KESALAHAN: Harap masukkan format input berupa angka!");
                scanner.nextLine(); 
            } catch (Exception e) {
                System.out.println("TERJADI KESALAHAN SISTEM: " + e.getMessage());
            }
        }

        scanner.close();
    }
}