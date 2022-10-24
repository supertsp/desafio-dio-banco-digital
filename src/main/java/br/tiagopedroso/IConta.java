package br.tiagopedroso;

public interface IConta {
	
	boolean sacar(double valor);
	
	boolean depositar(double valor);
	
	boolean transferir(double valor, IConta contaDestino);
	
	void imprimirExtrato();
}
