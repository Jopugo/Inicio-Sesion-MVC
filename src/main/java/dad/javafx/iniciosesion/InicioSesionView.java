package dad.javafx.iniciosesion;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InicioSesionView extends VBox {

	private Label userLbl, passwordLbl;
	private TextField userTxt;
	private PasswordField passwordFd;
	private Button loginBt, cancelBt;
	
	public InicioSesionView() {
	
		userLbl = new Label("Usuario:");
		userLbl.setPrefWidth(80);
		
		passwordLbl = new Label("Contraseña:");
		passwordLbl.setPrefWidth(80);
		
		userTxt = new TextField();
		userTxt.setPromptText("Usuario");
		userTxt.setPrefColumnCount(10);
		
		passwordFd = new PasswordField();
		passwordFd.setPromptText("Contraseña");
		passwordFd.setPrefColumnCount(10);
		
		loginBt = new Button("Acceder");
		loginBt.setDefaultButton(true);
		
		cancelBt = new Button("Cancelar");
		
		HBox userBox = new HBox(20, userLbl, userTxt);
		userBox.setFillHeight(false);
		userLbl.setAlignment(Pos.BASELINE_LEFT);
		
		HBox passBox = new HBox(20, passwordLbl, passwordFd);
		passBox.setFillHeight(false);
		passBox.setAlignment(Pos.BASELINE_LEFT);
		
		HBox btBox = new HBox(5, loginBt, cancelBt);
		btBox.setFillHeight(false);
		btBox.setAlignment(Pos.BASELINE_CENTER);
		
		getChildren().addAll(userBox, passBox, btBox);
		setAlignment(Pos.CENTER);
		setSpacing(10);
		setFillWidth(false);
	}

	public TextField getUserTxt() {
		return userTxt;
	}

	public PasswordField getPasswordFd() {
		return passwordFd;
	}

	public Button getLoginBt() {
		return loginBt;
	}

	public Button getCancelBt() {
		return cancelBt;
	}
}
