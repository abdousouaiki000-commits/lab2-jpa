package com.example.service;

import com.example.model.Room;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class RoomServiceTest {

    private EntityManagerFactory emf;
    private RoomService service;

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("gestion-salles");
        service = new RoomService(emf);
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
        Room room = new Room("Test Room", 20);
        room.setDescription("Test description");
        room.setEtage(1);

        Room savedRoom = service.save(room);
        assertNotNull(savedRoom.getId());

        // READ
        Optional<Room> foundRoom = service.findById(savedRoom.getId());
        assertTrue(foundRoom.isPresent());
        assertEquals("Test Room", foundRoom.get().getNom());

        // UPDATE
        Room toUpdate = foundRoom.get();
        toUpdate.setCapacite(30);
        service.update(toUpdate);

        Optional<Room> updatedRoom = service.findById(savedRoom.getId());
        assertTrue(updatedRoom.isPresent());
        assertEquals(Integer.valueOf(30), updatedRoom.get().getCapacite());

        // DELETE
        service.delete(updatedRoom.get());
        Optional<Room> deletedRoom = service.findById(savedRoom.getId());
        assertFalse(deletedRoom.isPresent());
    }

    @Test
    public void testFindByDisponible() {

        Room r1 = new Room("Available Room", 20);
        r1.setDisponible(true);

        Room r2 = new Room("Unavailable Room", 30);
        r2.setDisponible(false);

        service.save(r1);
        service.save(r2);

        List<Room> availableRooms = service.findByDisponible(true);
        assertTrue(availableRooms.stream()
                .anyMatch(r -> r.getNom().equals("Available Room")));

        assertFalse(availableRooms.stream()
                .anyMatch(r -> r.getNom().equals("Unavailable Room")));

        List<Room> unavailableRooms = service.findByDisponible(false);
        assertTrue(unavailableRooms.stream()
                .anyMatch(r -> r.getNom().equals("Unavailable Room")));

        service.delete(r1);
        service.delete(r2);
    }

    @Test
    public void testFindByCapaciteMinimum() {

        Room r1 = new Room("Small Room", 10);
        Room r2 = new Room("Medium Room", 50);
        Room r3 = new Room("Large Room", 100);

        service.save(r1);
        service.save(r2);
        service.save(r3);

        List<Room> roomsMin50 = service.findByCapaciteMinimum(50);
        assertEquals(2,
                roomsMin50.stream()
                        .filter(r -> r.getNom().equals("Medium Room")
                                || r.getNom().equals("Large Room"))
                        .count()
        );

        List<Room> roomsMin80 = service.findByCapaciteMinimum(80);
        assertEquals(1,
                roomsMin80.stream()
                        .filter(r -> r.getNom().equals("Large Room"))
                        .count()
        );

        service.delete(r1);
        service.delete(r2);
        service.delete(r3);
    }
}
