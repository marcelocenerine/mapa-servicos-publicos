package br.com.servicospublicos.infra.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;


public class CryptUtil {

    public static String calculateHMAC(String value, String secret) {
    	try {
    		Mac hmacSha256 = Mac.getInstance("HmacSHA256");
    		SecretKeySpec key = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256");
    		hmacSha256.init(key);
    		byte[] mac = hmacSha256.doFinal(value.getBytes("UTF-8"));
    		byte[] base64 = Base64.encodeBase64(mac);
    		return new String(base64, "UTF-8");
    	} catch (Exception e) {
    		throw new RuntimeException(e);
    	}
    }

}
