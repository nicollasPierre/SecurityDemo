/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.dss.controller;

import br.com.furb.dss.model.ListaCompras;
import br.com.furb.dss.model.Usuario;
import br.com.furb.dss.utils.AES;
import br.com.furb.dss.utils.Hash;
import br.com.furb.dss.utils.RSA;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

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

    public ListaCompras getById(final int id) throws Exception {
        ListaCompras lista = entityManager.find(ListaCompras.class, id);

        if (verificaLista(lista)) {
            descriptografaAES(lista);
        } else {
            JOptionPane.showConfirmDialog(null, "Alguém modificou a lista" + lista.getId() + "!!!", "Dado não integro", JOptionPane.WARNING_MESSAGE);
        }

        return lista;
    }

    public List<ListaCompras> getByUser(Usuario usuario) throws Exception {
        List<ListaCompras> lista = entityManager.createQuery("FROM " + Usuario.class.getName() + " WHERE usuario = " + usuario.getId()).getResultList();
        for (ListaCompras listaCompra : lista) {
            //listaCompra.setLista(descriptografaAES(listaCompra.getLista()));
        }
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<ListaCompras> findAll() throws Exception {
        List<ListaCompras> lista = entityManager.createQuery("FROM " + ListaCompras.class.getName()).getResultList();
        for (ListaCompras listaCompra : lista) {
            //listaCompra.setLista(descriptografaAES(listaCompra.getLista()));
        }

        return lista;
    }

    public void persist(ListaCompras listaCompras) {
        try {
            criptografaLista(listaCompras);
            assinaLista(listaCompras);
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
            criptografaLista(listaCompras);
            assinaLista(listaCompras);
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

    private String criptografaLista(ListaCompras lista) throws Exception {
        AES aes = new AES(lista.getChaveSimetrica());
        String list = new String(aes.criptografaComAES(lista.getLista()));
        lista.setChaveSimetrica(aes.getChave());
        lista.setVetorInicializacao(aes.getVi());
        lista.setLista(list);
        return lista.getLista();
    }

    private void assinaLista(ListaCompras lista) throws Exception {
        String hash = Hash.geraHash(lista.getLista());
        byte[] assinatura = RSA.assinarMensagem(lista.getChavePrivada(), hash.getBytes());
        lista.setAssinatura(new String(assinatura));
    }

    private boolean verificaLista(ListaCompras lista) throws Exception {
        String hash = Hash.geraHash(lista.getLista());
        return RSA.verificarMensagem(lista.getChavePublica(), lista.getAssinatura().getBytes(), hash);
    }

    private String descriptografaAES(ListaCompras lista) throws Exception {
        AES aes = new AES(lista.getChaveSimetrica(), lista.getVetorInicializacao());
        lista.setLista(new String(aes.descriptografaComAES(lista.getLista().getBytes())));
        return lista.getLista();
    }
}
