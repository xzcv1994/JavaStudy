package encrypt;

import encrypt.aes.EncryptWithAES;

import common.ExceptionPrinter;

public class EncyptTest {
    public static void main(String[] args) {
        EncryptWithAES encryptWithAES = new EncryptWithAES();

        try {
            encryptWithAES.normalAES("This is AES with No CBC and No PADD");
            encryptWithAES.aesWithCBCPADD("This is AES with CBC and PADD");
        } catch (Exception e) {
            ExceptionPrinter.print(e);
        }
    }
}
