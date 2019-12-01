package dad.javafx.iniciosesion;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.digest.DigestUtils;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class InicoSesionController {

	private final String usersFile = "users.csv";

	private InicioSesionModel model;
	private InicioSesionView view;

	public InicoSesionController() {

		model = new InicioSesionModel();
		view = new InicioSesionView();

		model.userProperty().bindBidirectional(view.getUserTxt().textProperty());
		model.passwordProperty().bindBidirectional(view.getPasswordFd().textProperty());

		view.getLoginBt().setOnAction(evt -> onLoginBtAction(evt));
		view.getCancelBt().setOnAction(evt -> Platform.exit());
	}

	private void onLoginBtAction(ActionEvent evt) {

		String user = model.getUser();
		String password = model.getPassword();

		if (user.isEmpty() || password.isEmpty() || !isUserValid(user, password)) {
			launchLoginError();
			model.setPassword("");
		}

		else {

			launchLoginOk();
			Platform.exit();
		}
	}

	private boolean isUserValid(String userName, String password) {

		FileInputStream file = null;
		InputStreamReader in = null;
		BufferedReader buff = null;

		try {

			file = new FileInputStream(usersFile);
			in = new InputStreamReader(file, StandardCharsets.UTF_8);
			buff = new BufferedReader(in);

			String line;
			while ((line = buff.readLine()) != null) {

				String[] data = line.split(",");
				if (userName.equals(data[0]) && transformar(password).equals(data[1])) {
					return true;
				}
			}

		} catch (IOException e) {
		} finally {

			try {
				if (buff != null) {
					buff.close();
				}

				if (in != null) {
					in.close();
				}

				if (file != null) {
					file.close();
				}
			} catch (IOException e) {
			}
		}

		return false;
	}

	private String transformar(String text) {
		return DigestUtils.md5Hex(text).toUpperCase();
	}

	private void launchLoginOk() {

		Alert goodAlert = new Alert(AlertType.INFORMATION);
		goodAlert.setHeaderText("Acceso permitido");
		goodAlert.setContentText("Las credenciales de acceso son correctas");
		goodAlert.showAndWait();
	}

	private void launchLoginError() {

		Alert badAlert = new Alert(AlertType.ERROR);
		badAlert.setHeaderText("Acceso denegado");
		badAlert.setContentText("El usuario y/o la contraseña no son válidos");
		badAlert.showAndWait();
	}

	public InicioSesionView getRootView() {
		return view;
	}
}
