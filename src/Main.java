import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<String> keranjang = new ArrayList<>();
        List<Integer> hargaItem = new ArrayList<>();
        int total = 0, bayar = 0, kembalian;

        // Input nama pelanggan dengan nextLine()
        System.out.print("Masukkan nama pelanggan: ");
        String namaPelanggan = scan.nextLine();

        System.out.println("\n== SELAMAT DATANG DI RESTORAN Eror 404! ==");
        System.out.println("1. Aneka Makanan");
        System.out.println("2. Aneka Minuman");
        System.out.println("3. Aneka Jus");

        String lanjut = "Y";

        do {
            System.out.print("Pilih kategori : ");
            int makanMinum = scan.nextInt();

            if (makanMinum == 1) {
                tampilkanMenuMakan();
            } else if (makanMinum == 2) {
                tampilkanMenuMinum();
            } else if (makanMinum == 3) {
                tampilkanMenuJus();
            } else {
                System.out.println("Kategori tidak valid!");
                continue;
            }

            System.out.print("Pilih nomor menu: ");
            int nomorMenu = scan.nextInt();
            System.out.print("Masukkan jumlah pesanan: ");
            int jumlah = scan.nextInt();

            // Tambahkan pesanan ke keranjang
            String namaItem = dapatkanNamaMenu(nomorMenu, makanMinum);
            int harga = dapatkanHargaMenu(nomorMenu, makanMinum) * jumlah;

            if (harga > 0) {
                keranjang.add(namaItem + " (x" + jumlah + ")");
                hargaItem.add(harga);
                total += harga;
                System.out.println("Pesanan berhasil ditambahkan ke keranjang.");
            } else {
                System.out.println("Menu tidak valid!");
            }

            System.out.print("Lanjut memesan? (Y/T): ");
            lanjut = scan.next();
        } while (lanjut.equalsIgnoreCase("Y"));

        // Tampilkan keranjang dan subtotal
        tampilkanKeranjang(keranjang, hargaItem, total);

        // Input diskon setelah melihat subtotal
        double diskonPersen = 0;
        System.out.print("Masukkan persentase diskon (contoh: 10 untuk 10%): ");
        diskonPersen = scan.nextDouble();

        while (diskonPersen < 0 || diskonPersen > 100) {
            System.out.println("Persentase diskon tidak valid! Masukkan nilai antara 0-100.");
            System.out.print("Masukkan persentase diskon: ");
            diskonPersen = scan.nextDouble();
        }

        // Hitung total diskon dan total bayar setelah diskon
        double nilaiDiskon = total * (diskonPersen / 100);
        int totalBayar = (int) (total - nilaiDiskon);

        System.out.println("Harga setelah diskon: Rp." + totalBayar);

        // Pembayaran
        do {
            System.out.print("Masukkan pembayaran: Rp.");
            bayar = scan.nextInt();

            if (bayar < totalBayar) {
                System.out.println("Uang Anda kurang! Harap masukkan pembayaran yang cukup.");
            }
            continue;
        } while (bayar < totalBayar);

        // Hitung kembalian
        kembalian = bayar - totalBayar;

        // Cetak invoice final
        cetakInvoice(keranjang, hargaItem, total, diskonPersen, nilaiDiskon, totalBayar, bayar, kembalian, namaPelanggan);

        scan.close();
    }

    public static void tampilkanMenuMakan() {
        System.out.println("===== Menu Makanan =====");
        System.out.println("1. Ayam Goreng Kremes   - Rp.18.000");
        System.out.println("2. Ayam Bakar           - Rp.18.000");
        System.out.println("3. Ayam Rica-rica       - Rp.20.000");
        System.out.println("4. Ayam Geprek          - Rp.15.000");
        System.out.println("5. Bebek Bakar          - Rp.20.000");
    }

    public static void tampilkanMenuMinum() {
        System.out.println("===== Menu Minuman =====");
        System.out.println("1. Lemon Teh                - Rp.10.000");
        System.out.println("2. Es Coklat (Es/Panas)     - Rp.10.000");
        System.out.println("3. Sakura Fizz              - Rp.11.000");
        System.out.println("4. Teh Manis Hangat         - Rp.5.000");
        System.out.println("5. Es Teh Manis             - Rp.7.000");
        System.out.println("6. Air Mineral              - Rp.7.000");
        System.out.println("7. Orange Jus               - Rp.8.000");
    }

    public static void tampilkanMenuJus() {
        System.out.println("===== Menu Aneka Jus =====");
        System.out.println("1. Jus Wortel               - Rp.8.000");
        System.out.println("2. Jus Buah Naga            - Rp.10.000");
        System.out.println("3. Jus Mangga               - Rp.10.000");
        System.out.println("4. Jus Melon                - Rp.10.000");
        System.out.println("5. Jus Pisang               - Rp.10.000");
        System.out.println("6. Jus Alpukat              - Rp.12.000");
        System.out.println("7. Jus Jambu                - Rp.12.000");
        System.out.println("8. Jus Sirsak               - Rp.13.000");
        System.out.println("9. Jus Melon                - Rp.11.000");
    }

    public static void tampilkanKeranjang(List<String> keranjang, List<Integer> hargaItem, int total) {
        System.out.println("\n===== PESANAN ANDA =====");
        for (int i = 0; i < keranjang.size(); i++) {
            System.out.println((i + 1) + ". " + keranjang.get(i) + " - Rp." + hargaItem.get(i));
        }
        System.out.println("------------------------");
        System.out.println("Subtotal: Rp." + total);
    }

    public static void cetakInvoice(List<String> keranjang, List<Integer> hargaItem, int total,
                                    double diskonPersen, double nilaiDiskon, int totalBayar,
                                    int bayar, int kembalian, String namaPelanggan) {
        System.out.println("===================================");
        System.out.println("       Restoran Error 404!       ");
        System.out.println(" Alamat : Jl. Margonda Raya No.100 ");
        System.out.println(" Depok Jawa Barat 021-11-22-33");
        System.out.println("===================================");
        System.out.println("============ INVOICE ==============");
        System.out.println("Nama Pelanggan: " + namaPelanggan);
        for (int i = 0; i < keranjang.size(); i++) {
            System.out.println((i + 1) + ". " + keranjang.get(i) + " - Rp." + hargaItem.get(i));
        }
        System.out.println("===================================");
        System.out.println("Subtotal: Rp." + total);
        System.out.println("Diskon (" + diskonPersen + "%): Rp." + (int) nilaiDiskon);
        System.out.println("Total Bayar: Rp." + totalBayar);
        System.out.println("===================================");
        System.out.println("Pembayaran: Rp." + bayar);
        System.out.println("Kembalian: Rp." + kembalian);
        System.out.println("\nTerima Kasih atas Pembelian Anda!");
    }

    public static String dapatkanNamaMenu(int nomorMenu, int kategori) {
        if (kategori == 1) {
            switch (nomorMenu) {
                case 1: return "Ayam Goreng Kremes";
                case 2: return "Ayam Bakar";
                case 3: return "Ayam Rica-rica";
                case 4: return "Ayam Geprek";
                case 5: return "Bebek Bakar";
                default: return "";
            }
        } else if (kategori == 2)
            switch (nomorMenu) {
                case 1: return "Lemon Teh";
                case 2: return "Es Coklat (Es/Panas)";
                case 3: return "Sakura Fizz";
                case 4: return "Teh Manis Hangat";
                case 5: return "Es Teh Manis";
                case 6: return "Air Mineral";
                case 7: return "Orange Jus";
                default:
                    return "";
            }
        else {
            switch (nomorMenu) {
                case 1: return "Jus Wortel";
                case 2: return "Jus Buah Naga";
                case 3: return "Jus Mangga";
                case 4: return "Jus Melon";
                case 5: return "Jus Pisang";
                case 6: return "Jus Alpukat";
                case 7: return "Jus Jambu";
                case 8: return "Jus Sirsak";
                case 9: return "Jus Melon";
                default:
                    return "";
            }
        }
    }

    public static int dapatkanHargaMenu(int nomorMenu, int kategori) {
        if (kategori == 1) {
            switch (nomorMenu) {
                case 1: case 2: return 18000;
                case 3: return 20000;
                case 4: return 15000;
                case 5: return 20000;
                default: return 0;
            }
        } else if (kategori == 2)
            switch (nomorMenu) {
                case 1: case 2: return 10000;
                case 3: return 11000;
                case 4: return 5000;
                case 5: case 6: return 7000;
                case 7: return 8000;
                default: return 0;
            }
        else {
            switch (nomorMenu) {
                case 1: return 8000;
                case 2: case 3: case 4: case 5: return 10000;
                case 6: case 7: return 12000;
                case 8: return 13000;
                case 9: return 11000;
                default:
                    return 0;
            }
        }
    }
}