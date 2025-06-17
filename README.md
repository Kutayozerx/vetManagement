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

API ENDPOINT TABLOSU
🧍‍♂️ Customer (Müşteri)
HTTP	Endpoint	Açıklama
POST	/v1/customers	Yeni müşteri ekler
GET	/v1/customers/getById/{id}	ID’ye göre müşteri getirir
GET	/v1/customers?page=0&pageSize=10	Tüm müşterileri sayfalı getirir
GET	/v1/customers/getByName/{name}	Ada göre müşteri arar
GET	/v1/customers/{id}/animals	Müşterinin sahip olduğu hayvanları listeler
PUT	/v1/customers	Müşteri günceller
DELETE	/v1/customers/{id}	Müşteri siler

🐶 Animal (Hayvan)
HTTP	Endpoint	Açıklama
POST	/v1/animals	Yeni hayvan ekler
GET	/v1/animals/{id}	ID’ye göre hayvan getirir
GET	/v1/animals	Tüm hayvanları listeler
GET	/v1/animals/name/{name}	Ada göre hayvan arar
PUT	/v1/animals	Hayvan günceller
DELETE	/v1/animals/{id}	Hayvan siler

👨‍⚕️ Doctor (Doktor)
HTTP	Endpoint	Açıklama
POST	/v1/doctors	Yeni doktor ekler
GET	/v1/doctors/{id}	ID’ye göre doktor getirir
GET	/v1/doctors?page=0&pageSize=10	Tüm doktorları sayfalı getirir
PUT	/v1/doctors	Doktor günceller
DELETE	/v1/doctors/{id}	Doktor siler

💉 Vaccine (Aşı)
HTTP	Endpoint	Açıklama
POST	/v1/vaccines	Yeni aşı ekler
GET	/v1/vaccines/{id}	ID’ye göre aşı getirir
GET	/v1/vaccines?page=0&pageSize=10	Tüm aşıları listeler
GET	/v1/vaccines/animal/{animalId}	Hayvan ID’sine göre aşıları getirir
GET	/v1/vaccines/protection-dates?start_date=YYYY-MM-DD&end_date=YYYY-MM-DD	Koruyuculuk tarihine göre filtreler
PUT	/v1/vaccines	Aşı günceller
DELETE	/v1/vaccines/{id}	Aşı siler

📅 Available Date (Uygun Gün)
HTTP	Endpoint	Açıklama
POST	/v1/available_dates	Yeni uygun gün ekler
GET	/v1/available_dates/{id}	ID’ye göre getirir
GET	/v1/available_dates?page=0&pageSize=10	Tüm uygun günleri listeler
PUT	/v1/available_dates	Güncelleme yapar
DELETE	/v1/available_dates/{id}	Uygun günü siler

📆 Appointments (Randevu)
HTTP	Endpoint	Açıklama
POST	/v1/appointments	Yeni randevu oluşturur
GET	/v1/appointments/{id}	ID’ye göre randevu getirir
GET	/v1/appointments?page=0&pageSize=10	Sayfalı randevu listesi
GET	/v1/appointments/doctorId/{id}?startDateTime=...&endDateTime=...	Doktor ID ve tarih aralığına göre filtreler
GET	/v1/appointments/getAnimalById/{id}?startDateTime=...&endDateTime=...	Hayvan ID ve tarih aralığına göre filtreler
PUT	/v1/appointments	Randevuyu günceller
DELETE	/v1/appointments/{id}	Randevuyu siler
mvn clean install
mvn spring-boot:run
```

