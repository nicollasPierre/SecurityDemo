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
import br.com.furb.dss.utils.Salt;
import java.util.Base64;
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

    public ListaCompras getById(int id) throws Exception {
        ListaCompras lista = null;
        lista = entityManager.find(ListaCompras.class, id);

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
            if (verificaLista(listaCompra)) {
                listaCompra.setLista(descriptografaAES(listaCompra));
            } else {
                JOptionPane.showConfirmDialog(null, "Alguém modificou a lista" + listaCompra.getId() + "!!!", "Dado não integro", JOptionPane.WARNING_MESSAGE);
            }
        }
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<ListaCompras> findAll() throws Exception {
        List<ListaCompras> lista = entityManager.createQuery("FROM " + ListaCompras.class.getName()).getResultList();
        for (ListaCompras listaCompra : lista) {
            if (verificaLista(listaCompra)) {
                listaCompra.setLista(descriptografaAES(listaCompra));
            } else {
                JOptionPane.showConfirmDialog(null, "Alguém modificou a lista" + listaCompra.getId() + "!!!", "Dado não integro", JOptionPane.WARNING_MESSAGE);
            }
        }

        return lista;
    }

    public void persist(ListaCompras listaCompras) {
        try {
            criptografaLista(listaCompras);
            assinaLista(listaCompras);
            toggleKeyCamouflage(listaCompras);
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
            toggleKeyCamouflage(listaCompras);
            criptografaLista(listaCompras);
            assinaLista(listaCompras);
            toggleKeyCamouflage(listaCompras);
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
        String novaListaBase64 = Base64.getEncoder().encodeToString(aes.criptografaComAES(lista.getLista()));
        String list = new String(novaListaBase64);
        lista.setChaveSimetrica(aes.getChave().getEncoded());
        lista.setVetorInicializacao(aes.getVi().getIV());
        lista.setLista(list);
        return lista.getLista();
    }

    private void assinaLista(ListaCompras lista) throws Exception {
        String hash = Hash.geraHash(lista.getLista());
        byte[] assinatura = RSA.assinarMensagem(lista.getChavePrivada(), hash.getBytes());
        lista.setAssinatura(assinatura);
        RSA rsa = RSA.getInstance();
        lista.setChavePrivada(rsa.getChavePriv().getEncoded());
        lista.setChavePublica(rsa.getChavePubli().getEncoded());
    }

    private boolean verificaLista(ListaCompras lista) throws Exception {
        String hash = Hash.geraHash(lista.getLista());
        return RSA.verificarMensagem(lista.getChavePublica(), lista.getAssinatura(), hash);
    }

    private String descriptografaAES(ListaCompras lista) throws Exception {
        AES aes = new AES(lista.getChaveSimetrica(), lista.getVetorInicializacao());
        lista.setLista(new String(aes.descriptografaComAES(lista.getLista())));
        return lista.getLista();
    }

    private byte[] doXOR(byte[] key, byte[] hash) {
        byte[] xor = new byte[key.length > hash.length ? key.length : hash.length];
        for (int i = 0; i < xor.length; i++) {
            if (key.length < i && hash.length < i) {
                xor[i] = (byte) (key[i] ^ hash[i]);
            } else if (key.length < hash.length) {
                xor[i] = hash[i];
            } else {
                xor[i] = key[i];
            }
        }
        return xor;
    }

    private void toggleKeyCamouflage(ListaCompras shopList) {
        byte[] hash = Base64.getDecoder().decode(shopList.getUsuario().getHash());
        shopList.setChaveSimetrica(doXOR(shopList.getChaveSimetrica(), hash));
        shopList.setChavePrivada(doXOR(shopList.getChavePrivada(), hash));
    }

    private byte[] doHash(byte[] senha, byte[] salt) {
        return Hash.geraHash(senha, salt);
        
    }
}
