package digital.vault.ui.handler;

import digital.vault.exception.ServiceException;
import digital.vault.model.vault.Card;
import digital.vault.model.vault.SecureNote;
import digital.vault.model.vault.VaultItem;
import digital.vault.model.vault.WebCredential;
import digital.vault.service.AuditService;
import digital.vault.service.AuthService;
import digital.vault.service.VaultService;
import digital.vault.ui.InputReader;

public class RevealHandler implements CommandHandler {
    private final VaultService vaultService;
    private final AuthService authService;
    private final SessionHolder session;
    private final InputReader reader;

    public RevealHandler(VaultService vaultService, AuthService authService,
                         SessionHolder session, InputReader reader) {
        this.vaultService = vaultService;
        this.authService = authService;
        this.session = session;
        this.reader = reader;
    }

    @Override
    public void handle(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: reveal <item-id>");
            return;
        }
        if (!session.isLoggedIn()) {
            System.out.println("You must be logged in.");
            return;
        }

        // Reconfirmare master password
        String masterPassword = reader.readLine("Enter master password to reveal: ");
        String username = authService.getUsernameFromToken(session.getToken());

        if (!authService.verifyPassword(username, masterPassword)) {
            System.out.println("[Access denied] Wrong master password.");
            return;
        }

        try {
            VaultItem item = vaultService.getVaultItemById(args[1], session.getToken());
            printRevealed(item);
        } catch (ServiceException e) {
            System.out.println("[Cannot reveal item]: " + e.getMessage());
        }
    }

    private void printRevealed(VaultItem item) {
        System.out.println("--------------------------------------");
        if (item instanceof WebCredential web) {
            System.out.println("  Type     : Web Credential");
            System.out.println("  URL      : " + web.getUrl());
            System.out.println("  Username : " + web.getUsername());
            System.out.println("  Password : " + web.getPassword());  // în clar
        } else if (item instanceof Card card) {
            System.out.println("  Type     : Payment Card");
            System.out.println("  Number   : " + card.getCardNumber());  // în clar
            System.out.println("  Holder   : " + card.getCardHolderName());
            System.out.println("  CVV      : " + card.getCvv());  // în clar
        } else if (item instanceof SecureNote note) {
            System.out.println("  Type     : Secure Note");
            System.out.println("  Content  : " + note.getContent());
        }
        System.out.println("--------------------------------------");
        AuditService.getInstance().log("reveal-item");
    }
}
