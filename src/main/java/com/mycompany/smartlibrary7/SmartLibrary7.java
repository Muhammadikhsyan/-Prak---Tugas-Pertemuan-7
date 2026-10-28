package com.mycompany.smartlibrary7;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SmartLibrary7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Perpustakaan perpusUnila = new Perpustakaan("Smart Library Unila");
        perpusUnila.bukaPerpustakaan();

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n=== Menu Utama ===");
            System.out.println("1. Tambah Koleksi");
            System.out.println("2. Lihat Daftar Koleksi");
            System.out.println("3. Cari Koleksi");
            System.out.println("4. Kelola Anggota");
            System.out.println("5. Peminjaman (Demo Association)");
            System.out.println("6. Keluar");
            System.out.print("Pilih Menu: 1-6: ");

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
                        System.out.print("Masukkan Pengarang    : ");
                        String pengarang = scanner.nextLine();
                        System.out.print("Masukkan Tahun Terbit : ");
                        int tahun = scanner.nextInt();

                        if (jenis == 1) {
                            System.out.print("Masukkan Jumlah Halaman: ");
                            int halaman = scanner.nextInt();
                            scanner.nextLine();
                            perpusUnila.tambahKoleksi(new BukuCetak(judul, pengarang, tahun, halaman));
                        } else if (jenis == 2) {
                            System.out.print("Masukkan Ukuran File (MB): ");
                            int ukuran = scanner.nextInt();
                            scanner.nextLine();
                            perpusUnila.tambahKoleksi(new Ebook(judul, pengarang, tahun, ukuran));
                        } else {
                            System.out.println("Pilihan jenis koleksi tidak valid.");
                        }
                        break;

                    case 2:
                        System.out.println("\n--- Daftar Koleksi di Perpustakaan ---");
                        perpusUnila.lihatSemuaKoleksi();
                        break;

                    case 3:
                        System.out.println("\n--- Fitur Cari Koleksi ---");
                        System.out.print("Masukkan Judul: ");
                        String kataKunci = scanner.nextLine();
                        try {
                            perpusUnila.cariKoleksi(kataKunci);
                        } catch (KoleksiTidakDitemukanException e) {
                            System.out.println("Peringatan: " + e.getMessage());
                        }
                        break;

                    case 4:
                        System.out.println("\n--- Kelola Anggota ---");
                        System.out.println("1. Tambah Anggota");
                        System.out.println("2. Cari Anggota");
                        System.out.println("3. Lihat Semua");
                        System.out.print("Pilih: ");
                        int menuA = scanner.nextInt();
                        scanner.nextLine();

                        if (menuA == 1) {
                            System.out.print("Masukkan NPM: ");
                            String npm = scanner.nextLine();
                            System.out.print("Masukkan Nama: ");
                            String nama = scanner.nextLine();
                            perpusUnila.tambahAnggota(new Anggota(npm, nama));
                        } else if (menuA == 2) {
                            System.out.print("NPM yg dicari: ");
                            perpusUnila.cariAnggota(scanner.nextLine());
                        } else if (menuA == 3) {
                            perpusUnila.lihatSemuaAnggota();
                        }
                        break;

                    case 5:
                        System.out.println("\n--- Sistem Peminjaman (Asosiasi) ---");
                        System.out.print("Masukkan NPM Peminjam: ");
                        String inputNpm = scanner.nextLine();
                        
                        Anggota peminjam = perpusUnila.getAnggota(inputNpm);
                        if (peminjam == null) {
                            System.out.println("Gagal: Anggota belum terdaftar!");
                        } else if (perpusUnila.getJumlahKoleksi() == 0) {
                            System.out.println("Gagal: Belum ada koleksi yang bisa dipinjam.");
                        } else {
                            perpusUnila.lihatSemuaKoleksi();
                            System.out.print("Pilih Nomor Koleksi yg ingin dipinjam: ");
                            int noKoleksi = scanner.nextInt() - 1;
                            scanner.nextLine();

                            if (noKoleksi >= 0 && noKoleksi < perpusUnila.getJumlahKoleksi()) {
                                Koleksi itemPinjam = perpusUnila.getKoleksiInt(noKoleksi);
                                TransaksiPeminjaman.prosesPinjam(peminjam, itemPinjam);
                            } else {
                                System.out.println("Nomor koleksi tidak valid!");
                            }
                        }
                        break;

                    case 6:
                        System.out.println("Terima kasih telah menggunakan Smart Library!");
                        isRunning = false;
                        break;

                    default:
                        System.out.println("Pilihan tidak valid.");
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