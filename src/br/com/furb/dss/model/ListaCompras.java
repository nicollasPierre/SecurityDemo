/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.dss.model;

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

    public ListaCompras(String lista, Usuario usuario) {
        this.lista = lista;
        this.usuario = usuario;
    }

    public ListaCompras(String lista) {
        this.lista = lista;
    }
}
