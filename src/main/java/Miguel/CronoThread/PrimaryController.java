package Miguel.CronoThread;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Miguel.CronoThread.Model.Cronometro;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class PrimaryController implements Initializable {
	private Cronometro crono;
	private Thread thread;
	private ArrayList<String> times;
	
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
	@FXML
	private TableView<List<String>> tableTimes;
	@FXML
	private TableColumn<List<String>, String> cellTimes;

	
	
	@FXML
	private void startCrono() {
		times = new ArrayList<String>();
		crono = new Cronometro();
		thread = new Thread(crono);
		thread.start();
		start.setDisable(true);
		stop.setDisable(false);
		restart.setDisable(false);
		seconds.textProperty().bind(crono.getSegundos().asString());
		minutes.textProperty().bind(crono.getMinutos().asString());
		hours.textProperty().bind(crono.getHoras().asString());
	}
	@FXML
	private void stopCrono() {
		if (crono.isSuspendido()) {
			crono.setSuspendido(false);
			stop.setText("Parar");

		} else {
			updateTable(crono.toString());
			crono.setSuspendido(true);
			stop.setText("Reanude");	
		}
	}
	
	@FXML
	private void restartCrono() {
		tableTimes.getItems().clear();
		times.clear();
		crono.setSuspendido(true);
		thread.interrupt();
		start.setDisable(false);
		stop.setDisable(true);
		restart.setDisable(true);
		seconds.textProperty().unbind();
		minutes.textProperty().unbind();
		hours.textProperty().unbind();
		seconds.setText("0");
		minutes.setText("0");
		hours.setText("0");
		
	}
	
	public void updateTable(String time) {
		tableTimes.getItems().clear();
		times.add(time);
		ObservableList<String> oList = FXCollections.observableArrayList(times);
		cellTimes.setCellValueFactory(Time -> {
			SimpleStringProperty a = new SimpleStringProperty();
			a.setValue(Time.getValue().toString());
			return a;
		});
		tableTimes.getItems().addAll(oList);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		stop.setDisable(true);
		restart.setDisable(true);
		seconds.setText("0");
		minutes.setText("0");
		hours.setText("0");
		
	}
	
	
}