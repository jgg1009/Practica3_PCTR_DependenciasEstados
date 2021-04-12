package src.p03.c01;

import java.util.Random;

public class ActividadSalidaPuerta implements Runnable{

	//
	// TODO
	//
	private static final int NUMENTRADAS = 20;
	private String puerta;
	private IParque parque;
	
	public ActividadSalidaPuerta(String puerta, IParque parque) {
		this.puerta = puerta;
		this.parque = parque;
	}

	@Override
	public void run() {
		for (int i = 0; i<NUMENTRADAS; i++) {
			parque.salirDelParque(puerta);
			
			try {
				
				Thread.sleep(new Random().nextInt(5) * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}
