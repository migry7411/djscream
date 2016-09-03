package test.migry;

import net.migry.common.Encrypt;

public class TestEncrypt {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String text = "test@test.com";
   	 
        String enen = Encrypt.encrypt(text);
        String dede = Encrypt.decrypt(enen);
        
        System.out.println("\nOrigin text: " + text);
        System.out.println("\nEncrypted text: " + enen);
        System.out.println("\nDecrypted text: " + dede);
	}

}
