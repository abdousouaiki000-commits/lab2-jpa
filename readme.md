# 📚 Gestion des Salles - JPA / Hibernate

Projet Java utilisant **JPA (Hibernate)** et **H2 Database** pour gérer :

- 👤 Utilisateurs
- 🏢 Salles

------------------------------------------------

src/
├── main/
│ ├── java/
│ │ └── com.example
│ │ ├── model
│ │ │ ├── User.java
│ │ │ └── Room.java
│ │ ├── service
│ │ │ ├── CrudService.java
│ │ │ ├── AbstractCrudService.java
│ │ │ ├── UserService.java
│ │ │ └── RoomService.java
│ │ └── App.java
│ └── resources/
│ └── META-INF/persistence.xml
│
└── test/
└── java/
└── com.example.service
├── UserServiceTest.java
└── RoomServiceTest.java


---

## 🛠️ Technologies utilisées

- Java
- Maven
- JPA
- Hibernate
- H2 Database
- JUnit

---

## 🚀 Fonctionnalités

### 👤 Utilisateur
- Create
- Read (par ID)
- Read (par email)
- Update
- Delete

### 🏢 Salle
- Create
- Read
- Recherche par disponibilité
- Recherche par capacité minimale
- Update
- Delete

---

## ▶️ Exécution du projet

### Dans IntelliJ :
Exécuter la classe :


---

## 🧪 Exécution des tests



Ou clic droit sur le dossier `test` → Run Tests

---

## 🗄️ Base de données

Base utilisée : **H2 in-memory**

Configuration dans :


---

## 👨‍🎓 Auteur
Abderrahmane SOUAIKI

Projet réalisé dans le cadre d’un TP JPA / Hibernate.

