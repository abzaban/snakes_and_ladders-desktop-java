import javax.swing.*;

public class SerpEscModelo {
	
	private int Dado;
	private int JugadorTurno;
	
	public SerpEscModelo() {
		JugadorTurno = 1;
	}
	
	public void CreaEsc(ListaDBL<JButtonCasilla> LJBC) {
		int NvoNodoE, Posicion = 0, Contador = 0;
		while(Contador < 5) {
			NvoNodoE = Rutinas.nextInt(15, 70);
			NodoDBL<JButtonCasilla> Aux = LJBC.getFrente();
			while(Aux != null) {
				if(Aux.Info.getNoCasilla() == NvoNodoE)
					break;
				Aux = Aux.getSig();
			}
			if(Aux.Info.getTipoCasilla() != 'N')
				continue;
			Posicion = Rutinas.nextInt(5, 20);
			while(!NodoValidoEsc(Posicion, Aux))
				Posicion = Rutinas.nextInt(5, 20);
			Aux.Info.setTipoCasilla('E');
			Aux.Info.setPosiciones(Posicion);
			Contador++;
		}
	}
	
	public void CreaSerp(ListaDBL<JButtonCasilla> LJBC) {
		int NvoNodoE, Posicion = 0, Contador = 0;
		while(Contador < 5) {
			NvoNodoE = Rutinas.nextInt(30, 95);
			NodoDBL<JButtonCasilla> Aux = LJBC.getFrente();
			while(Aux != null) {
				if(Aux.Info.getNoCasilla() == NvoNodoE)
					break;
				Aux = Aux.getSig();
			}
			if(Aux.Info.getTipoCasilla() != 'N')
				continue;
			Posicion = Rutinas.nextInt(5, 20);
			while(!NodoValidoSerp(Posicion, Aux))
				Posicion = Rutinas.nextInt(5, 20);
			Aux.Info.setTipoCasilla('S');
			Aux.Info.setPosiciones(Posicion);
			Contador++;
		}
	}
	
	private boolean NodoValidoEsc(int Posicion, NodoDBL<JButtonCasilla> Aux) {
		for(int i = 0 ; i < Posicion ; Aux = Aux.getSig(), i++);
		if(Aux.Info.getTipoCasilla() != 'N')
			return false;
		Aux.Info.setTipoCasilla('T');
		return true;
	}
	
	private boolean NodoValidoSerp(int Posicion, NodoDBL<JButtonCasilla> Aux) {
		for(int i = 0 ; i < Posicion ; Aux = Aux.getAnt(), i++);
		if(Aux.Info.getTipoCasilla() != 'N')
			return false;
		Aux.Info.setTipoCasilla('T');
		return true;
	}
	
	public int Tirada(ListaDBL<JButtonCasilla> LJBC, JLabel [] VJugadores) {
		Dado = Rutinas.nextInt(1, 6);
		int CasillaActual = Integer.parseInt(VJugadores[JugadorTurno - 1].getText());
		CasillaActual += Dado;
		if(CasillaActual > 100)
			CasillaActual = 100 - (CasillaActual - 100);
		return CasillaActual;
	}
	
	public void setJugadorTurno(int JugadorTurno) {
		this.JugadorTurno = JugadorTurno;
	}
	
	public int getJugadorTurno() {
		return JugadorTurno;
	}
	
	public int getDado() {
		return Dado;
	}
}
