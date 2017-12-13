/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.dss.utils;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 *
 * @author nicol
 */
public class AES {

    private SecretKey chave = null;
    private IvParameterSpec vi = null;

    public AES(SecretKey simetrica, IvParameterSpec vi) {
        this.chave = simetrica;
        this.vi = vi;
    }

    public AES(SecretKey simetrica) {
        this.chave = simetrica;
    }

    
    public SecretKey getChave() {
        return chave;
    }

    public void setChave(SecretKey chave) {
        this.chave = chave;
    }

    public IvParameterSpec getVi() {
        return vi;
    }

    public void setVi(IvParameterSpec vi) {
        this.vi = vi;
    }
    
    public byte[] criptografaComAES(String mensagem) throws Exception {
        //Passo 1
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);

        //Passo 2
        if (chave == null) {
            chave = keyGenerator.generateKey();
        }

        //Passo 3
        byte[] vetor = new byte[16];
        SecureRandom secure = new SecureRandom();
        secure.nextBytes(vetor);

        //Passo 4
        IvParameterSpec ips = new IvParameterSpec(vetor);
        vi = ips;
        //Passo 5
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, chave, ips);

        //Passo 6
        byte[] cifrado = cipher.doFinal(mensagem.getBytes());
        return cifrado;
    }

    public byte[] descriptografaComAES(byte[] textoCifrado, SecretKey chave, IvParameterSpec vi) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, chave, vi);
        return cipher.doFinal(textoCifrado);
    }
    
    public byte[] descriptografaComAES(byte[] textoCifrado) throws Exception {
        return descriptografaComAES( textoCifrado, chave, vi);
    }
}
