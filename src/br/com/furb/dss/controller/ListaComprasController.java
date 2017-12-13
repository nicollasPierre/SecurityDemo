/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.dss.controller;

import br.com.furb.dss.model.ListaCompras;

/**
 *
 * @author nicol
 */
public class ListaComprasController {
    private ListaCompras lista;
    private ListaComprasController instance;

    protected ListaComprasController(ListaCompras lista) {
        this.lista = lista;
    }

    protected ListaCompras getLista() {
        return lista;
    }

    public void setLista(ListaCompras lista) {
        this.lista = lista;
    }
    
    public String escreverLista(String lista){
        return this.lista.setLista(lista);
    }
    
    public String adicionarALista(String itensLista){
        return this.lista.setLista(this.lista.getLista()+itensLista);
    }
}
