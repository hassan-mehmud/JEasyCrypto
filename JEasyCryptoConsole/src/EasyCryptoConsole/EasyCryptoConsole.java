package EasyCryptoConsole;

import java.io.Console;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import easycrypto.EasyCryptoAPI;

public class EasyCryptoConsole {

    public static void main(String[] args) {

        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
            System.setProperty("file.encoding", "UTF-8");
            Console console = System.console();

            console.printf("Welcome to CryptoClient!\n");
            console.printf("Supported methods are: %s\n", EasyCryptoAPI.methods());

            while (true) {
                String d = console.readLine("Do you wish to encrypt or decrypt (e or d)? > ");
                EasyCryptoAPI.Result result;

                if (d.equalsIgnoreCase("e")) {
                    String e = console.readLine("Please enter text to be encrypted > ");
                    String m = console.readLine("Please enter encryption method > ");
                    result = EasyCryptoAPI.encrypt(e, m);
                } else if (d.equalsIgnoreCase("d")) {
                    String n = console.readLine("Please enter text to be decrypted > ");
                    String o = console.readLine("Please enter decryption method > ");
                    result = EasyCryptoAPI.decrypt(n, o);
                } else {
                    return;
                }
                switch (result.resultCode()) {
                    case ESuccess: {
                        console.printf("\n Encryption Successful: %d - %s\n", result.resultCode().ordinal(), result.resultCode().toString());
                        console.printf("Encrypted text is: %s \n", result.result());
                        System.out.println(result.result());
                        break;
                    }
                    case EError:
                    case ENotSupported: {
                        console.printf("Method does not conform Please try again: \n");
                        break;
                    }
                    case DSuccess: {
                        console.printf("\n Decryption Successful: %d - %s\n", result.resultCode().ordinal(), result.resultCode().toString());
                        console.printf("Decrypted text is: %s \n", result.result());
                        System.out.println(result.result());
                        break;
                    }
                    case DError:
                    case DNotSupported: {
                        console.printf("Method does not conform Please try again: \n");
                        break;
                    }
                }
            }
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
