import java.util.Scanner;

public class MonoalphabeticCipher
{
    public static char l[]  = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' }; // lowercase alphabet array
    public static char ev[] = { 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M' }; // encrypted value array

    
    //Program starts here
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in); // initializing scanner for input
        System.out.println("Enter the message: "); // asking for user input
        String en = encrypt(sc.next().toLowerCase()); // converting input text to lowercase first, calling encryption method then storing into 'en'
        System.out.println("Encrypted message: " + en); // output the encrypted text
        System.out.println("Decrypted message: " + decrypt(en)); // calling the decryption method with the encrypted text as variable
        sc.close();
    }

    // Encryption Method
    public static String encrypt(String t)
    {
        char e[] = new char[(t.length())]; // getting the length on the input text, t, to set the size of the encrypted variable, c
        for (int i = 0; i < t.length(); i++)
        {
            for (int j = 0; j < 26; j++)
            {
                if (l[j] == t.charAt(i)) // looking for input character into lowercase alphabet array
                {
                    e[i] = ev[j]; // getting the corresponding encryption character for the input character
                    break; // break from for loop, to get next character from input text
                }
            }
        }
        return (new String(e)); // returning encrypted text to calling function in main
    }

    // Decryption Method
    public static String decrypt(String e)
    {
        char d[] = new char[(e.length())]; // getting the length of the encrypted text, e to set size of decrypted variable, d
        for (int i = 0; i < e.length(); i++)
        {
            for (int j = 0; j < 26; j++)
            {
                if (ev[j] == e.charAt(i)) // looking for encrypted character into encrypted value array
                {
                    d[i] = l[j]; // getting the corresponding lowercase character for the encrypted character
                    break; // break from for loop, to get next character from encrypted text
                }
            }
        }
        return (new String(d)); // returning decrypted text to calling function in main
    }
    
}