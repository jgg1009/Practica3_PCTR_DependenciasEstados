package src.p03.c01;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Clase que implenta los metodos relcionados con el parque.
 * 
 * @author Juan Luis G.G && Alejandro Ortega Martinez
 *
 */
public class Parque implements IParque{
	/**
	 * Variable que guarda el numero maximo de personas en el parque.
	 */
	private int aforo;
	/**
	 * Variable que refleja el numero de personas en el parque.
	 */
	private int contadorPersonasTotales;
	/**
	 * Hashtable para almacenar las entradas y salidas por las puertas.
	 */
	private Hashtable<String, Integer> contadoresPersonasPuerta;
	
	/**
	 * Constructor.
	 * 
	 * @param aforo El numero de personas maximas.
	 */
	public Parque(int aforo) {	
		contadorPersonasTotales = 0;
		contadoresPersonasPuerta = new Hashtable<String, Integer>();
		this.aforo=aforo;
	}

	@Override
	/**
	 * Funcion que refleja la entrada de una persona en el parque.
	 * 
	 *@param puerta puerta por la que se entra 
	 */
	public synchronized void entrarAlParque(String puerta){		
		
		// Si no hay entradas por esa puerta, inicializamos
		if (contadoresPersonasPuerta.get(puerta) == null){
			contadoresPersonasPuerta.put(puerta, 0);
		}
		
		comprobarAntesDeEntrar();
				
		// Aumentamos el contador total y el individual
		contadorPersonasTotales++;		
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta)+1);
		
		// Imprimimos el estado del parque
		sumarContadoresPuerta();
		imprimirInfo(puerta, "Entrada");
		
		//Notificamos la entrada y comprobamos los invariantes
		this.notifyAll();
		
		checkInvariante();
		
	}
	
	@Override
	/**
	 * Funcion que refleja la salida de una persona en el parque.
	 * 
	 *@param puerta puerta por la que se sale. 
	 */
	public synchronized void salirDelParque(String puerta) { 	// TODO
		
		if (contadoresPersonasPuerta.get(puerta) == null){
			contadoresPersonasPuerta.put(puerta, 0);
		}
		comprobarAntesDeSalir();
		
		contadorPersonasTotales--;		
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta)-1);
		
		// Imprimimos el estado del parque
		
		sumarContadoresPuerta();
		imprimirInfo(puerta, "Salida");
		
		
		//Notificamos la entrada y comprobamos los invariantes
		this.notifyAll();
		checkInvariante();
		
	}
	
	/**
	 * Imprime el evento y el estado final del parque.
	 * 
	 * @param puerta por que puerta se ha producido el evento.
	 * @param movimiento si ha sido de salida o entrada.
	 */
	private void imprimirInfo (String puerta, String movimiento){
		System.out.println(movimiento + " por puerta " + puerta);
		System.out.println("--> Personas en el parque " + contadorPersonasTotales); //+ " tiempo medio de estancia: "  + tmedio);
		
		// Iteramos por todas las puertas e imprimimos sus entradas
		for(String p: contadoresPersonasPuerta.keySet()){
			System.out.println("----> Por puerta " + p + " " + contadoresPersonasPuerta.get(p));
		}
		System.out.println(" ");
	}
	/**
	 * Funcion que se encarga de sumar el numero de persoans que estan en el parque en funcion de los eventos.
	 * cometidos en las puertas.
	 * 
	 * @return el balance de personas respecto a las puertas
	 */
	private int sumarContadoresPuerta() {
		int sumaContadoresPuerta = 0;
			Enumeration<Integer> iterPuertas = contadoresPersonasPuerta.elements();
			while (iterPuertas.hasMoreElements()) {
				sumaContadoresPuerta += iterPuertas.nextElement();
			}
		return sumaContadoresPuerta;
	}
	/**
	 * Comprueba los invariantes.
	 */
	protected  void checkInvariante() {
		assert sumarContadoresPuerta() == contadorPersonasTotales : "INV: La suma de contadores de las puertas debe ser igual al valor del contador del parte";
		assert contadorPersonasTotales <= aforo : "Se ha superado el aforo"; 
		assert contadorPersonasTotales >= 0 :"Descordinacion en salidas";
	}
	/**
	 * Comprueba que no se supere el aforo al entrar al parque.
	 */
	protected synchronized void comprobarAntesDeEntrar() {	
		while (contadorPersonasTotales==aforo) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	}
	/**
	 * Comprueba que haya personas en el parque.
	 */
	protected synchronized void comprobarAntesDeSalir(){
		while (contadorPersonasTotales==0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	}


	


}
