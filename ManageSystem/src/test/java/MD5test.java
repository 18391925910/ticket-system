import org.junit.jupiter.api.Test;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * @author: elvis.yue@i9i8.com
 * @create: 2021-04-30 10:15
 */
public class MD5test {
    @Test
    void test(){
        while (true){
            System.out.println("please input md5 string:");
            String s=new Scanner(System.in).nextLine();
            try {
                System.out.println("解密值："+getMySQLPassword(s));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }
    public static String getMySQLPassword(String plainText)
            throws UnsupportedEncodingException {
        byte[] utf8 = plainText.getBytes("UTF-8");
        byte[] test = DigestUtils.sha(DigestUtils.sha(utf8));
        return "*" + convertToHex(test).toUpperCase();
    }

    private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9))
                    buf.append((char) ('0' + halfbyte));
                else
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }
}