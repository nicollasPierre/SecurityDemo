/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.dss.model;

/**
 *
 * @author nicol
 */
public class ListaCompras {
    private String lista;
    private Usuario usuario;

    public String getLista() {
        return lista;
    }

    public void setLista(String lista) {
        this.lista = lista;
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
