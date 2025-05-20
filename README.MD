# 🔍 API Testing Project - Postman & Java (Rest-Assured + TestNG)

Ini adalah proyek tugas akhir untuk course **API Testing dengan Postman & Rest-Assured + TestNG**, yang terdiri dari 2 bagian utama:

1. Pembuatan API Collection di Postman
2. Implementasi automated testing menggunakan Java (Rest-Assured + TestNG)

---

## 📦 Tech Stack

- Java 11+
- Maven
- Rest-Assured
- TestNG
- Postman

---

## 🧪 Bagian 1: API Collection - Postman

### ✅ Fitur:

- Semua endpoint dari dokumentasi API telah diimplementasikan.
- Menggunakan environment variables: `base_url`, `token`, `object_id`, dll.
- Setiap request diuji & disimpan contoh response-nya.
- Export collection disertakan (`.json`).

---

## 🤖 Bagian 2: API Automation - Java (Rest-Assured + TestNG)

### ✅ Fitur:

- Otomatis login dan ambil token (`BaseTest`)
- Register dan login user baru (`AuthTest`)
- CRUD object (GET, POST, PUT, PATCH, DELETE) (`ObjectTest`)
- Ambil list department (`DepartmentTest`)
- Validasi status code dan isi response
- Menggunakan Maven + TestNG

---

## 🚀 Cara Menjalankan Test

### 1. Clone repo

```bash
git clone https://github.com/username/nama-repo.git
cd nama-repo
```
