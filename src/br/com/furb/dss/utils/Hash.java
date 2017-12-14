/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.dss.utils;

import java.security.MessageDigest;
import java.util.Base64;

/**
 *
 * @author nicol
 */
public class Hash {

    public static String geraHash(String senha, String salt) {
        String senhaSalt = senha + salt;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(senhaSalt.getBytes());
            return Base64.getEncoder().encodeToString(md.digest());
        } catch (Exception e) {
            return null;
        }
    }
    
    public static byte[] geraHash(byte[] senha, byte[] salt) {
        byte[] senhaSalt = new byte[senha.length + salt.length];
        for (int i = 0; i < senhaSalt.length; i++) {
            if(senha.length < i){
                senhaSalt[i] = senha[i];
            }else{
                senhaSalt[i] = salt[i-senha.length];
            }
        }

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(senhaSalt);
            return md.digest();
        } catch (Exception e) {
            return new byte[0];
        }
    }

    public static String geraHash(String mensagem) {
        String senhaSalt = mensagem;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(senhaSalt.getBytes());
            return new String(md.digest());
        } catch (Exception e) {
            return null;
        }
    }
}
