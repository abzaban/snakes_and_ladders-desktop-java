
public class AplSerpEsc {
	
	public AplSerpEsc() {
		SerpEscVista Vista = new SerpEscVista();
		SerpEscModelo Modelo = new SerpEscModelo();
		SerpEscControlador Controlador = new SerpEscControlador(Vista, Modelo);
		Vista.setControlador(Controlador);
		Vista.Muestra();
	}
	
	public static void main(String [] a) {
		new AplSerpEsc();
	}
}
