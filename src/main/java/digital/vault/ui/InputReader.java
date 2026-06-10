package digital.vault.ui;

import digital.vault.model.Category;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputReader
{
    private final Scanner scanner=new Scanner(System.in);

    public String readLine(String prompt)
    {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public int readInt(String prompt)
    {
        System.out.println(prompt);
        try{
            return Integer.parseInt(scanner.nextLine().trim());
        }catch (NumberFormatException e)
        {
            System.out.println("Invalid number. Defaulting to 0");
            return 0;
        }
    }

    public Category readCategory()
    {
        String input=readLine
                ("Enter category: (SOCIAL, BANKING, WORK, EMAIL, SHOPPING, ENTERTAINMENT, SECURITY, OTHER").toUpperCase();
        try{
            return Category.valueOf(input);
        }catch (IllegalArgumentException e)
        {
            System.out.println("Invalid category. Defaulting to other");
            return Category.OTHER;
        }
    }


}
