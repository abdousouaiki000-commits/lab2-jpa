package com.example.service;

import com.example.model.Room;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class RoomService extends AbstractCrudService<Room, Long> {

    public RoomService(EntityManagerFactory emf) {
        super(emf);
    }

    // Chercher les rooms disponibles
    public List<Room> findByDisponible(boolean disponible) {

        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Room> query = em.createQuery(
                    "SELECT r FROM Room r WHERE r.disponible = :disponible",
                    Room.class
            );

            query.setParameter("disponible", disponible);

            return query.getResultList();

        } finally {
            em.close();
        }
    }

    // Chercher les rooms avec capacité minimale
    public List<Room> findByCapaciteMinimum(int capaciteMin) {

        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Room> query = em.createQuery(
                    "SELECT r FROM Room r WHERE r.capacite >= :capaciteMin",
                    Room.class
            );

            query.setParameter("capaciteMin", capaciteMin);

            return query.getResultList();

        } finally {
            em.close();
        }
    }
}
