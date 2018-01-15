import java.util.Scanner;

public class CeasarCipher {

    // initializing all the alphabets to variable Al
    public static final String Al = "abcdefghijklmnopqrstuvwxyz";

    //Program starts here
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your text to be encrypted: ");
        String text;
        text = sc.next(); //Getting input from user
        System.out.println("Encrypted message: " + encryption(text, 3)); // Encrypting the text with a shift of value 3
        System.out.println("Decrypted message: " + decryption(encryption(text, 3), 3)); // Decrypting the text with a shift of value 3
        sc.close();
    }

    //Encryption Method with parameters(text to be encrypted, amount to shift alphabets)
    public static String encryption(String text, int shift)
    {
        text = text.toLowerCase(); // converting all to lower case before encrypting
        String cipherText = ""; // Variable to store encrypted text
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
        String plainText = ""; // Variable to store decrypted text
        for (int i = 0; i < cipherText.length(); i++)
        {
            int position = Al.indexOf(cipherText.charAt(i)); //Finding position of character in Al
            int decryptedValIndex = (position - shift) % 26; //Finding index of decrypted character
            if (decryptedValIndex < 0)
            {
                decryptedValIndex = Al.length() + decryptedValIndex; // Add length of Al(26) if value is negative
            }
            char realVal = Al.charAt(decryptedValIndex); //Decrypted Character value in Al at index found
            plainText += realVal;
        }
        return plainText;
    }

}
