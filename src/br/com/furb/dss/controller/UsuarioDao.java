/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.dss.controller;

import br.com.furb.dss.model.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

/**
 *
 * @author nicol
 */
public class UsuarioDao {

    private static UsuarioDao instance;
    protected EntityManager entityManager;

    public static UsuarioDao getInstance() {
        if (instance == null) {
            instance = new UsuarioDao();
        }

        return instance;
    }

    private UsuarioDao() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("SecurityDemoPU");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }

        return entityManager;
    }

    public Usuario getById(final int id) {
        return entityManager.find(Usuario.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Usuario> findAll() {
        return entityManager.createQuery("FROM " + Usuario.class.getName()).getResultList();
    }

    public List<Usuario> findByLogin(String login) {
        return entityManager.createQuery("FROM " + Usuario.class.getName() + " WHERE login LIKE \'" + login + "\'").getResultList();
    }

    public List<Usuario> findByLogin(String login, String senhaHash) {
        return entityManager.createQuery("FROM " + Usuario.class.getName() + " WHERE login LIKE \'" + login + "\' LIKE  \'" + senhaHash + "\'").getResultList();
    }

    public Integer maxUserID() {
//        DetachedCriteria.forClass(Usuario.class)
//                .setProjection(Projections.max("id"));
//        entityManager.createCriteria(Usuario.class)
//                .add(Property.forName("id").eq(maxId))
//                .list();
        return (Integer) entityManager.createQuery("SELECT MAX(u.id) from Usuario u").getSingleResult();
    }

    public void persist(Usuario usuario) {
        try {
            
            entityManager.getTransaction().begin();
            if (usuario.getId() == 0) {
                usuario.setId(maxUserID() + 1);
            }
            entityManager.persist(usuario);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void merge(Usuario uUsuario) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(uUsuario);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void remove(Usuario usuario) {
        try {
            entityManager.getTransaction().begin();
            usuario = entityManager.find(Usuario.class, usuario.getId());
            entityManager.remove(usuario);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void removeById(final int id) {
        try {
            Usuario cliente = getById(id);
            remove(cliente);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
