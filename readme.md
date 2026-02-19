Gestion des Salles – Projet JPA / Hibernate / H2
Description du projet

Ce projet est une application Java développée dans le cadre du Lab 2.
Il permet de gérer des utilisateurs et des salles à l’aide de la technologie JPA (Java Persistence API) avec Hibernate comme implémentation et H2 comme base de données embarquée en mémoire.

L’objectif principal est de mettre en pratique :

La configuration d’une unité de persistance

La création d’entités JPA

L’implémentation d’un service générique CRUD

L’utilisation de requêtes JPQL

L’écriture de tests unitaires avec JUnit

L’exécution et la validation des opérations via une classe principale

Technologies utilisées

Java

Maven

JPA (javax.persistence)

Hibernate ORM

H2 Database (base de données en mémoire)

JUnit 4

Architecture du projet
(voire les captures d'ecron dans le fichier docs)

Fonctionnalités implémentées
1. Gestion des utilisateurs

Entité : User

Attributs principaux :

id

nom

prenom

email

dateNaissance

telephone

Opérations disponibles :

Création d’un utilisateur

Recherche par identifiant

Recherche par email

Modification

Suppression

Liste complète des utilisateurs

2. Gestion des salles

Entité : Room

Attributs principaux :

id

nom

capacite

description

disponible

etage

Opérations disponibles :

Création d’une salle

Recherche par identifiant

Recherche des salles disponibles

Recherche par capacité minimale

Modification

Suppression

Liste complète des salles

Service CRUD générique

Le projet contient :

Interface

CrudService<T, ID>

Elle définit les opérations standards :

save

findById

findAll

update

delete

deleteById

Classe abstraite

AbstractCrudService<T, ID>

Elle contient l’implémentation générique des opérations CRUD en utilisant EntityManager.

Les services spécifiques (UserService et RoomService) héritent de cette classe.

Configuration de la base de données

La base utilisée est H2 en mémoire.

Configuration dans persistence.xml :

Driver : org.h2.Driver

URL : jdbc:h2:mem:testdb

Dialect : org.hibernate.dialect.H2Dialect

hbm2ddl.auto : create-drop

La base est automatiquement créée au démarrage et supprimée à la fermeture.

Exécution du projet
Depuis IntelliJ

Exécuter la classe :

App.java


La console affiche :

Les requêtes SQL générées par Hibernate

Les résultats des opérations CRUD

Depuis la ligne de commande
mvn clean compile exec:java -Dexec.mainClass="com.example.App"

Exécution des tests

Pour exécuter les tests unitaires :

mvn test


Ou bien lancer les classes :

UserServiceTest

RoomServiceTest

Tous les tests doivent s’exécuter sans erreur.

Objectifs pédagogiques atteints

Compréhension du cycle de vie des entités JPA

Manipulation d’EntityManager et des transactions

Utilisation de JPQL

Création d’un service générique avec les génériques Java

Mise en place de tests unitaires

Intégration Maven

Auteur

Projet réalisé dans le cadre du module JPA / Hibernate.

Nom : Abderrahmane Souaiki