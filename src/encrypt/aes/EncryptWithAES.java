package encrypt.aes;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptWithAES {

    private final String secretKey = "thisis16byteskey";

    /**
     * CBC, PADD 없는 AES 암호화
     * 1. plain을 bytes로 변환
     * 2. bytes를 암호화 하여 encrypted bytes 생성
     * 3. encrypted bytes를 base64로 string 인코딩(데이터 관리 편의를 위해)
     * 4. base 64 encoded encryptes bytes를 encrypted bytes로 디코딩
     * 5. encrypted bytes를 복호화 하여 decrypted bytes 생성
     * 6. decrypted bytes를 plain string으로 변환
     * @param plain
     */
    public void normalAES(String plain) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        //SecretKey 객체 생성
        SecretKey key = new SecretKeySpec(secretKey.getBytes(), "AES");

        //암호화를 위한 Cipher 객체 생성
        Cipher cipher = Cipher.getInstance("AES");

        //Cipher 모드 설정 및 암호화
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plain.getBytes());
        String encrypted = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("String After Encoding Encrypted Bytes : " + encrypted);

        //Cipher 모드 설정 및 암호화
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encrypted));
        String decrypted = new String(decryptedBytes);
        System.out.println("decrypted : " + decrypted);
    }

    /**
     * CBC, PADD를 적용한 AES 암호화
     * @param plain
     */
    public void aesWithCBCPADD(String plain) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        //SecretKey 객체 생성
        SecretKey key = new SecretKeySpec(secretKey.getBytes(), "AES");

        //CBC암호화를 위한 initial vector 생성
        IvParameterSpec iv = new IvParameterSpec("thisis16bytesIvs".getBytes());

        //암호화를 위한 Cipher 객체 생성
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        //Cipher 모드 설정 및 암호화
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] encryptedBytes = cipher.doFinal(plain.getBytes());
        String encrypted = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("String After Encoding Encrypted Bytes : " + encrypted);

        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        String decrypted = new String(decryptedBytes);
        System.out.println("decrypted : " + decrypted);
    }
}
