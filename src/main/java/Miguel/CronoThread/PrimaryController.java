package Miguel.CronoThread;

import Miguel.CronoThread.Model.Cronometro;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;

public class PrimaryController {

	@FXML
	private Label seconds;
	@FXML
	private Label minutes;
	@FXML
	private Label hours;
	@FXML
	private Button start;
	@FXML
	private Button stop;
	@FXML
	private Button restart;

	private Cronometro crono;
	private Thread thread;
	
	@FXML
	private void startCrono() {
		crono = new Cronometro();
		thread = new Thread(crono);
		thread.start();
		//crono.setSuspendido(false);
		start.setDisable(true);
		seconds.textProperty().bind(crono.getSegundos().asString());
		minutes.textProperty().bind(crono.getMinutos().asString());
		hours.textProperty().bind(crono.getHoras().asString());
	}
	@FXML
	private void hola() {
		System.out.println("hola");
		
	}
	@FXML
	private void stopCrono() {
		if (crono.isSuspendido()) {
			crono.setSuspendido(false);
			stop.setText("Parar");

		} else {
			crono.setSuspendido(true);
			stop.setText("Reanudar");	
		}
	}
	
	@FXML
	private void restartCrono() {
		crono.setSuspendido(true);
		thread.interrupt();
		start.setDisable(false);
		seconds.textProperty().unbind();
		minutes.textProperty().unbind();
		hours.textProperty().unbind();
		seconds.setText("0");
		minutes.setText("0");
		hours.setText("0");
		
	}
	
	
	
	
}