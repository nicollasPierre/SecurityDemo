/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.dss.controller;

import br.com.furb.dss.model.ListaCompras;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author nicol
 */
public class ListaComprasDao {
    private static ListaComprasDao instance;
    protected EntityManager entityManager;

    public static ListaComprasDao getInstance() {
        if (instance == null) {
            instance = new ListaComprasDao();
        }

        return instance;
    }

    private ListaComprasDao() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("SecurityDemoPU");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }

        return entityManager;
    }

    public ListaCompras getById(final int id) {
        return entityManager.find(ListaCompras.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<ListaCompras> findAll() {
        return entityManager.createQuery("FROM " + ListaCompras.class.getName()).getResultList();
    }

    public void persist(ListaCompras listaCompras) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(listaCompras);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void merge(ListaCompras listaCompras) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(listaCompras);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void remove(ListaCompras listaCompras) {
        try {
            entityManager.getTransaction().begin();
            listaCompras = entityManager.find(ListaCompras.class, listaCompras.getId());
            entityManager.remove(listaCompras);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void removeById(final int id) {
        try {
            ListaCompras cliente = getById(id);
            remove(cliente);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
