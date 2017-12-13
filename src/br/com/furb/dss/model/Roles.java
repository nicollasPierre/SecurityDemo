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
public enum Roles {
    usuarioNormal(0),
    moderador(1),
    admin(2);
    
    private int id;
    private Roles(int id) { this.id = id; }
    public int getValue() { return id; }
}
