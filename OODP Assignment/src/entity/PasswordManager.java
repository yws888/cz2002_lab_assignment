package entity;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;

/**
 * PasswordManger carries the persistent information of verifying
 * the password as well as securing it to allow data hiding.
 * 
 */

public class PasswordManager {
    /**
     * 
     */
    private static final Random RANDOM = new SecureRandom();
    /**
     *  number of iterations
     */
    private static final int ALGO_ITERATIONS = 10000;
    /**
     * 
     */
    private static final int KEY_LENGTH = 256;
    /**
     *  name of algorithm
     */
    private static final String HASHALGO = "PBKDF2WithHmacSHA1";

    /**
     * 
     */
    private PasswordManager(){}

    /**
     * @return
     */
    public static byte[] getRandomSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return salt;
    }

    /**
     * @param password
     * @param salt
     * @return
     */
    public static byte[] hash(char[] password, byte[] salt){
        PBEKeySpec spec = new PBEKeySpec(password, salt, ALGO_ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try{
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(HASHALGO);
            return secretKeyFactory.generateSecret(spec).getEncoded();
        }catch (NoSuchAlgorithmException | InvalidKeySpecException ex){
            throw new AssertionError("Error hashing password" + ex.getMessage(), ex);
        }finally{
            spec.clearPassword();
        }
    }
    /**
     * @param password
     * @param salt
     * @param expectedHash
     * @return
     */
    public static boolean PasswordValidation(char[] password, byte[] salt, byte[] expectedHash) {
        byte[] pwdHash = hash(password, salt);
        Arrays.fill(password, Character.MIN_VALUE);
        if (pwdHash.length != expectedHash.length) return false;
        for (int i = 0; i < pwdHash.length; i++) {
            if (pwdHash[i] != expectedHash[i]) return false;
        }
        return true;
    }
    /**
     * @param input
     * @return
     */
    public static String hexEncoder( byte[] input ){
        StringBuffer result = new StringBuffer();
        char[] digits = {'0', '1', '2', '3', '4','5','6','7','8','9','a','b','c','d','e','f'};
        for (int i = 0; i < input.length; ++i) {
            byte b = input[i];
            result.append( digits[ (b&0xf0) >> 4 ] );
            result.append( digits[ b&0x0f] );
        }
        return result.toString();
    }
    /**
     * @param input
     * @return
     */
    public static byte[] hexDecoder(String input) {
        int len = input.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(input.charAt(i), 16) << 4)
                    + Character.digit(input.charAt(i+1), 16));
        }
        return data;
    }


    /**
     * @param length
     * @return
     */
    public static String generateRNGPassword(int length){
        StringBuilder sb = new StringBuilder(length);
        for(int i = 0; i < length; i++){
            int c = RANDOM.nextInt(62);
            if(c <= 9){
                sb.append(String.valueOf(c));
            }else if(c < 36){
                sb.append((char)('a' + c - 10));
            }else{
                sb.append((char)('A' + c - 36));
            }

        }
        return sb.toString();
    }

}
