import java.security.*;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAEncryption {
    public static void main(String[] args) {
        try {
            // Step 1: Generate RSA Key Pair
            var keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048); // 2048-bit key size for security
            KeyPair keyPair = keyGen.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            // Step 2: Encrypt Data
            String originalData = "Sensitive Payment Info";
            Cipher encryptCipher = Cipher.getInstance("RSA");
            encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey); // Initialize cipher with public key
            byte[] encryptedBytes = encryptCipher.doFinal(originalData.getBytes()); // Encrypt the data
            String encryptedData = Base64.getEncoder().encodeToString(encryptedBytes); // Convert to Base64 string

            // Step 3: Decrypt Data
            Cipher decryptCipher = Cipher.getInstance("RSA");
            decryptCipher.init(Cipher.DECRYPT_MODE, privateKey); // Initialize cipher with private key
            byte[] decryptedBytes = decryptCipher.doFinal(Base64.getDecoder().decode(encryptedData)); // Decrypt the data
            String decryptedData = new String(decryptedBytes); // Convert bytes back to string

            // Step 4: Display Results
            System.out.println("Original Data: " + originalData); // Print original data
            System.out.println("Encrypted Data: " + encryptedData); // Print encrypted data (Base64)
            System.out.println("Decrypted Data: " + decryptedData); // Print decrypted data

        } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            // Improved exception handling: Print error details
            System.out.println("Error: " + e.getMessage());
            // Print stack trace for more details
        }
    }
}
