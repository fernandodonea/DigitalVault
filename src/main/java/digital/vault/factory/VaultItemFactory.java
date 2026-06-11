package digital.vault.factory;

import digital.vault.model.Category;
import digital.vault.model.vault.*;
import digital.vault.ui.InputReader;

public class VaultItemFactory
{
    private final InputReader reader;

    public VaultItemFactory(InputReader reader) {
        this.reader = reader;
    }

    public VaultItem create(String type)
    {
        String title=reader.readLine("Enter title");
        Category category=reader.readCategory();

        return switch (type) {
            case "note" -> createNote(title, category);
            case "web" -> createWebCredential(title, category);
            case "card" -> createCard(title, category);
            default -> null;
        };
    }

    private SecureNote createNote(String title, Category category)
    {
        String content=reader.readLine("Enter secure note content: ");
        return new SecureNote(title, category, "",content);
    }

    private Card createCard(String title, Category category)
    {
        String cardNumber=reader.readLine("Enter card number: ");
        String cardHolderName=reader.readLine("Enter cald horder name: ");
        int cvv=reader.readInt("Enter cvv: ");
        return new Card(title, category, "", cardNumber, cardHolderName, cvv);
    }

    private WebCredential createWebCredential(String title, Category category)
    {
        String url=reader.readLine("Enter url: ");
        String user=reader.readLine("Enter username/email: ");
        String password=reader.readLine("Enter password: ");
        return new WebCredential(title, category,"", url, user, password);
    }
}
