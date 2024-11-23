import java.awt.event.*;
import javax.swing.*;

public class SerpEscControlador implements ActionListener {
	
	private SerpEscVista Vista;
	private SerpEscModelo Modelo;
	
	public SerpEscControlador(SerpEscVista Vista, SerpEscModelo Modelo) {
		this.Vista = Vista;
		this.Modelo = Modelo;
		Modelo.CreaEsc(Vista.getTablero());
		Modelo.CreaSerp(Vista.getTablero());
		Vista.AcomodaTablero();
//		Vista.setCasilla();
	}
	
	public void actionPerformed(ActionEvent Evt) {
		JButton BtnAux = (JButton) Evt.getSource();
		if(BtnAux == Vista.getBtnDado()) {
			int Recorrido = Modelo.Tirada(Vista.getTablero(), Vista.getVectorJugadores());
			Vista.ActualizaTablero(Modelo.getDado(), Modelo.getJugadorTurno(), Recorrido);
			Modelo.setJugadorTurno(Modelo.getJugadorTurno() + 1);
			if(Modelo.getJugadorTurno() > Vista.getNoJugadores())
				Modelo.setJugadorTurno(1);
		}
	}
}
