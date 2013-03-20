package interfaces;

import javax.swing.JTextField;

import GUI.GUI;
import GUI.Login;
import GUI.components.LoginButton;
import GUI.components.LogoutButton;
import GUI.components.PasswordField;
import GUI.components.BuyerType;
import GUI.components.SellerType;
import GUI.components.UsernameField;

/**
 * @author Stedy
 *
 */
public interface IGUIMediator {
	public void registerNetwork(INetwork network);
	public void registerWSClient(IWSClient client);
}
