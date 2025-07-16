package kasirbankgui.model;

import java.sql.Timestamp;

public class Transaksi {
    private Timestamp tanggal;
    private String tipe;
    private double jumlah;

    public Transaksi(Timestamp tanggal, String tipe, double jumlah) {
        this.tanggal = tanggal;
        this.tipe = tipe;
        this.jumlah = jumlah;
    }

    public Timestamp getTanggal() {
        return tanggal;
    }

    public String getTipe() {
        return tipe;
    }

    public double getJumlah() {
        return jumlah;
    }
}