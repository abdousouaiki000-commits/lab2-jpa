package com.example.service;

import com.example.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class UserServiceTest {

    private EntityManagerFactory emf;
    private UserService service;

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("gestion-salles");
        service = new UserService(emf);
    }

    @After
    public void tearDown() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    @Test
    public void testCrudOperations() {

        // CREATE
        User user = new User("Test", "User", "test.user@example.com");
        user.setDateNaissance(LocalDate.of(1990, 1, 1));
        user.setTelephone("+33612345678");

        User savedUser = service.save(user);
        assertNotNull(savedUser.getId());

        // READ
        Optional<User> foundUser = service.findById(savedUser.getId());
        assertTrue(foundUser.isPresent());
        assertEquals("Test", foundUser.get().getNom());

        // UPDATE
        User toUpdate = foundUser.get();
        toUpdate.setNom("Updated");
        service.update(toUpdate);

        Optional<User> updatedUser = service.findById(savedUser.getId());
        assertTrue(updatedUser.isPresent());
        assertEquals("Updated", updatedUser.get().getNom());

        // DELETE
        service.delete(updatedUser.get());
        Optional<User> deletedUser = service.findById(savedUser.getId());
        assertFalse(deletedUser.isPresent());
    }

    @Test
    public void testFindByEmail() {

        User user = new User("Email", "Test", "email.test@example.com");
        service.save(user);

        Optional<User> foundUser = service.findByEmail("email.test@example.com");
        assertTrue(foundUser.isPresent());
        assertEquals("Email", foundUser.get().getNom());

        Optional<User> notFound = service.findByEmail("nonexistent@example.com");
        assertFalse(notFound.isPresent());

        service.delete(foundUser.get());
    }

    @Test
    public void testFindAll() {

        User u1 = new User("User", "One", "user.one@example.com");
        User u2 = new User("User", "Two", "user.two@example.com");

        service.save(u1);
        service.save(u2);

        List<User> users = service.findAll();
        assertTrue(users.size() >= 2);

        service.delete(u1);
        service.delete(u2);
    }
}
