/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.dss.utils;

import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

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

    public AES(byte[] simetrica, byte[] vi) {
        this.chave = new SecretKeySpec(simetrica, 0, simetrica.length, "AES");
        this.vi = new IvParameterSpec(vi);
    }

    public AES(byte[] simetrica) {
        if (simetrica.length > 0) {
            this.chave = new SecretKeySpec(simetrica, 0, simetrica.length, "AES");
        }
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

    public byte[] descriptografaComAES(String textoCifradoBase64, SecretKey chave, IvParameterSpec vi) throws Exception {
        byte[] textoCifrado = Base64.getDecoder().decode(textoCifradoBase64);
        System.out.println(textoCifrado.length);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, chave, vi);
        return cipher.doFinal(textoCifrado);
    }

    public byte[] descriptografaComAES(String textoCifradoBase64) throws Exception {
        return descriptografaComAES(textoCifradoBase64, chave, vi);
    }
}
