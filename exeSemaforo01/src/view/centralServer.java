
package view;

import java.util.concurrent.Semaphore;
import controller.transacaoThreads;

public class centralServer {
	public static void main(String[] args) {
		int num_threads = 3;
		Semaphore fila_bd = new Semaphore(1);
		
		for(int i=0; i < num_threads; i++)
			new transacaoThreads(i, fila_bd).start();
	}
}
