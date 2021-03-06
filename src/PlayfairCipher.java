import java.util.Scanner;

public class PlayfairCipher
{
    private String KeyWord        = new String(); // KeyWord global variable available to all methods
    private String Key            = new String(); // Key global variable available to all methods
    private char   matrix_arr[][] = new char[5][5]; // a matrix(two dimensional array) of 5 x 5 available to all methods


    public static void main(String[] args)
    {
        PlayfairCipher pc = new PlayfairCipher();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a keyword:");
        String keyword = sc.next(); // user input the keyword
        pc.setKey(keyword); // assigning the input keyword to the global variable
        pc.KeyGen(); // calling the KeyGen method to generate the 5 x 5 matrix
        System.out.println("Enter your word to be encrypted: (Make sure length of message is even)");
        String key_input = sc.next().toLowerCase();
        if (key_input.length() % 2 == 0) // checking if input text is of even length
        {
            String encryptedText = pc.encryptMessage(key_input);
            System.out.println("Encryption: " + encryptedText);
            System.out.println("Decryption: " + pc.decryptMessage(encryptedText));

        }
        else
        {
            System.out.println("Message length should be even");
        }

        sc.close();
    }

    public void setKey(String k)
    {
        String K_adjust = new String();
        boolean flag = false;
        K_adjust = K_adjust + k.charAt(0);
        for (int i = 1; i < k.length(); i++)
        {
            for (int j = 0; j < K_adjust.length(); j++)
            {
                if (k.charAt(i) == K_adjust.charAt(j))
                {
                    flag = true;
                }
            }
            if (flag == false)
                K_adjust = K_adjust + k.charAt(i);
            flag = false;
        }
        KeyWord = K_adjust;
    }

    public void KeyGen()
    {
        boolean flag = true;
        char current;
        Key = KeyWord;
        for (int i = 0; i < 26; i++)
        {
            current = (char) (i + 97); // alphabet a to z
            if (current == 'j') // ignoring the alphabet j
                continue;
            for (int j = 0; j < KeyWord.length(); j++) // checking for existing alphabet in the keyword
            {
                if (current == KeyWord.charAt(j))
                {
                    flag = false;
                    break;
                }
            }
            if (flag)
                Key = Key + current; // concatenating all the alphabets to be in the 5 x 5 matrix
            flag = true;
        }
        System.out.println(Key);
        matrix(); // calling the matrix method
    }

    // method to make the 5 x 5 matrix
    private void matrix()
    {
        int counter = 0;
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                matrix_arr[i][j] = Key.charAt(counter);
                System.out.print(matrix_arr[i][j] + " ");
                counter++;
            }
            System.out.println();
        }
    }


    public String encryptMessage(String Source)
    {
        String src_arr[] = Divid2Pairs(Source);
        //System.out.println(src_arr.length);
        String Code = new String();
        char one;
        char two;
        int part1[] = new int[2];
        int part2[] = new int[2];
        for (int i = 0; i < src_arr.length; i++)
        {
            one = src_arr[i].charAt(0);
            two = src_arr[i].charAt(1);
            //System.out.println(one);
            //System.out.println(two);
            part1 = GetPositions(one);
            part2 = GetPositions(two);

            //System.out.println(part1);
            //System.out.println(part2);
            if (part1[0] == part2[0])
            {
                if (part1[1] < 4)
                    part1[1]++;
                else
                    part1[1] = 0;
                if (part2[1] < 4)
                    part2[1]++;
                else
                    part2[1] = 0;
            }
            else if (part1[1] == part2[1])
            {
                if (part1[0] < 4)
                    part1[0]++;
                else
                    part1[0] = 0;
                if (part2[0] < 4)
                    part2[0]++;
                else
                    part2[0] = 0;
            }
            else
            {
                int temp = part1[1];
                part1[1] = part2[1];
                part2[1] = temp;
            }
            Code = Code + matrix_arr[part1[0]][part1[1]]
                    + matrix_arr[part2[0]][part2[1]];
        }
        return Code;
    }

    // method to create the pairs of 2
    private String[] Divid2Pairs(String new_string)
    {
        //System.out.println(new_string);
        String Original = format(new_string); // calling the getDim
        //System.out.println(Original);

        int size = Original.length();
        if (size % 2 != 0) // if size of input string in not even, add 'x' to it
        {
            size++;
            Original = Original + 'x';
        }
        String x[] = new String[size / 2]; // creating an array of half the length of the input string after formatting
        int counter = 0;
        for (int i = 0; i < size / 2; i++) // creating the pairs of 2 alphabets
        {
            x[i] = Original.substring(counter, counter + 2);
            counter = counter + 2;
        }
        return x;
    }

    // format method to format the input string
    private String format(String old_text)
    {
        int i = 0;
        int len = 0;
        String text = new String();
        len = old_text.length();
        for (int tmp = 0; tmp < len; tmp++)
        {
            if (old_text.charAt(tmp) == 'j') // to replace the alphabet 'j' with 'i'
            {
                text = text + 'i';
            }
            else
                text = text + old_text.charAt(tmp);
        }
        len = text.length();
        for (i = 0; i < len; i = i + 2) // if two alphabets are the same, add 'x' between them
        {
            if (text.charAt(i + 1) == text.charAt(i))
            {
                text = text.substring(0, i + 1) + 'x' + text.substring(i + 1);
                System.out.println(text);
            }
        }
        return text;
    }

    // method to get position of character in the 5 x 5 matrix
    public int[] GetPositions(char letter)
    {
        int[] key = new int[2];
        if (letter == 'j')
            letter = 'i';
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                if (matrix_arr[i][j] == letter)
                {
                    key[0] = i;
                    key[1] = j;
                    break;
                }
            }
        }
        return key;
    }

    public String decryptMessage(String Code)
    {
        String Original = new String();
        String src_arr[] = Divid2Pairs(Code);
        char one;
        char two;
        int part1[] = new int[2];
        int part2[] = new int[2];
        for (int i = 0; i < src_arr.length; i++)
        {
            one = src_arr[i].charAt(0);
            two = src_arr[i].charAt(1);
            part1 = GetPositions(one);
            part2 = GetPositions(two);
            if (part1[0] == part2[0])
            {
                if (part1[1] > 0)
                    part1[1]--;
                else
                    part1[1] = 4;
                if (part2[1] > 0)
                    part2[1]--;
                else
                    part2[1] = 4;
            }
            else if (part1[1] == part2[1])
            {
                if (part1[0] > 0)
                    part1[0]--;
                else
                    part1[0] = 4;
                if (part2[0] > 0)
                    part2[0]--;
                else
                    part2[0] = 4;
            }
            else
            {
                int temp = part1[1];
                part1[1] = part2[1];
                part2[1] = temp;
            }
            Original = Original + matrix_arr[part1[0]][part1[1]]
                    + matrix_arr[part2[0]][part2[1]];
        }
        return Original;
    }
}