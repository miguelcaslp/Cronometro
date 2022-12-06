package Miguel.CronoThread.Model;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableIntegerValue;

public class Cronometro implements Runnable {

	private IntegerProperty segundos;
	private IntegerProperty minutos;
	private IntegerProperty horas;
	private boolean suspendido;

	public Cronometro() {
		super();
		this.segundos = new SimpleIntegerProperty(0);
		this.minutos = new SimpleIntegerProperty(0);
		this.horas = new SimpleIntegerProperty(0);
		this.suspendido = false;
	}

	public boolean isSuspendido() {
		return suspendido;
	}

	public synchronized void setSuspendido(boolean suspendido) {
		this.suspendido = suspendido;
		notifyAll();
	}

	public IntegerProperty getSegundos() {
		return segundos;
	}

	public void setSegundos(int segundos) {
		this.segundos.set(segundos);
	}

	public IntegerProperty getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos.set(minutos);
	}

	public IntegerProperty getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas.set(horas);
	}

	public synchronized void suspender() throws InterruptedException {
		while (this.suspendido) {
			wait();
		}
	}

	@Override
	public String toString() {
		return horas.intValue() + ":" + minutos.intValue() + ":" + segundos.intValue();
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				for (int second = 0, minute = 0, hour = 0; true; second++) {
					Thread.sleep(1000);
					suspender();
					if (second == 60) {
						second = 00;
						minute++;
					}
					if (minute == 60) {
						minute = 00;
						hour++;
					}
					int s1 = second;
					int m1 = minute;
					int h1 = hour;
					Platform.runLater(() -> {
						segundos.set(s1);
					});
					Platform.runLater(() -> {
						minutos.set(m1);
					});
					Platform.runLater(() -> {
						horas.set(h1);
					});

				}

			}
		} catch (Exception e) {
		}
	}
}
