/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.dss.model;

import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author nicol
 */
@Entity
@Table(name="listaCompras")
public class ListaCompras {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String lista;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="usuario_fk")
    private Usuario usuario;
    @Column(length = 65535)
    private byte[] chaveSimetrica = new byte[0];
    @Column(length = 65535)
    private byte[] chavePrivada = new byte[0];
    @Column(length = 65535)
    private byte[] chavePublica = new byte[0];
    @Column(length = 65535)
    private byte[] assinatura = new byte[0];
    @Column(length = 65535)
    private byte[] vetorInicializacao = new byte[0];

    public ListaCompras(int id, String lista, Usuario usuario) {
        this.id = id;
        this.lista = lista;
        this.usuario = usuario;
    }

    public byte[] getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(byte[] assinatura) {
        this.assinatura = assinatura;
    }
    
    public byte[] getVetorInicializacao() {
        return vetorInicializacao;
    }

    public void setVetorInicializacao(byte[] vetorInicializacao) {
        this.vetorInicializacao = vetorInicializacao;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLista() {
        return lista;
    }

    public String setLista(String lista) {
        this.lista = lista;
        return lista;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public byte[] getChaveSimetrica() {
        return chaveSimetrica;
    }

    public void setChaveSimetrica(byte[] chaveSimetrica) {
        this.chaveSimetrica = chaveSimetrica;
    }

    public byte[] getChavePrivada() {
        return chavePrivada;
    }

    public void setChavePrivada(byte[] chavePrivada) {
        this.chavePrivada = chavePrivada;
    }

    public byte[] getChavePublica() {
        return chavePublica;
    }

    public void setChavePublica(byte[] chavePublica) {
        this.chavePublica = chavePublica;
    }
    
    public ListaCompras(String lista, Usuario usuario) {
        this.lista = lista;
        this.usuario = usuario;
    }

    public ListaCompras(String lista) {
        this.lista = lista;
    }
}
