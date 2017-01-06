import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class JCEASymmetricExample {
	private static String transformation = "RSA/ECB/PKCS1Padding";
	private static SecretKey aesKey;

	public static void main(String[] args) {

		try {
			// create a symmetric key to protect with asymmetric crypthography
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			aesKey = keyGen.generateKey();
			byte[] symmKey = aesKey.getEncoded();
			System.out.println("Symm key = " + byteBufferToHex(symmKey));

			// create an RSA cipher object
			Cipher rsaCipher = Cipher.getInstance(transformation);
			KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
			kpGen.initialize(2048);
			KeyPair keyPair = kpGen.generateKeyPair();
			Key publicKey = keyPair.getPublic();
			Key privateKey = keyPair.getPrivate();

			// Encrypt
			rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] cipherText = rsaCipher.doFinal(symmKey);
			System.out.println("Encrypted = " + byteBufferToHex(cipherText));

			// Decrypt

			rsaCipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] plainText = rsaCipher.doFinal(cipherText);
			System.out.println("Decrypted = " + byteBufferToHex(plainText));

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String byteBufferToHex(byte[] buffer) {
		StringBuilder strBuilder = new StringBuilder();
		for (byte b : buffer) {
			// Append the new byte to hex string
			strBuilder.append(String.format("%02x", b & 0xff));
		}
		return (strBuilder.toString());
	}
}
