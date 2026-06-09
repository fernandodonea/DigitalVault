package digital.vault.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserSession
{
    private final String secretToken;
    private final String username;
    private final LocalDateTime expiresAt;

    public UserSession(String username, int hoursValid)
    {
        this.secretToken = UUID.randomUUID().toString(); //generam un token UNIC intr-un mod aleator
        this.username = username;
        this.expiresAt = LocalDateTime.now().plusHours(hoursValid);
    }

    public String getSecretToken() {return secretToken;}
    public String getUsername() {return username;}
    public boolean isExpired(){return LocalDateTime.now().isAfter(expiresAt);}

}
