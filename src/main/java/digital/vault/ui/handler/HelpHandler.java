package digital.vault.ui.handler;

public class HelpHandler implements CommandHandler
{
    @Override
    public void handle(String[] args) {
        System.out.println("Digital vault commands:");
        System.out.println("register | register <username> <email> <password> ");
        System.out.println("login | login <username> <password>");
        System.out.println("logout| logout");

        System.out.println();
        System.out.println();

        System.out.println("ls | ls (list all vault items)");
        System.out.println("touch | touch <vault-item-type> ");
        System.out.println("    - touch credential ( add a web credential)");
        System.out.println("    - touch note (add a secure note)");
        System.out.println("    - touch card (add payment card)");
        System.out.println("rm | rm <item-id>");

        System.out.println();
        System.out.println();

        System.out.println("help | help (show this menu");
        System.out.println("quit | quit (exit the application");
    }
}
