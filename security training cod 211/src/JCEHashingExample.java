import java.security.MessageDigest;

import javax.crypto.SecretKey;

public class JCEHashingExample {
	
	private static String transformation = "SHA-256";
	
	public static void main(String[] args) {
		try {
			
			String passwordToHash = "miclave";
			String passwordSalt = "<Your app-specific salt value>";
			
			//create a message digest object
			MessageDigest mdigest = MessageDigest.getInstance(transformation);
			
			//calculate the hash value
			mdigest.update((passwordToHash+passwordSalt).getBytes());
			byte[] hashValue = mdigest.digest();
			System.out.println("Hash value = " + byteBufferToHex(hashValue));
			
		} catch (Exception e) {
			// TODO: handle exception
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
