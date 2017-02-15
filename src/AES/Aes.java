package AES;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Aes {
    private final static Logger LOGGER = LoggerFactory.getLogger(Aes.class);
    private final static String KEY_ALGORITHM = "AES";
    private final static String KEY = "bd55vc9df6e3ddb9";
    private final static int KEY_SIZE = 16;
    private final static String IV_SPEC = "3e5c6vfd9dfde5vb";
    private final static int IV_SPEC_SIZE = 16;
    private final static String MODEL = "AES/CBC/PKCS5Padding";
    
    public static String encrypt(String content) {
        if(content == null || content.length() == 0){
            return null;
        }
        try{
            SecretKeySpec skeySpec = getKey(KEY);
            IvParameterSpec iv = getIvKey(IV_SPEC);
            Cipher cipher = Cipher.getInstance(MODEL);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] bs = cipher.doFinal(content.getBytes());
            return parseByte2HexStr(bs);
        }catch(Exception e){
            LOGGER.error(e.getMessage(), e);
            return content;
        }
    }

    public static String decrypt(String content) {
        if(content == null || content.length() == 0){
            return null;
        }
        try{
            byte[] bs = parseHexStr2Byte(content);
            SecretKeySpec skeySpec = getKey(KEY);
            IvParameterSpec iv = getIvKey(IV_SPEC);
            Cipher cipher = Cipher.getInstance(MODEL);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            bs = cipher.doFinal(bs);
            return new String(bs);
        }catch(Exception e){
            LOGGER.error(e.getMessage(), e);
            return content;
        }
    }

    private static SecretKeySpec skeySpec = null;
    private static SecretKeySpec getKey(String strKey) throws Exception {
        if(skeySpec == null){
            byte[] arrBTmp = strKey.getBytes();
            byte[] arrB = new byte[KEY_SIZE];
            for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
                arrB[i] = arrBTmp[i];
            }
            skeySpec = new SecretKeySpec(arrB, KEY_ALGORITHM);
        }
        return skeySpec;
    }
    private static IvParameterSpec iv = null;
    private static IvParameterSpec getIvKey(String strKey) throws Exception {
        if(iv == null){
            byte[] arrBTmp = strKey.getBytes();
            byte[] arrB = new byte[IV_SPEC_SIZE];
            for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
                arrB[i] = arrBTmp[i];
            }
            iv = new IvParameterSpec(arrB);
        }
        return iv;
    }
    
    public static String parseByte2HexStr(byte buf[]) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static byte[] parseHexStr2Byte(String hex) {
        int len = hex.length();
        byte[] buf = new byte[((len + 1) / 2)];
        int i = 0, j = 0;
        if ((len % 2) == 1) {
            buf[j++] = (byte) hexDigit(hex.charAt(i++));
        }
        while (i < len) {
            buf[j++] = (byte) ((hexDigit(hex.charAt(i++)) << 4) | hexDigit(hex
                    .charAt(i++)));
        }
        return buf;
    }
    
    public static int hexDigit(char ch) {
        if (ch >= '0' && ch <= '9') {
            return ch - '0';
        }
        if (ch >= 'A' && ch <= 'F') {
            return ch - 'A' + 10;
        }
        if (ch >= 'a' && ch <= 'f') {
            return ch - 'a' + 10;
        }
        return (0);
    }

//以下为简单测试示例
    public static void main(String[] args) throws Exception {
        String content = "F087166F51D1B4CA3712BC038FD4D855844FED1DD91C2042040E649C4A780AFF";
        System.out.println("原文：" + content);
        content = encrypt(content);
        System.out.println("加密：" + content);
        content = decrypt(content);
        System.out.println("解密：" + content);
    }
}
