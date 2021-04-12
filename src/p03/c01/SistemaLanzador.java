package src.p03.c01;

public class SistemaLanzador {
	public static void main(String[] args) {
		final int AFORO = 50;
		IParque parque = new Parque(AFORO); // TODO
		char letra_puerta = 'A';
		
		System.out.println("¡Parque abierto!");
		
		for (int i = 0; i < 5; i++) {
			
			String puerta = ""+((char) (letra_puerta++));
			
			// Creacion de hilos de entrada
			ActividadEntradaPuerta entradas = new ActividadEntradaPuerta(puerta, parque);
			ActividadSalidaPuerta salidas = new ActividadSalidaPuerta(puerta, parque);
			new Thread (entradas).start();
			new Thread (salidas).start();
			
			// 
			// TODO
			//
			
			
		}
	}	
}