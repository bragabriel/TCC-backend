package br.com.spotted.backend.domain.dto;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Criptografia {

    public static String encriptografar(String senha) {
        String retorno = "";
        MessageDigest md;

        try {
            md = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
            retorno = hash.toString(16); //Retornando um hexadecimal
        }catch (Exception e){
            System.out.println(e);
        }

        return retorno;
    }
}
