# 🐾 Veteriner Yönetim Sistemi - Java Spring Boot Bitirme Projesi


![VetUML](https://github.com/user-attachments/assets/d2c63149-db9e-4a8b-88c7-9b7b1d9d91ba)



## ✅ Proje Amacı

Bu proje, bir veteriner kliniğinin iş süreçlerini yönetebilmesini sağlamak amacıyla geliştirilmiştir. Sistemin kullanıcısı veteriner kliniği çalışanları olup, hayvan sahipleri, hayvanlar, doktorlar, randevular ve aşı işlemleri gibi verileri kolayca yönetebilirler.

## ⚡ Teknolojiler

* Java 21
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Maven
* Lombok
* ModelMapper

## 📚 Katmanlı Mimari

* **Controller:** API uç noktaları
* **Service:** Şirket mantığı ve kurallar
* **Repository:** Veritabanı işlemleri
* **DTO:** Veri transfer nesneleri
* **Entity:** Veritabanı modelleri
* **Exception:** Özel hata yönetimi

## 📖 Entity Yapısı ve İlişkiler

### Customer (Hayvan Sahibi)

* OneToMany ilişkisiyle Animal varlığına bağlı

### Animal (Hayvan)

* ManyToOne: Customer
* OneToMany: Vaccine, Appointment

### Doctor (Veteriner)

* OneToMany: Appointment, AvailableDate

### Vaccine (Aşı)

* ManyToOne: Animal

### Appointment (Randevu)

* ManyToOne: Animal, Doctor

### AvailableDate (Müsait Gün)

* ManyToOne: Doctor

## 📅 Temel Özellikler

### 👩‍💻 Veteriner Doktor Yönetimi

* Doktor kaydetme, güncelleme, silme, listeleme

### 📆 Müsait Gün Yönetimi

* Doktora ait çalışma günleri (LocalDate)
* Saat bilgisi yoktur

### 👩‍👩‍👦 Hayvan Sahibi Yönetimi

* Kaydetme, güncelleme, silme, listeleme
* İsme göre filtreleme
* Hayvan sahibine ait tüm hayvanları listeleme

### 🐾 Hayvan Yönetimi

* Kaydetme, güncelleme, silme, listeleme
* İsme göre filtreleme

### 🎒 Aşı Yönetimi

* Hayvana ait aşı kaydetme, güncelleme, silme
* Aşı kodu ve ismi aynı olan, koruma tarihi bitmemiş ise yeni aşı eklenemez
* Belirli hayvana ait aşıları listeleme
* Tarih aralığına göre bitme tarihi yaklaşan aşıları listeleme

### 📝 Randevu Yönetimi

* Randevular LocalDateTime olarak tutulur
* Randevu sadece saat başı yapılabilir
* Doktorun o gün müsait günü kontrol edilir
* Aynı saat/doktor randevusu varsa hata fırlatılır
* Özel hata mesajları: `DoctorNotAvailableException`
* Tarih aralığı ve doktora göre randevu filtreleme
* Tarih aralığı ve hayvana göre randevu filtreleme

## 🚧 Exception Yönetimi

Custom exception sınıfları:

* NotFoundException
* AlreadyExistsException
* DoctorNotAvailableException

GlobalExceptionHandler ile yakalanır ve API tarafına anlamlı mesajlar dönülür.

## 📃 Postman Collection

Tüm API istekleri klasör yapısında toplandı ve export edilerek proje klasörüne eklenmiştir.

## 📂 Veritabanı

* PostgreSQL kullanılmıştır
* Tablolar için en az 5 veri eklenmiştir
* `veterinary_system.sql` dosyası proje dizinindedir

## 📈 Projenin Kurulumu

```bash
git clone https://github.com/kullaniciadi/veterinary-management-system.git
cd veterinary-management-system

# PostgreSQL ayarlarını application.properties dosyasından yap
mvn clean install
mvn spring-boot:run
```

