package br.tiagopedroso;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class Banco {

	private String nome;
	private int numero;
	private List<Conta> contas = new ArrayList<>();

	public Banco(String nome, int numero) {
		this.nome = nome;
		this.numero = numero;
	}

	public void incluirConta(Conta conta){
		contas.add(conta);
	}

	public Conta getConta(int agencia, int numero) {
		return contas.stream()
				.filter(conta -> conta.agencia == agencia && conta.numero == numero)
				.findFirst()
				.orElse(Conta.INEXISTENTE);
	}

	public List<Conta> getContas(Cliente clienteProcurado) {
		return contas.stream()
				.filter(clienteAtual -> clienteAtual.equals(clienteProcurado))
				.collect(Collectors.toList());
	}

}
