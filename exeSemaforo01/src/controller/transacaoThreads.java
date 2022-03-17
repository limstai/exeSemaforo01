package controller;
import java.util.concurrent.Semaphore;

public class transacaoThreads extends Thread {
	private int id;
	private Semaphore semaforo;

	public transacaoThreads(int _id, Semaphore _semaforo) {
		id = _id;
		semaforo = _semaforo;
	}

	public void run() {
		switch (id % 3) {
		case 1:
			// ((Math.random() * 801) + 200)
			executarTransacao(1000, 200, 801);break;
		case 2:
			// ((Math.random() * 1001) + 500)
			executarTransacao(1500, 500, 1001);break;
		case 0:
			// ((Math.random() * 1001) + 1000)
			executarTransacao(1500, 1000, 1001);break;
		}
	}
	private void executarTransacao(int tempo_bd, int tempo_minimo, int tempo_maximo) {
		for (int i = 0; i < 2; i++) {
			// Tempo de Transação. 1000 milissegundos = 1 segundo
			int tempo_calculo = (int) ((Math.random() * tempo_maximo) + tempo_minimo);
			try {
				// Cálculos
				System.out.println("Thread "+id+ ": calculando...");
				sleep(tempo_calculo);
				// Transação para BD
				semaforo.acquire();
				System.out.println("Thread "+id+": Transação BD");
				sleep(tempo_bd);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo.release();
			}
		}
	}
}
