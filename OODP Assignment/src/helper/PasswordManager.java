package helper;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;

/**
 * PasswordManager class handles all password related jobs,
 * such as salt hashing passwords using the PBKDF2-HMAC-SHA1 hashing algorithm to secure passwords,
 * and verifying passwords with the salt hashed passwords.
 * 
 */

public class PasswordManager {
    /**
     * Generates a random number to be used to generate a random salt for secure password hashing.
     * SecureRandom() is used instead of Random() since SecureRandom() generates a more non-predictable random number as
     * SecureRandom() implements Cryptographically Secure Pseudo-Random Number Generator (CSPRNG) which is more secure in the case
     * of generating a random salt.
     *
     */
    private static final Random RANDOM = new SecureRandom();
    /**
     *  The iteration count of the number of times that the password is hashed during the derivation of the symmetric key with PBEKeySpec.
     *  The higher number, the more difficult it is to validate a password guess and then derive the correct key.
     *  In this case it is used together with the salt, the reason of using a salt is to prevent against rainbow tables attacks.
     */
    private static final int ALGO_ITERATIONS = 10000;
    /**
     * Symmetric key length in bits generated from PBEKeySpec. In this case it is 256 bits.
     */
    private static final int KEY_LENGTH = 256;
    /**
     *  Defines the hashing algorithm used. Hashing algorithm used is PBKDF2-HMAC-SHA1,
     *  which is a password-based key derivation function
     */
    private static final String HASHALGO = "PBKDF2WithHmacSHA1";



    /**
     * This method generates a random salt in bytes for password+salt hashing
     * @return      Returns a randomly generated salt in bytes for password+salt hashing
     */
    public static byte[] getRandomSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return salt;
    }

    /**
     * This function securely hashes passwords to be stored securely in a database.
     * @param password      Plaintext password in char[] to hash
     * @param salt          Random salt generated to hash
     * @return              Returns an encrypted hashed password+salt password in byte format.
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
     * Verifies if input password is correct.
     * @param password          Password to verify
     * @param salt              Salt of the password
     * @param expectedHash      Expected hash output of password+salt to compare
     * @return                  returns "True" if expectedHash equals to to the hash format of the input password+salt, else returns "False"
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
     * Takes in a byte array and converts the byte array to hex format
     * @param input     Byte array
     * @return          Returns byte input in hex format
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
     * takes in a string in hex format and converts the string to a byte array
     * @param input     Hex format string
     * @return          Returns hex input in byte format
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
     * generates RNG Password
     * 
     * @param length		length of password
     * @return				randomly generated password
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
