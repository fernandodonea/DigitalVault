package digital.vault;


import digital.vault.dao.UserDao;
import digital.vault.dao.VaultDao;
import digital.vault.dao.impl.UserDaoDB;
import digital.vault.dao.impl.UserDaoInMemory;
import digital.vault.dao.impl.VaultDaoDB;
import digital.vault.dao.impl.VaultDaoInMemory;
import digital.vault.service.AuthService;
import digital.vault.service.VaultService;
import digital.vault.ui.TerminalService;


public class Main {
    public static void main()
    {
        UserDao userDao=new UserDaoDB();
        VaultDao vaultDao=new VaultDaoDB();




        AuthService authService=new AuthService(userDao);
        VaultService vaultService=new VaultService(vaultDao, authService);


        TerminalService terminal = new TerminalService(vaultService, authService);
        terminal.start();


    }
}
