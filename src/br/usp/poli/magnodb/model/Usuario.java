package br.usp.poli.magnodb.model;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class Usuario {

    private String username;
    private boolean root;
    
    private int id;

    private byte[] salt = new byte[32];
    private byte[] passwordHash = new byte[32];

    public Usuario(String username, boolean root, byte[] salt, byte[] password) {
        this.username = username;
        this.root = root;
        this.salt = salt;
        this.passwordHash = password;
    }

    public Usuario(String username, String password, boolean root) {
        this.username = username.trim();
        this.root = root;

        Random random = new SecureRandom();
        random.nextBytes(salt);

        passwordHash = digest(password);
    }

    public Usuario(String username, String password) {
        this(username, password, false);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public byte[] getPasswordHash() {
        return passwordHash;
    }

    public byte[] getSalt() {
        return salt;
    }

    public boolean isRoot() {
        return root;
    }

    public boolean login(String password) {
        byte[] calculated = digest(password);

        return compare(passwordHash, calculated);
    }

    private boolean compare(byte[] passwordHash, byte[] calculated) {
        boolean match = true;
        for (int i = 0; i < passwordHash.length; ++i) {
            match &= passwordHash[i] == calculated[i];
        }
        return match;
    }

    private byte[] digest(String password) {
        byte[] hash = null;

        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            digest.update(password.getBytes(Charset.forName("utf-8")));
            digest.update(salt);

            hash = digest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hash;
    }
}
