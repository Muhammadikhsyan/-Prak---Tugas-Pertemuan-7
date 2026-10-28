package com.mycompany.smartlibrary7;

public class RuangBaca {
    private String namaRuangan;
    private int kapasitasKursi;

    public RuangBaca(String namaRuangan, int kapasitasKursi) {
        this.namaRuangan = namaRuangan;
        this.kapasitasKursi = kapasitasKursi;
    }

    public void bukaRuangan() {
        System.out.println("-> " + namaRuangan + " dibuka. Kapasitas: " + kapasitasKursi + " kursi.");
    }
}