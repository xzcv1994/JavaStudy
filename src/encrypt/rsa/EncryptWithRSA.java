package encrypt.rsa;

import common.Constant;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class EncryptWithRSA {

    /**
     * 블록 암호화, 패딩 설정 없이 RSA 암호화
     * 1. private, publickey 값 파일에서 읽어옴
     * 2. 읽어온 값을 String으로 변환 후 불필요 문자열 제거
     * 3. 처리된 문자열을 base64 디코딩(private, publickey는 처음 생성하면 base64 인코딩 형태로 값이 생성된다.)
     * 4. private, publickey 생성을 위한 keySpec 생성
     * 5. KeyFactory 생성
     * 6. Private, PublicKey 객체 생성
     * 7. Cipher를 이용한 암호화, 복호화
     * @param plain
     */
    public void normalRSA(String plain) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        //비밀 키 파일 경로
        String privateKeyFilePath = "/Users/yangseungbin/Documents/private_key.pem";
        //공개 키 파일 경로
        String publicKeyFilePath = "/Users/yangseungbin/Documents/public_key.pem";

        //키값 획득
        byte[] privateKeyBase64Bytes = Files.readAllBytes(Paths.get(privateKeyFilePath));
        byte[] publicKeyBase64Bytes = Files.readAllBytes(Paths.get(publicKeyFilePath));

        String privateKeyBase64Str = new String(privateKeyBase64Bytes);
        String publicKeyBase64Str = new String(publicKeyBase64Bytes);

        privateKeyBase64Str = privateKeyBase64Str.replace(Constant.PRIVATE_KEY_PREFIX, "").replace(Constant.PRIVATE_KEY_POSTFIX,"").replace("\n","");
        publicKeyBase64Str = publicKeyBase64Str.replace(Constant.PUBLIC_KEY_PREFIX, "").replace(Constant.PUBLIC_KEY_POSTFIX,"").replace("\n","");

        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyBase64Str.getBytes());
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyBase64Str.getBytes());

        //private, public keySpec 정보 생성
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);

        //RSA keyFactory 생성
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        //private, public key 객체 생성
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        //cipher 객체 생성
        Cipher cipher = Cipher.getInstance("RSA");
        System.out.println("plain str : " + plain);

        //cipher 모드 설정 및 암호화
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(plain.getBytes());
        String encrypted = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("encrypted Base64 str : " + encrypted);

        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encrypted.getBytes()));
        String decrypted = new String(decryptedBytes);
        System.out.println("decrypted str : " + decrypted + "\n");
    }

    public void rsaWithCBCPADD(String plain) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        //비밀 키 파일 경로
        String privateKeyFilePath = "/Users/yangseungbin/Documents/private_key.pem";
        //공개 키 파일 경로
        String publicKeyFilePath = "/Users/yangseungbin/Documents/public_key.pem";

        byte[] privateKeyBase64Bytes = Files.readAllBytes(Paths.get(privateKeyFilePath));
        byte[] publicKeyBase64Bytes = Files.readAllBytes(Paths.get(publicKeyFilePath));

        String privateKeyBase64Str = new String(privateKeyBase64Bytes);
        String publicKeyBase64Str = new String(publicKeyBase64Bytes);

        privateKeyBase64Str = privateKeyBase64Str.replace(Constant.PRIVATE_KEY_PREFIX, "").replace(Constant.PRIVATE_KEY_POSTFIX,"").replace("\n","");
        publicKeyBase64Str = publicKeyBase64Str.replace(Constant.PUBLIC_KEY_PREFIX, "").replace(Constant.PUBLIC_KEY_POSTFIX,"").replace("\n","");

        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyBase64Str.getBytes());
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyBase64Str.getBytes());

        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        System.out.println("plain str : " + plain);

        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(plain.getBytes());
        String encrypted = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("encrypted Base64 str : " + encrypted);

        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        String decrypted = new String(decryptedBytes);
        System.out.println("decrypted str : " + decrypted + "\n");
    }
}
