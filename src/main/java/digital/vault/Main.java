package digital.vault;

import digital.vault.model.Category;
import digital.vault.model.User;
import digital.vault.model.VaultItem;
import digital.vault.model.vault.items.WebCredential;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main()
    {
        User user=new User("gigel","gigelPopescu","123");
        System.out.println(user);

        VaultItem v = new WebCredential("Facebook login", Category.SOCIAL,"fernando","facebook.com","fernando","123");
        v.displayItem();

    }
}
