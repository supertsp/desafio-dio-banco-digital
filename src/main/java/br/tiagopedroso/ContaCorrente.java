package br.tiagopedroso;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ContaCorrente extends Conta {

	@Setter
	@Getter
	private double limite;

	@Getter
	private boolean limiteAtivo;

	public ContaCorrente(Banco banco, Cliente cliente) {
		super(banco, TipoConta.CORRENTE, cliente);
	}

	public void ativarLimite(double valor) {
		limiteAtivo = true;
		limite = valor;
	}

	public void desativarLimite() {
		limiteAtivo = false;
		limite = .0;
	}

	@Override
	public boolean sacar(double valor) {
		if ((saldo + limite) >= valor) {
			saldo -= valor;
			return true;
		}

		return false;
	}

	@Override
	public boolean transferir(double valor, IConta contaDestino) {
		if (this.sacar(valor)) {
			var depositoOk = contaDestino.depositar(valor);

			if (depositoOk) {
				return true;
			} else {
				depositar(valor);
				return false;
			}
		}

		return false;
	}

	@Override
	public void imprimirExtrato() {
		var extratoBase = getExtratoBase();

		if (isLimiteAtivo()) {
			extratoBase += String.format("   Limite: R$ %.2f \n", limite);
			extratoBase += String.format("   Saldo + Limite: R$ %.2f \n", (saldo + limite));
		}

		System.out.println(extratoBase);
	}
}
