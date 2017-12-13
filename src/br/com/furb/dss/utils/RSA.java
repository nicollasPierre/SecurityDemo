/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.dss.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;

/**
 *
 * @author nicol
 */
public class RSA {

    private PrivateKey chavePriv = null;
    private PublicKey chavePubli = null;
    private IvParameterSpec vi = null;
    private static RSA instance;

    private RSA() {
    }

    public static RSA getInstance(){
        return instance == null ? new RSA() : instance;
    }
    public PrivateKey getChavePriv() {
        return chavePriv;
    }

    public void setChavePriv(PrivateKey chavePriv) {
        this.chavePriv = chavePriv;
    }

    public PublicKey getChavePubli() {
        return chavePubli;
    }

    public void setChavePubli(PublicKey chavePubli) {
        this.chavePubli = chavePubli;
    }

    public IvParameterSpec getVi() {
        return vi;
    }

    public void setVi(IvParameterSpec vi) {
        this.vi = vi;
    }
    
    public byte[] criptografaComRSA(String mensagem) throws Exception {
        // Passo 1
        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
        KeyPair par = keyGenerator.generateKeyPair();
        // Passo 2
        if (chavePriv == null) {
            chavePriv = par.getPrivate();
        }

        if (chavePubli == null) {
            chavePubli = par.getPublic();
        }

        // Passo 3
        Cipher cipherPubli = Cipher.getInstance("RSA");
        cipherPubli.init(Cipher.ENCRYPT_MODE, chavePubli);
        // RSA/ECB/OAEPWithSHA-256AndMGF1Padding
        // Passo 4

        byte[] cifr = cipherPubli.doFinal(mensagem.getBytes());

        return cifr;
    }

    private byte[] descriptografaComRSA(byte[] textoCifrado, PublicKey chavePubli, PrivateKey chavePriv)
            throws Exception {

        Cipher cipherPriv = Cipher.getInstance("RSA");
        cipherPriv.init(Cipher.DECRYPT_MODE, chavePriv);

        byte[] descriptografada = cipherPriv.doFinal(textoCifrado);
        return descriptografada;
    }
    
    private byte[] descriptografaComRSA(byte[] textoCifrado)
            throws Exception {

        return descriptografaComRSA(textoCifrado, chavePubli, chavePriv);
    }

    public static byte[] assinarMensagem(PrivateKey chavePriv, byte[] mensagem) throws Exception {

        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(chavePriv);
        sign.update(mensagem);
        return sign.sign();

    }

    public static boolean verificarMensagem(PublicKey chavePubli, byte[] assinado, String mensagem) throws Exception {
        Signature verifica = Signature.getInstance("SHA256withRSA");
        verifica.initVerify(chavePubli);
        verifica.update(mensagem.getBytes());
        return verifica.verify(assinado);
    }
}
