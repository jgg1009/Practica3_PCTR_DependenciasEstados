package src.p03.c01;

import java.util.Random;

/**
 * Clase que implenta los metodos relcionados con la actividad de salida de persoans del parque..
 * 
 * @author Juan Luis G.G && Alejandro Ortega Martinez
 *
 */

public class ActividadSalidaPuerta implements Runnable{

	/**
	 * El numero de salidas maximas por la puerta.
	 */
	private static final int NUMSALIDAS = 20;
	/**
	 * Almacena el identificador de la puerta que representa
	 */
	private String puerta;
	/**
	 * El parque con el que se va a interactuar.
	 */
	private IParque parque;
	
	/**
	 * Constructor.
	 * 
	 * @param puerta la puerta que representa.
	 * @param parque el parque con el que interactuar.
	 */
	public ActividadSalidaPuerta(String puerta, IParque parque) {
		this.puerta = puerta;
		this.parque = parque;
	}

	@Override
	public void run() {
		for (int i = 0; i<NUMSALIDAS; i++) {
			parque.salirDelParque(puerta);
			
			try {
				
				Thread.sleep(new Random().nextInt(5) * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}
