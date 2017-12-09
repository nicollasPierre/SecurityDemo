/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.dss.utils;

import java.util.Random;

/**
 *
 * @author pauli
 */
public class Salt {
    
    public static String geraSalt(){
        byte[] salt = new byte[15];
        Random r = new Random();
        r.nextBytes(salt);
        return new String(salt);
    }
}
