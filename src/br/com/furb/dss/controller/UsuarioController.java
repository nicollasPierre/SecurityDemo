/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.dss.controller;

import br.com.furb.dss.model.Roles;
import br.com.furb.dss.model.Usuario;
import br.com.furb.dss.utils.Hash;
import br.com.furb.dss.utils.Salt;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author nicol
 */
public class UsuarioController {

    private ArrayList<Usuario> usuarios;
    private static UsuarioController usuarioController;
    private Usuario usuarioLogado;

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
    
    public static UsuarioController getInstance() {
        if (usuarioController == null) {
            usuarioController = new UsuarioController();
        }
        return usuarioController;
    }

    private UsuarioController() {
        usuarios = new ArrayList<>();
    }

    public void addUsuarioGeraSaltHashSenha(Usuario novoUsuario) {        
        novoUsuario.setSalt(Salt.geraSalt());
        novoUsuario.setSenha(Hash.geraHash(novoUsuario.getSenha(), novoUsuario.getSalt()));
        usuarios.add(novoUsuario);
    }
    
    public void removeUsuario(Usuario usuario) {
        usuarios.remove(usuario);
    }

    public Usuario login(String login, String senhaDigitada) {
//        for (Usuario usu : usuarios) {
//            if (usu.getLogin().equals(login) && usu.getSenha().equals(Hash.geraHash(senhaDigitada, usu.getSalt() ))) {
//                System.out.println("Senha: " + usu.getSenha() + "\nIgual à:\n" + Hash.geraHash(senhaDigitada, usu.getSalt()));
//                return usu;
//            }
//        }
//        return null;
        
        UsuarioDao usu = UsuarioDao.getInstance();
        List<Usuario> usuariosLogados = usu.findByLogin(login);
        if(usuariosLogados.isEmpty()){
            JOptionPane.showMessageDialog(null, "Usuário inexistente");
        } else {
            Usuario logado = usuariosLogados.get(0);
            if(logado.getSenha().equals(Hash.geraHash(senhaDigitada, logado.getSalt()))){
                return logado;
            } else {
                JOptionPane.showMessageDialog(null, "Login ou senha incorreto.");                
            }
        }
        
        return null;
        
//return new Usuario("a", "a", Roles.admin);
    }
    
    public Usuario login(Usuario usuario) {
        for (Usuario usu : UsuarioDao.getInstance().findByLogin(usuario.getLogin())) {
            if (usu.getLogin().equals(usuario.getLogin()) && usu.getSenha().equals(Hash.geraHash(usuario.getSenha(), usu.getSalt() ))) {
                System.out.println("Senha: " + usu.getSenha() + "\nIgual à:\n" + Hash.geraHash(usuario.getSenha(), usu.getSalt()));
                return usu;
            }
        }
        return null;
    }
}
