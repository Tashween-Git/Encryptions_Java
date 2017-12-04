import java.util.Scanner;

public class CeasarCipher {

    // initializing all the alphabets to variable Al
    public static final String Al = "abcdefghijklmnopqrstuvwxyz";

    //Encryption Method with parameters(text to be encrypted, amount to shift alphabets)
    public static String encryption(String text, int shift)
    {
        text = text.toLowerCase(); // converting all to lower case before encrypting
        String cipherText = ""; // declaring a new variable which will hold the encrypted text
        for (int i = 0; i < text.length(); i++)
        {
            int position = Al.indexOf(text.charAt(i)); //getting the index of the alphabet at position i in Al
            int newAlPosition = (shift + position) % 26; //getting the index of the new encrypted alphabet
            char newAl = Al.charAt(newAlPosition); //getting the alphabet from the index
            cipherText += newAl; //concatenating every encrypted alphabet
        }
        return cipherText;
    }

    public static String decryption(String cipherText, int shift)
    {
        cipherText = cipherText.toLowerCase();
        String plainText = "";
        for (int i = 0; i < cipherText.length(); i++)
        {
            int charPosition = Al.indexOf(cipherText.charAt(i));
            int keyVal = (charPosition - shift) % 26;
            if (keyVal < 0)
            {
                keyVal = Al.length() + keyVal;
            }
            char replaceVal = Al.charAt(keyVal);
            plainText += replaceVal;
        }
        return plainText;
    }
}
