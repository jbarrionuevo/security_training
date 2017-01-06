import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class JCESymmetricExample {
	private static String transformation = "AES/CBC/PKCS5PAdding";
	private static SecretKey aesKey;
	
	public static void main(String[] args) {
		//clear text to encrypt
		byte[] clearTextData = "Soy Jorge".getBytes();
		System.out.println("Cleartext = " + byteBufferToHex(clearTextData));
		
		
		try {
			//create a key
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(128);
			aesKey = keyGen.generateKey();
			
			//create an AES cipher object
			Cipher aesCipher = Cipher.getInstance(transformation);
			aesCipher.init(Cipher.ENCRYPT_MODE, aesKey);
			
			//Encrypt
			byte[] encryptedData = aesCipher.doFinal(clearTextData);
			System.out.println("Encrypted = " + byteBufferToHex(encryptedData));
			
			//Decrypt
			IvParameterSpec iv = new IvParameterSpec(aesCipher.getIV());
			aesCipher.init(Cipher.DECRYPT_MODE, aesKey, iv);
			System.out.println("Decrypted = " + byteBufferToHex(aesCipher.doFinal(encryptedData)));
			
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
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String byteBufferToHex(byte[] buffer) {
		StringBuilder strBuilder = new StringBuilder();
		for(byte b: buffer){
			//Append the new byte to hex string
			strBuilder.append(String.format("%02x", b & 0xff));
		}
		return (strBuilder.toString());
	}
}
