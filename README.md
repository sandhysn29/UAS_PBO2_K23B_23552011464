# Final Proyek Pemrograman Berorientasi Obyek 2
<ul>
  <li>Mata Kuliah: Pemrograman Berorientasi Obyek 2</li>
  <li>Dosen Pengampu: <a href="https://github.com/Muhammad-Ikhwan-Fathulloh">Muhammad Ikhwan Fathulloh</a></li>
</ul>

## Profil
<ul>
  <li>Nama: Sandhy Safarudin Nurdiansyah</li>
  <li>NIM: 23552011464</li>
  <li>Studi Kasus: Kasir Bank</li>
</ul>

## Aplikasi Kasir Bank
<p>Aplikasi Kasir Bank adalah sebuah aplikasi berbasis Java yang dirancang untuk membantu pengelolaan transaksi perbankan secara sederhana namun efektif. Aplikasi ini memfasilitasi pencatatan dan pemrosesan berbagai jenis rekening seperti tabungan dan giro, memungkinkan pengguna untuk melakukan setor tunai, penarikan, serta melihat histori transaksi.</p>

## Penjelasan Studi Kasus
<p>Dalam kegiatan perbankan, transaksi dasar seperti cek saldo, setor tunai, dan tarik tunai harus dikelola dengan akurat dan efisien. Bank atau lembaga keuangan membutuhkan sistem yang dapat menangani berbagai jenis rekening dan mencatat setiap transaksi secara otomatis agar tidak terjadi kesalahan perhitungan atau pencatatan manual. Namun, banyak aplikasi simulasi yang tidak mencerminkan struktur nyata sistem perbankan dan belum menerapkan prinsip OOP dengan baik, sehingga sulit dikembangkan atau dimodifikasi.

Tidak adanya sistem kasir sederhana yang bisa digunakan untuk simulasi transaksi bank secara objektif dan modular.

Struktur kode yang tidak fleksibel dan sulit dikembangkan karena tidak menerapkan prinsip OOP seperti abstraksi, enkapsulasi, inheritance, dan polimorfisme. Perlu sistem yang bisa menangani lebih dari satu jenis rekening (Tabungan dan Giro) dengan aturan transaksi yang berbeda.

Solusi dari permasalahan ini adalah membangun Aplikasi Kasir Bank menggunakan Java dan MySQL dengan pendekatan OOP:
1. Abstraction: Menyembunyikan detail implementasi di balik antarmuka atau class abstrak.
2. Inheritance: Menggunakan pewarisan antara class, biasanya dari class dasar atau abstract class.
3. Encapsulation: Menyembunyikan detail data dan memberikan akses lewat method publik.
4. Polymorphism: Method register() bisa punya banyak bentuk — bergantung pada konteks atau class tempat dia digunakan.

## Penjelasan 4 Pilar OOP dalam Studi Kasus

### 1. Inheritance
<p>Di dalam RegisterService.java, misalnya kita buat class turunan dari abstract service
  
    public abstract class BaseService {
        public abstract boolean register(String nama, String pin, String jenis);
    }

Lalu diimplementasikan di RegisterService.java

    public class RegisterService extends BaseService {
  
      private static List<String> registeredUsers = new ArrayList<>();
      
      @Override
      public boolean register(String nama, String pin, String jenis) {
          String userData = nama + "-" + pin + "-" + jenis;
          if (!registeredUsers.contains(userData)) {
              registeredUsers.add(userData);
              return true;
          }
          return false;
      }}
</p>

### 2. Encapsulation
<p>Contoh pada class model data Nasabah.java

  
    public class Nasabah {
      private String nama;
      private String pin;
      private String jenisRekening;
  
      public Nasabah(String nama, String pin, String jenisRekening) {
          this.nama = nama;
          this.pin = pin;
          this.jenisRekening = jenisRekening;
      }
  
      public String getNama() {
          return nama;
      }
  
      public String getPin() {
          return pin;
      }
  
      public String getJenisRekening() {
          return jenisRekening;
      }
    }
</p>

### 3. Polymorphism
<p>Method register() bisa punya banyak bentuk — bergantung pada konteks atau class tempat dia digunakan.
  
  
    // Masih di BaseService & RegisterService
    public abstract class BaseService {
        public abstract boolean register(String nama, String pin, String jenis);
    }
      
    //Di class RegisterService
    @Override
    public boolean register(String nama, String pin, String jenis) {
      // Implementasi spesifik
      ...
    }
      
    // Bisa juga pakai overloading (jika ada lebih dari satu versi register)
    public boolean register(String nama, String pin) {
          return register(nama, pin, "Tabungan");
    }




</p>

### 4. Abstract
<p>Menyembunyikan detail implementasi di balik antarmuka atau class abstrak.
  
    // BaseService sebagai class abstract
    public abstract class BaseService {
        public abstract boolean register(String nama, String pin, String jenis);
    }
    
    // Penggunaannya di controller
    RegisterService service = new RegisterService();
    boolean hasil = service.register("Dilan", "1234", "Tabungan");
</p>

### FXMain.java
<img width="839" height="275" alt="MainFX" src="https://github.com/user-attachments/assets/9c438d3a-f24d-48a9-b7a0-e2f2bcae4b19" />
<p>Class ini adalah gerbang utama aplikasi JavaFX. Di sinilah method start(Stage stage) dijalankan saat program pertama kali di-run. Di dalam method tersebut, kita load file FXML.fxml sebagai tampilan awal, lalu menampilkan jendela aplikasi lewat primaryStage.show(). Biasanya digunakan untuk set judul jendela, ukuran scene, dan inisialisasi awal tampilan. Bisa dibilang ini "pintu depan"-nya aplikasi, tempat pertama kali pengguna masuk sebelum masuk ke fitur lainnya.</p>

### Struktur Folder
<img width="314" height="438" alt="Struktur Folder" src="https://github.com/user-attachments/assets/35850e22-92e8-4140-8c5a-53cb820d9981" />

## Demo Proyek
<ul>
  <li>Github: <a href="">Github</a></li>
  <li>Drive: <a href="https://drive.google.com/file/d/19s9bt00B4GXex-UvCYIPHUxEX6d8kYBj/view?usp=sharing">Drive</a></li>
</ul>
