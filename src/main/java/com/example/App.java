package com.example;

import com.example.model.User;
import com.example.model.Room;
import com.example.service.UserService;
import com.example.service.RoomService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class App {

    public static void main(String[] args) {

        // Création de l'EntityManagerFactory
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("gestion-salles");

        // Création des services
        UserService userService = new UserService(emf);
        RoomService roomService = new RoomService(emf);

        try {

            System.out.println("\n=== Test CRUD User ===");
            testCrudUser(userService);

            System.out.println("\n=== Test CRUD Room ===");
            testCrudRoom(roomService);

        } finally {
            emf.close();
        }
    }

    private static void testCrudUser(UserService service) {

        System.out.println("Création des utilisateurs...");

        User u1 = new User("Dupont", "Jean", "jean.dupont@example.com");
        u1.setDateNaissance(LocalDate.of(1985, 5, 15));
        u1.setTelephone("+33612345678");

        User u2 = new User("Martin", "Sophie", "sophie.martin@example.com");
        u2.setDateNaissance(LocalDate.of(1990, 10, 20));
        u2.setTelephone("+33687654321");

        service.save(u1);
        service.save(u2);

        System.out.println("\nTous les utilisateurs :");
        service.findAll().forEach(System.out::println);

        System.out.println("\nRecherche par ID :");
        Optional<User> userOpt = service.findById(1L);
        userOpt.ifPresent(System.out::println);

        System.out.println("\nRecherche par email :");
        service.findByEmail("sophie.martin@example.com")
                .ifPresent(System.out::println);

        System.out.println("\nMise à jour d'un utilisateur :");
        userOpt.ifPresent(user -> {
            user.setTelephone("+33699887766");
            service.update(user);
            System.out.println("Utilisateur mis à jour : " + user);
        });

        System.out.println("\nSuppression d'un utilisateur :");
        service.deleteById(2L);
        System.out.println("Utilisateur ID=2 supprimé");

        System.out.println("\nListe après suppression :");
        service.findAll().forEach(System.out::println);
    }

    private static void testCrudRoom(RoomService service) {

        System.out.println("Création des salles...");

        Room r1 = new Room("Salle A101", 30);
        r1.setDescription("Salle de réunion avec projecteur");
        r1.setEtage(1);

        Room r2 = new Room("Amphithéâtre B201", 150);
        r2.setDescription("Grand amphithéâtre");
        r2.setEtage(2);

        Room r3 = new Room("Salle C305", 10);
        r3.setDescription("Petite salle pour entretiens");
        r3.setEtage(3);
        r3.setDisponible(false);

        service.save(r1);
        service.save(r2);
        service.save(r3);

        System.out.println("\nToutes les salles :");
        service.findAll().forEach(System.out::println);

        System.out.println("\nRecherche par ID :");
        Optional<Room> roomOpt = service.findById(2L);
        roomOpt.ifPresent(System.out::println);

        System.out.println("\nSalles disponibles :");
        service.findByDisponible(true).forEach(System.out::println);

        System.out.println("\nSalles capacité minimum 50 :");
        service.findByCapaciteMinimum(50)
                .forEach(System.out::println);

        System.out.println("\nMise à jour d'une salle :");
        roomOpt.ifPresent(room -> {
            room.setCapacite(200);
            service.update(room);
            System.out.println("Salle mise à jour : " + room);
        });

        System.out.println("\nSuppression d'une salle :");
        service.deleteById(3L);
        System.out.println("Salle ID=3 supprimée");

        System.out.println("\nListe après suppression :");
        service.findAll().forEach(System.out::println);
    }
}
