import java.awt.*;
import javax.swing.*;

public class SerpEscVista extends JFrame {
	
	private JButton BtnDado;
	private JLabel DadoGrafico;
	private JPanel PanelJugadores, PanelCasillas, PanelBoton;
	private ListaDBL<JButtonCasilla> LJBC;
	private Icon UltImagen;
	private JButtonCasilla UltCasilla;
	private int NoJugadores;
	private JLabel [] VJugadores;
	
	public SerpEscVista() {
		super("Serpientes y Escaleras");
		NoJugadores = Rutinas.nextInt(2,  8);
		// NoJugadores = 2;
		VJugadores = new JLabel [NoJugadores];
		LJBC = new ListaDBL<JButtonCasilla>();
		CreaCasillas();
		HazInterfaz();
	}
	
	public void setCasilla() {
		VJugadores[0].setText(94+"");
	}
	
	public void HazInterfaz() {
		PanelBoton = new JPanel();
		BtnDado = new JButton("Tirada");
		PanelBoton.add(BtnDado);
		DadoGrafico = new JLabel("0");
		PanelBoton.add(DadoGrafico);
		add(PanelBoton, BorderLayout.NORTH);
		
		PanelJugadores = new JPanel();
		PanelJugadores.setLayout(new GridLayout(0, 1));
		for(int i = 0 ; i < VJugadores.length ; i++) {
			VJugadores[i] = new JLabel(0+"");
			PanelJugadores.add(new JLabel("Jugador "+(i+1)));
			PanelJugadores.add(VJugadores[i]);
		}
		add(PanelJugadores, BorderLayout.WEST);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	public void AcomodaTablero() {
		PanelCasillas = new JPanel();
		PanelCasillas.setLayout(new GridLayout(0, 10));
		JButtonCasilla [] VC = new JButtonCasilla [LJBC.Length()];
		VC = OrdenFila(VC);
		for(int i = 0 ; i < VC.length ; i++) {
			if(VC[i].getTipoCasilla() == 'E')
				VC[i].setIcon(Rutinas.AjustarImagen("img/Esc.png", 40, 40));
			else if(VC[i].getTipoCasilla() == 'S')
				VC[i].setIcon(Rutinas.AjustarImagen("img/Serp.png", 40, 40));
			else
				VC[i].setIcon(Rutinas.AjustarImagen("img/N.png", 40, 40));
			PanelCasillas.add(VC[i]);
			System.out.println(VC[i].getNoCasilla()+"------"+VC[i].getPosiciones());
		}
		add(PanelCasillas, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(null);
	}
	
	public JButtonCasilla[] OrdenFila(JButtonCasilla [] VC) {
		NodoDBL<JButtonCasilla> Aux = LJBC.getFin();
		int Contador = 0;
		for(int i = 0 ; i < VC.length ; i++) {
			VC[i] = Aux.Info;
			Contador++;
			Aux = Aux.getAnt();
			if(Contador == 10) {
				int Limite = i;
				for(int k = (i+10) ; k > Limite ; k--) {
					VC[k] = Aux.Info;
					Aux = Aux.getAnt();
					i++;
				}
				Contador = 0;
			}
		}
		return VC;
	}
	
	private void CreaCasillas() {
		JButtonCasilla BtnCasilla;
		for(int i = 0 ; i < 100; i++) {
			BtnCasilla = new JButtonCasilla(i+1);
			LJBC.InsertaFin(BtnCasilla);
		}
	}
	
	public void ActualizaTablero(int Dado, int JugadorTurno, int CasillaIntermedia) {
		DadoGrafico.setText("Al jugador "+JugadorTurno+" le salió "+Dado);
		PanelBoton.update(PanelBoton.getGraphics());
		NodoDBL<JButtonCasilla> Aux = LJBC.getFrente();
		int CasillaInicial = Integer.parseInt(VJugadores[JugadorTurno - 1].getText());
		if(UltCasilla != null) {
			UltCasilla.setIcon(UltImagen);
			Aux.Info.update(Aux.Info.getGraphics());
		}
		int DadoReal = Dado;
		while(Aux != null) {
			if(CasillaInicial == 0) {
				CasillaInicial = 1;
				DadoReal--;
				break;
			}
			if(Aux.Info.getNoCasilla() == CasillaInicial)
				break;
			Aux = Aux.getSig();
		}
		
		Icon Imagen = Aux.Info.getIcon();
		Aux.Info.setIcon(Rutinas.AjustarImagen("img/players/Jugador"+JugadorTurno+"Der.png", 40, 40));
		Aux.Info.update(Aux.Info.getGraphics());
		
		try {Thread.sleep(250);}catch(InterruptedException IE) {}
		
		int CasillasRetroceder = DadoReal;
		for(int i = 0 ; i < DadoReal ; i++) {
			Aux.Info.setIcon(Imagen);
			Aux.Info.update(Aux.Info.getGraphics());
			
			try {Thread.sleep(250);}catch(InterruptedException IE) {}
			
			Aux = Aux.getSig();
			if(Aux == null)
				break;
			Imagen = Aux.Info.getIcon();
			Aux.Info.setIcon(Rutinas.AjustarImagen("img/players/P"+JugadorTurno+"Der.png", 40, 40));
			Aux.Info.update(Aux.Info.getGraphics());
			CasillasRetroceder--;
		}
		if(CasillasRetroceder > 0) {
			Aux = LJBC.getFin();
			for(int k = 0 ; k < CasillasRetroceder ; k++) {
				Aux.Info.setIcon(Imagen);
				Aux.Info.update(Aux.Info.getGraphics());
				
				try {Thread.sleep(250);}catch(InterruptedException IE) {}
				
				Aux = Aux.getAnt();
				Imagen = Aux.Info.getIcon();
				Aux.Info.setIcon(Rutinas.AjustarImagen("img/players/P"+JugadorTurno+"Der.png", 40, 40));
				Aux.Info.update(Aux.Info.getGraphics());
			}
		}
		if(Aux.Info.getTipoCasilla() == 'E') {
			int PosicionesAvanzar = Aux.Info.getPosiciones();
			for(int k = 0 ; k < PosicionesAvanzar ; k++) {
				Aux.Info.setIcon(Imagen);
				Aux.Info.update(Aux.Info.getGraphics());
				
				try {Thread.sleep(250);}catch(InterruptedException IE) {}
				
				Aux = Aux.getSig();
				Imagen = Aux.Info.getIcon();
				Aux.Info.setIcon(Rutinas.AjustarImagen("img/players/P"+JugadorTurno+"Der.png", 40, 40));
				Aux.Info.update(Aux.Info.getGraphics());
			}
		}
		if(Aux.Info.getTipoCasilla() == 'S') {
			int PosicionesRetroceder = Aux.Info.getPosiciones();
			for(int k = 0 ; k < PosicionesRetroceder ; k++) {
				Aux.Info.setIcon(Imagen);
				Aux.Info.update(Aux.Info.getGraphics());
				
				try {Thread.sleep(250);}catch(InterruptedException IE) {}
				
				Aux = Aux.getAnt();
				Imagen = Aux.Info.getIcon();
				Aux.Info.setIcon(Rutinas.AjustarImagen("img/players/P"+JugadorTurno+"Der.png", 40, 40));
				Aux.Info.update(Aux.Info.getGraphics());
			}
		}
		VJugadores[JugadorTurno - 1].setText(Aux.Info.getNoCasilla()+"");
		if(Aux.Info.getNoCasilla() == 100) {
			JDialog MnsjWin = new JDialog();
			MnsjWin.setLayout(new BorderLayout());
			MnsjWin.setSize(250, 250);
			MnsjWin.add(new JLabel("El ganador es el jugador "+JugadorTurno, JLabel.CENTER));
			MnsjWin.setLocationRelativeTo(null);
			MnsjWin.setResizable(false);
			MnsjWin.setModal(true);
			MnsjWin.setVisible(true);
		}
		UltCasilla = Aux.Info;
		UltImagen = Imagen;
	}

	public int getNoJugadores() {
		return NoJugadores;
	}
	
	public JLabel[] getVectorJugadores() {
		return VJugadores;
	}
	
	public JButton getBtnDado() {
		return BtnDado;
	}
	
	public ListaDBL<JButtonCasilla> getTablero() {
		return LJBC;
	}
	
	public void setControlador(SerpEscControlador C){
		BtnDado.addActionListener(C);
	}
	
	public void Muestra() {
		setVisible(true);
	}
}
