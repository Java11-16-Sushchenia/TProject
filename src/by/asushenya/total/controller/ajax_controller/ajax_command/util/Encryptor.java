package by.asushenya.total.controller.ajax_controller.ajax_command.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

/**
 * Class encrypt user passwords
 * 
 * @author Artyom Sushenya
 *
 */
public class Encryptor {

	private static final Logger log = Logger.getLogger(Encryptor.class);

	/**
	 * 
	 * @param hashedString
	 *            hashed string
	 * @return hash of hashedString according to MD5 algorithm
	 */
	public static String getMD5Hash(String hashedString) {
		MessageDigest messageDigest = null;
		byte[] digest = new byte[0];

		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(hashedString.getBytes());
			digest = messageDigest.digest();
		} catch (NoSuchAlgorithmException e) {
			log.error("md5 hash getting error", e);

		}

		BigInteger bigInt = new BigInteger(1, digest);
		String md5Hex = bigInt.toString(16);

		while (md5Hex.length() < 32) {
			md5Hex = "0" + md5Hex;
		}
		return md5Hex;
	}
}
