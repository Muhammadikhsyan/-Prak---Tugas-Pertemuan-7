package com.mycompany.smartlibrary7;

import java.util.Scanner;

public class SmartLibrary7 {
    public static void cariKoleksi(Koleksi[] daftar, int jumlah, String judul) {
        boolean ditemukan = false;
        for (int i = 0; i < jumlah; i++) {
            if (daftar[i].getJudul().equalsIgnoreCase(judul)) {
                System.out.println("\n--- Koleksi Ditemukan ---");
                daftar[i].tampilkanInfo();
                daftar[i].caraPinjam();
                ditemukan = true;
                break;
            }
        }
        if (!ditemukan) {
            System.out.println("Koleksi dengan judul '" + judul + "' tidak ditemukan.");
        }
    }

    public static void cariKoleksi(Koleksi[] daftar, int jumlah, int tahun) {
        boolean ditemukan = false;
        for (int i = 0; i < jumlah; i++) {
            if (daftar[i].getTahunTerbit() == tahun) {
                System.out.println("\n--- Koleksi Ditemukan ---");
                daftar[i].tampilkanInfo();
                daftar[i].caraPinjam();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Koleksi yang terbit pada tahun " + tahun + " tidak ditemukan.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Menggunakan array bertipe Koleksi (Abstract Superclass)
        Koleksi[] daftarKoleksi = new Koleksi[10];

        int jumlahKoleksi = 0;
        boolean isRunning = true;

        System.out.println("==================================");
        System.out.println("   Selamat Datang di Smart Library");
        System.out.println("==================================");

        while (isRunning) {
            System.out.println("\n=== Menu Utama ===");
            System.out.println("1. Tambah Koleksi");
            System.out.println("2. Lihat Daftar Koleksi");
            System.out.println("3. Cari Koleksi");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu (1-4): ");

            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    if (jumlahKoleksi < daftarKoleksi.length) {
                        System.out.println("\n--- Pilih Jenis Koleksi ---");
                        System.out.println("1. Buku Cetak");
                        System.out.println("2. E-Book");
                        System.out.print("Pilih (1-2): ");
                        int jenis = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Masukkan judul        : ");
                        String judul = scanner.nextLine();

                        System.out.print("Masukkan penulis      : ");
                        String penulis = scanner.nextLine();

                        System.out.print("Masukkan tahun terbit : ");
                        int tahunTerbit = scanner.nextInt();

                        if (jenis == 1) {
                            System.out.print("Masukkan jumlah halaman: ");
                            int jumlahHalaman = scanner.nextInt();
                            scanner.nextLine();

                            daftarKoleksi[jumlahKoleksi] = new BukuCetak(judul, penulis, tahunTerbit, jumlahHalaman);
                            jumlahKoleksi++;
                            System.out.println("Sukses! Buku Cetak berhasil ditambahkan.");

                        } else if (jenis == 2) {
                            System.out.print("Masukkan ukuran file (MB): ");
                            double ukuranFileMB = scanner.nextDouble();
                            scanner.nextLine();

                            daftarKoleksi[jumlahKoleksi] = new Ebook(judul, penulis, tahunTerbit, ukuranFileMB);
                            jumlahKoleksi++;
                            System.out.println("Sukses! E-Book berhasil ditambahkan.");

                        } else {
                            System.out.println("Pilihan jenis koleksi tidak valid.");
                        }
                    } else {
                        System.out.println("Maaf, kapasitas perpustakaan sudah penuh!");
                    }
                    break;

                case 2:
                    System.out.println("\n--- Daftar Koleksi di Perpustakaan ---");
                    System.out.println("Total koleksi terdaftar: " + Koleksi.totalKoleksi);
                    if (jumlahKoleksi == 0) {
                        System.out.println("Belum ada koleksi yang tersimpan.");
                    } else {
                        for (int i = 0; i < jumlahKoleksi; i++) {
                            System.out.println("\nKoleksi ke-" + (i + 1));
                            daftarKoleksi[i].tampilkanInfo();
                            System.out.print("Cara Pinjam : ");
                            daftarKoleksi[i].caraPinjam();
                            
                            // Memanggil Abstract Method dari Abstract Class
                            daftarKoleksi[i].hitungDendaKeterlambatan();

                            // Pengecekan Interface menggunakan 'instanceof'
                            if (daftarKoleksi[i] instanceof DapatDipinjam) {
                                DapatDipinjam itemFisik = (DapatDipinjam) daftarKoleksi[i];
                                itemFisik.prosesPinjamFisik();
                            }

                            if (daftarKoleksi[i] instanceof DapatDinilai) {
                                DapatDinilai itemDinilai = (DapatDinilai) daftarKoleksi[i];
                                itemDinilai.beriRating(5);
                            }
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n=== Cari Koleksi ===");
                    System.out.println("1. Cari berdasarkan Judul");
                    System.out.println("2. Cari berdasarkan Tahun Terbit");
                    System.out.print("Pilih metode pencarian (1-2): ");
                    int subPilihan = scanner.nextInt();
                    scanner.nextLine();

                    if (subPilihan == 1) {
                        System.out.print("Masukkan judul koleksi: ");
                        String kataKunciJudul = scanner.nextLine();
                        cariKoleksi(daftarKoleksi, jumlahKoleksi, kataKunciJudul);
                    } else if (subPilihan == 2) {
                        System.out.print("Masukkan tahun terbit: ");
                        int kataKunciTahun = scanner.nextInt();
                        scanner.nextLine();
                        cariKoleksi(daftarKoleksi, jumlahKoleksi, kataKunciTahun);
                    } else {
                        System.out.println("Pilihan jenis pencarian tidak valid.");
                    }
                    break;

                case 4:
                    System.out.println("Terima kasih telah menggunakan Smart Library!");
                    isRunning = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan masukkan angka 1-4.");
                    break;
            }
        }

        scanner.close();
    }
}