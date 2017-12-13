/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.dss.utils;

import java.security.MessageDigest;

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
            return new String(md.digest());
        } catch (Exception e) {
            return null;
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
