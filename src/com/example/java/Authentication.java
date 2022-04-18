package com.example.java;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Authentication {

    private HashMap<String, String> loginInfo = new HashMap<>(); // user, hashedPassword

    public void getLoginInfo() {
        loginInfo.putAll(IOmodule.readAuthFile());
    }

    public boolean checkUser (String userID) {
        getLoginInfo();
        if (loginInfo.containsKey(userID)) {
            return true;
        }
        else {
            return false;
        }
    }


    // getting the username and password to register a user
    public void register (String userID, String passW) {
        if (this.checkUser(userID)) {
            System.out.println("The user already exists.");
        }
        else {
            String password = hashing(passW);
            loginInfo.put(userID, password);
        }
        IOmodule.writeAuthFile(loginInfo);
    }

    // to hash the password for security purposes
    public String hashing (String pwd) {
        try {
            MessageDigest msgDigest = MessageDigest.getInstance("SHA");
            msgDigest.update(pwd.getBytes());
            byte[] byteArray = msgDigest.digest();
            StringBuilder sb = new StringBuilder();

            for (byte bt : byteArray) {
                sb.append(String.format("%02x", bt));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";

    }

    // to check if the password match the records
    public boolean checkLogin (String userN, String pass) {
        try {
            getLoginInfo();
            String hashedPW = loginInfo.get(userN).toString();
            String hashingPass = hashing(pass);

            if (hashingPass.equals(hashedPW)) {
                return true;
            }
            else {
                return false;
            }
        }

        catch (Exception noAccount){
            return false;
        }

    }

}