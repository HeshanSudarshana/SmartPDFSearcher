package business_logic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class PasswordHasher {

//    public static void main(String[] args) {
//        System.out.println(generateHash("abc"));
//    }

    public static String generateHash(String password) {
        String salt = "smartPDFsearchersalttext";
        StringBuilder hash = new StringBuilder();
        String saltedText = password + salt;
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(saltedText.getBytes());
            char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f' };
            for (int idx = 0; idx < hashedBytes.length; ++idx) {
                byte b = hashedBytes[idx];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            // handle error here.
            System.out.println("System Error!");
        }

        return hash.toString();
    }
}