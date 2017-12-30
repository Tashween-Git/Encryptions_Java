import sun.awt.Symbol;

import java.util.Scanner;

public class PolyalphabeticCipher
{
    // Program starts here
    public static void main(String[] args)
    {
        final String key = "VIGENERECIPHER"; // setting the word 'VIGENERECIPHER' as encryption key
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Text: ");
        String message = sc.next(); // Getting user input
        String encryptedText = encrypt(message, key); // calling encryption method and getting encrypted text
        System.out.println("Entered Text: " + message); // output input text
        System.out.println("Encrypted text: " + encryptedText); // output encrypted text
        System.out.println("Decrypted text: " + decrypt(encryptedText, key)); // output decrypted text
    }

    // Encryption Method
    public static String encrypt(String text, String key)
    {
        String res = ""; // variable to store encrypted text
        text = text.toUpperCase(); // converting whole text to upper case
        for (int i = 0, j = 0; i < text.length(); i++)
        {
            char c = text.charAt(i); // getting character at position i from input text
            if (c < 'A' || c > 'Z')
                continue;
//            System.out.println((c));
//            System.out.println(key.charAt(j));
//            System.out.println((c+ key.charAt(j)));
//            System.out.println((c+ key.charAt(j) - 2 ));
//            System.out.println(2 * 'A');
//            System.out.println((c+ key.charAt(j) - 2 * 'A'));
//            System.out.println((c + key.charAt(j) - 2 * 'A') % 26 + 'A');

            res += (char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A'); // calculating the encrypted value for the character and concatenating to variable
            j = ++j % key.length();
        }
        return res;
    }

    // Decryption Method
    public static String decrypt(String text, String key)
    {
        String res = ""; // variable to store decrypted text
        text = text.toUpperCase(); // converting whole text to upper case
        for (int i = 0, j = 0; i < text.length(); i++)
        {
            char c = text.charAt(i); // getting character at position i from encrypted text
            if (c < 'A' || c > 'Z')
                continue;
            res += (char) ((c - key.charAt(j) + 26) % 26 + 'A'); // calculating the encrypted value for the character and concatenating to variable
            j = ++j % key.length();
        }
        return res;
    }

}