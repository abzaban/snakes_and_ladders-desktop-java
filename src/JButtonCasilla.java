import javax.swing.*;

public class JButtonCasilla extends JButton {
	
	private int NoCasilla;
	private char TipoCasilla;
	private int Posiciones;
	
	public JButtonCasilla(int NoCasilla) {
		this.NoCasilla = NoCasilla;
		this.setText(NoCasilla+"");
		TipoCasilla = 'N';
		Posiciones = 0;
	}
	
	public int getNoCasilla() {
		return NoCasilla;
	}
	
	public void setTipoCasilla(char TipoCasilla) {
		this.TipoCasilla = TipoCasilla;
	}
	
	public char getTipoCasilla() {
		return TipoCasilla;
	}
	
	public void setPosiciones(int Posiciones) {
		this.Posiciones = Posiciones;
	}
	
	public int getPosiciones() {
		return Posiciones;
	}
	
	public String toString() {
		return Rutinas.PonCeros(NoCasilla, 3);
	}
}
