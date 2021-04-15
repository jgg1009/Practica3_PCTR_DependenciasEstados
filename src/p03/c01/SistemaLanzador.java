package src.p03.c01;

public class SistemaLanzador {
	public static void main(String[] args) {
		final int AFORO = 50;
		IParque parque = new Parque(AFORO); // TODO
		char letra_puerta = 'A';
		int nPuertas;
		
		//Si hemos pasado por argumento el numero de puertas, lo leemos
		System.out.println("¡Parque abierto!");
		if (args.length != 0) {
			nPuertas = Integer.parseInt(args[0]);
		} else {
			nPuertas=5;
		}
		for (int i = 0; i < nPuertas; i++) {
			//Creamos el nombre de la puerta
			String puerta = ""+((char) (letra_puerta++));
			
			// Creacion de hilos de entrada
			ActividadEntradaPuerta entradas = new ActividadEntradaPuerta(puerta, parque);
			
			//Creacion de hilos de salida
			ActividadSalidaPuerta salidas = new ActividadSalidaPuerta(puerta, parque);
			
			//Lanzamiento de hilos
			new Thread (entradas).start();
			new Thread (salidas).start();

			
			
		}
	}	
}