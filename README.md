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
* `vetManagement.sql` dosyası proje dizinindedir

API ENDPOINT TABLOSU
Customer
| Yöntem | URL                                                     | Açıklama                                 |
| ------ | ------------------------------------------------------- | ---------------------------------------- |
| POST   | `http://localhost:8080/v1/customers`                    | Yeni müşteri ekler                       |
| GET    | `http://localhost:8080/v1/customers/getById/5`          | Müşteriyi ID’ye göre getirir             |
| GET    | `http://localhost:8080/v1/customers?page=0&pageSize=10` | Tüm müşterileri listeler                 |
| GET    | `http://localhost:8080/v1/customers/getByName/berk`     | İsme göre filtreler                      |
| GET    | `http://localhost:8080/v1/customers/4/animals`          | Belirli müşteriye ait hayvanları getirir |
| PUT    | `http://localhost:8080/v1/customers`                    | Müşteri bilgisini günceller              |
| DELETE | `http://localhost:8080/v1/customers/`                   | Müşteriyi siler                          |

Animal
| Yöntem | URL                                          | Açıklama                   |
| ------ | -------------------------------------------- | -------------------------- |
| POST   | `http://localhost:8080/v1/animals`           | Yeni hayvan ekler          |
| GET    | `http://localhost:8080/v1/animals/9`         | Hayvanı ID’ye göre getirir |
| GET    | `http://localhost:8080/v1/animals`           | Tüm hayvanları listeler    |
| GET    | `http://localhost:8080/v1/animals/name/Odin` | İsme göre filtreler        |
| PUT    | `http://localhost:8080/v1/animals`           | Hayvan bilgisini günceller |
| DELETE | `http://localhost:8080/v1/animals/9`         | Hayvanı siler              |

Doctor
| Yöntem | URL                                                   | Açıklama                   |
| ------ | ----------------------------------------------------- | -------------------------- |
| POST   | `http://localhost:8080/v1/doctors`                    | Yeni doktor ekler          |
| GET    | `http://localhost:8080/v1/doctors/`                   | Tüm doktorları listeler    |
| GET    | `http://localhost:8080/v1/doctors?page=0&pageSize=10` | Sayfalı doktor listesi     |
| PUT    | `http://localhost:8080/v1/doctors`                    | Doktor bilgisini günceller |
| DELETE | `http://localhost:8080/v1/doctors/5`                  | Doktoru siler              |

Vaccine
| Yöntem | URL                                                                                            | Açıklama                             |
| ------ | ---------------------------------------------------------------------------------------------- | ------------------------------------ |
| POST   | `http://localhost:8080/v1/vaccines`                                                            | Yeni aşı ekler                       |
| GET    | `http://localhost:8080/v1/vaccines/8`                                                          | Aşıyı ID’ye göre getirir             |
| GET    | `http://localhost:8080/v1/vaccines?page=0&pageSize=10`                                         | Aşıları listeler                     |
| GET    | `http://localhost:8080/v1/vaccines/animal/5`                                                   | Belirli hayvana ait aşıları listeler |
| GET    | `http://localhost:8080/v1/vaccines/protection-dates?start_date=2022-01-01&end_date=2025-01-01` | Koruma tarihine göre filtreler       |
| PUT    | `http://localhost:8080/v1/vaccines`                                                            | Aşıyı günceller                      |
| DELETE | `http://localhost:8080/v1/vaccines/12`                                                         | Aşıyı siler                          |

AvailableDate
| Yöntem | URL                                                           | Açıklama                |
| ------ | ------------------------------------------------------------- | ----------------------- |
| POST   | `http://localhost:8080/v1/available_dates`                    | Yeni gün ekler          |
| GET    | `http://localhost:8080/v1/available_dates/2`                  | Günü ID’ye göre getirir |
| GET    | `http://localhost:8080/v1/available_dates?page=0&pageSize=10` | Sayfalı listeleme       |
| PUT    | `http://localhost:8080/v1/available_dates`                    | Günü günceller          |
| DELETE | `http://localhost:8080/v1/available_dates/4`                  | Günü siler              |

Appointment
| Yöntem | URL                                                                                                                       | Açıklama                                  |
| ------ | ------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------- |
| POST   | `http://localhost:8080/v1/appointments`                                                                                   | Yeni randevu ekler                        |
| GET    | `http://localhost:8080/v1/appointments/2`                                                                                 | Randevuyu ID’ye göre getirir              |
| GET    | `http://localhost:8080/v1/appointments?page=0&pageSize=10`                                                                | Randevuları listeler                      |
| GET    | `http://localhost:8080/v1/appointments/doctorId/1?startDateTime=2023-01-01T00:00:00&endDateTime=2025-01-01T23:59:59`      | Doktora ve tarih aralığına göre filtreler |
| GET    | `http://localhost:8080/v1/appointments/getAnimalById/1?startDateTime=2023-01-01T00:00:00&endDateTime=2025-01-01T23:59:59` | Hayvana ve tarih aralığına göre filtreler |
| PUT    | `http://localhost:8080/v1/appointments`                                                                                   | Randevuyu günceller                       |
| DELETE | `http://localhost:8080/v1/appointments/3`                                                                                 | Randevuyu siler                           |

