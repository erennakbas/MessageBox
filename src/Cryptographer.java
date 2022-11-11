import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
public class Cryptographer {
        private SecretKey key;
        IvParameterSpec initVector;


        private Cipher encryptionCipher;
        public Cryptographer() throws Exception {
            byte[] secretKey = "MySecret".getBytes();
            byte[] iv = "Vector12".getBytes();
            this.initVector = new IvParameterSpec(iv);
            this.key = new SecretKeySpec(secretKey, 0, secretKey.length, "DES");
        }
        public byte[] encrypt(byte[] blocks) throws Exception {
            //initialize ECB mode.
            encryptionCipher = Cipher.getInstance( "DES/CBC/PKCS5Padding");
            encryptionCipher.init(Cipher.ENCRYPT_MODE, key, initVector);
            //encrypt the block
            byte[] encryptedBlocks = encryptionCipher.doFinal(blocks);
            return encryptedBlocks;
        }

        public byte[] decrypt(byte[] encryptedBlocks) throws Exception {
            Cipher decryptionCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            decryptionCipher.init(Cipher.DECRYPT_MODE, key, initVector);
            //decrypt the block
            byte[] decryptedBytes = decryptionCipher.doFinal(encryptedBlocks);
            return decryptedBytes;
        }
}

