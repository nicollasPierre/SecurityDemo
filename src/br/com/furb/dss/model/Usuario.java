/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.dss.model;

import java.util.LinkedList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author nicol
 */
@Entity
@Table(name="usuario")
public class Usuario {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String login;
    @Column
    private String hash;
    @Column
    private String salt;
    private LinkedList<Roles> roles;

    public Usuario(String login, String hash, Roles role) {
        this.login = login;
        this.hash = hash;
        this.roles = new LinkedList<>();
        this.roles.add(role);
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSalt() {
        return salt == null ? "" : salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Usuario(String login, String hash, LinkedList<Roles> role) {
        this.login = login;
        this.hash = hash;
        this.roles = role;
    }
    
    public boolean addRole(Roles role) {
        return this.roles.add(role);
    }
    
    public boolean removeRole(Roles role){
        return roles.remove(role);
    }
    
    public boolean hasRole(Roles role) {
        return roles.contains(role);
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return hash;
    }

    public void setSenha(String senha) {
        this.hash = senha;
    }

    public LinkedList<Roles> getRoles() {
        return roles;
    }

    public void setRoles(LinkedList<Roles> roles) {
        this.roles = roles;
    }

    
    
    
}
