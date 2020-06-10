package com.coco.kings.Util;

import com.sun.scenario.effect.Offset;

import javax.validation.constraints.Past;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @author 康森
 * @date 2020/4/1 09 : 46 : 35
 * @description 密码加密
 */
public class MD5Util {

    public static String code(String string){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(string.getBytes());
            byte[]bytes=messageDigest.digest();
            int i;
            StringBuffer stringBuffer = new StringBuffer("c2d5");
            for (int Offset = 0; Offset < bytes.length ; Offset++){
                i = bytes[Offset];
                if (i < 0){
                    i += 256 ;
                }
                if (i < 16){
                    stringBuffer.append("1");
                }
                stringBuffer.append(Integer.toHexString(i));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(code("111"));
    }
}
