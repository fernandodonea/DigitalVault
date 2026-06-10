package digital.vault.ui.handler;

public class HelpHandler implements CommandHandler
{
    @Override
    public void handle(String[] args) {
        System.out.println("Digital vault commands:");
        System.out.println("register | register <username> <email> <password> | create a new user");
        System.out.println("login | login <username> <password> | log in as an existing user");
        System.out.println("logout| logout | log out");

        System.out.println();
        System.out.println();

        System.out.println("ls | ls | list all vault items ");
        System.out.println("touch | touch <vault-item-type> | create a new vault item ");
        System.out.println("rm | rm <item-id> | delete an item with set id");
        System.out.println("cat | cat <item-id> | display an item with set it" );
        System.out.println("nano | nano <item-id> | edit an item with set it");

        System.out.println();
        System.out.println();

        System.out.println("help | help  | show this menu");
        System.out.println("quit | quit  | exit the application");
    }
}
