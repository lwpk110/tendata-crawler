package cn.xinbee.rcs.util;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import sun.misc.BASE64Decoder;

/**
 * Created by jeashi on 2017/1/9.
 */
public class TripleDES {
    public final  static  String KEY = "TendataTE0ndataTENdata2017tendata";
    public static void main(String[] args) throws Exception {

        String key = "TendataTE0ndataTENdata2017tendata";

        String data = "this is 腾达";

        System.out.println("ECB加密解密");
        String isoString = des3EncodeECBStr(key, data);
        System.out.println(isoString);
        String str4 = ees3DecodeECBStr(key, isoString);
        System.out.println(str4);
    }

    /**
     * des3加密
     *
     * @param keyByte 密匙
     * @param dataByte 被加密的秘钥
     * @throws Exception
     */
    public static byte[] des3EncodeECB(byte[] keyByte, byte[] dataByte)
        throws Exception {

        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(keyByte);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");

        cipher.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] bOut = cipher.doFinal(dataByte);
        return bOut;
    }

    /**
     * 加密
     *
     * @param key 密匙
     * @param date 被加密的秘钥
     * @throws Exception
     */
    public static String des3EncodeECBStr(String key, String date)
        throws Exception {

        byte[] keyByte = new BASE64Decoder().decodeBuffer(key);
        byte[] dataByte = date.getBytes("UTF-8");

        byte[] bOut = TripleDES.des3EncodeECB(keyByte, dataByte);
        String result = encodeToString(bOut);
        return result;
    }

    /**
     * 解密
     *
     * @throws Exception
     */
    public static byte[] ees3DecodeECB(byte[] keyByte, byte[] date)
        throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(keyByte);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");

        cipher.init(Cipher.DECRYPT_MODE, deskey);

        byte[] bOut = cipher.doFinal(date);
        return bOut;
    }

    public static String ees3DecodeECBStr(String key, String date)
        throws Exception {
        byte[] keyByte = new BASE64Decoder().decodeBuffer(key);
        byte[] dateByte = decode(date);

        byte[] bOut = TripleDES.ees3DecodeECB(keyByte, dateByte);
        return new String(bOut, "UTF-8");
    }

    private static final char[] DIGITS = {
        '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    /**
     * byte[]转String
     */
    public static String encodeToString(byte[] bytes) {
        char[] encodedChars = encode(bytes);
        return new String(encodedChars);
    }

    public static char[] encode(byte[] data) {
        int l = data.length;
        char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS[0x0F & data[i]];
        }
        return out;
    }

    /**
     * String转byte[]
     */
    public static byte[] decode(String hex) {
        return decode(hex.toCharArray());
    }

    public static byte[] decode(char[] data) throws IllegalArgumentException {
        int len = data.length;
        if ((len & 0x01) != 0) {
            throw new IllegalArgumentException("Odd number of characters.");
        }
        byte[] out = new byte[len >> 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f = f | toDigit(data[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }
        return out;
    }

    protected static int toDigit(char ch, int index) throws IllegalArgumentException {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new IllegalArgumentException(
                "Illegal hexadecimal charcter " + ch + " at index " + index);
        }
        return digit;
    }
}
