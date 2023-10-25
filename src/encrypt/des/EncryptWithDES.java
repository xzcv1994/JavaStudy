package encrypt.des;

import common.Constant;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EncryptWithDES {
    public void pbeWithDES() throws IOException {
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

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        config.setPassword("thisispassword");
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);

        String encryptedPrivate = encryptor.encrypt(privateKeyBase64Str);
        String encryptedPublic = encryptor.encrypt(publicKeyBase64Str);

        System.out.println("privateKeyBase64 str PBE : " + encryptedPrivate);
        System.out.println("publicKeyBase64 str PBE : " + encryptedPublic);
        System.out.println("privateKeyBase64 str decrypted : " + encryptor.decrypt(encryptedPrivate));
        System.out.println("publicKeyBase64 str decrypted : " + encryptor.decrypt(encryptedPublic));
    }
}
