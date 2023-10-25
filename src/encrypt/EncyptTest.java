package encrypt;

import encrypt.aes.EncryptWithAES;

import common.ExceptionPrinter;
import encrypt.des.EncryptWithDES;
import encrypt.rsa.EncryptWithRSA;

public class EncyptTest {
    public static void main(String[] args) {
        EncryptWithAES encryptWithAES = new EncryptWithAES();
        EncryptWithRSA encryptWithRSA = new EncryptWithRSA();
        EncryptWithDES encryptWithDES = new EncryptWithDES();

        try {
            encryptWithAES.normalAES("This is AES with No CBC and No PADD");
            encryptWithAES.aesWithCBCPADD("This is AES with CBC and PADD");
            encryptWithRSA.normalRSA("testId");
            encryptWithRSA.normalRSA("testPass");
            encryptWithRSA.rsaWithCBCPADD("This is RSA with CBC and PADD");
            encryptWithDES.pbeWithDES();
        } catch (Exception e) {
            ExceptionPrinter.print(e);
        }
    }
}
