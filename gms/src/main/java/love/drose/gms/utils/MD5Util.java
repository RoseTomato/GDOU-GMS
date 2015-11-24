package love.drose.gms.utils;

import java.security.MessageDigest;

/**
 * md5算法加密工具类.
 * Created by lovedrose on 2015/11/22.
 */
public class MD5Util {
    public static String getMD5String(String str) {
        try {
            // 获得MD5算法的messagedigest对象
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 重置以供再次使用
            messageDigest.reset();
            // 使用指定的Byte数组
            messageDigest.update(str.getBytes("UTF-8"));
            // 操作完成哈希算法
            byte[] byteArray = messageDigest.digest();

            StringBuffer md5StrBuff = new StringBuffer();

            for (int i = 0; i < byteArray.length; i++) {
                //以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式
                if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                    md5StrBuff.append("0").append(
                            Integer.toHexString(0xFF & byteArray[i]));
                else
                    md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }

            return md5StrBuff.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
