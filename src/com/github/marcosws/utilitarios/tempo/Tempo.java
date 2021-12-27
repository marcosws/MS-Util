package com.github.marcosws.utilitarios.tempo;

public class Tempo {
	
	private long tempoInicio;
	private long tempoFim;
	private long tempoTotal;
	
	public Tempo(){
		tempoInicio = 0;
		tempoFim = 0;
		tempoTotal = 0;
	}
	
	public long getTempoTotal() {
		return tempoTotal;
	}

	public void iniciarTempo(){
		tempoInicio = System.nanoTime();
	}
	
	public void finalizarTempo(){
		tempoFim = System.nanoTime();
		tempoTotal = ((tempoFim - tempoInicio) / 1000000);
	}
	
	public void imprimirTempoFinal(){
		long milisegundos = tempoTotal % 1000;
		long segundos = (tempoTotal / 1000) % 60;
		long minutos = (tempoTotal / 1000) / 60;
		System.out.printf("Tempo total: %s:%s.%s",String.format("%02d", minutos),String.format("%02d", segundos),String.format("%03d", milisegundos));
	}
	
	public void esperar(int segundos) {
		try {
			Thread.sleep(1000 * segundos);
		} 
		catch (InterruptedException e) {}
	}

}
