package com.emf.hash;

public class MapaHash {
	private Registro valores[];

	public MapaHash() {
		valores = new Registro[1000];
	}

	public void put(Registro registro) {
		int posicao = registro.hashCode();
		if (valores[posicao] == null) {
			valores[posicao] = registro;
			// System.out.println("=====Posicao vazia!! Novo Registro");
		} else {
			// System.out.print("Ops... colisao! >>>");
			Registro reg = valores[posicao];
			// especial para tratar o primeiro elemento
			if (reg.getKey() == registro.getKey()) { // o registro que quero inserir � o mesmo que j� existe?
				reg.setValue(registro.getValue()); // apenas substituo o valor
				// System.out.println("� o primeiro Registro Existente - atualizando...");
				return;
			}
			// se ele t� no meio da lista
			while (reg.getProximo() != null) {
				if (reg.getKey() == registro.getKey()) { // o registro que quero inserir � o mesmo que j� existe?
					reg.setValue(registro.getValue()); // apenas substituo o valor
					System.out.println("T� no meio da lista - Registro Existente - atualizando...");
					return;
				}
				reg = reg.getProximo();
			}
			// se ele � o �ltimo da lista
			// if (reg.getProximo() == null){
			if (reg.getKey() == registro.getKey()) {
				reg.setValue(registro.getValue());
				// System.out.println("� o ultimo! Registro Existente - atualizando...");
				return;
			}
			// }
			reg.setProximo(registro); // coloquei o novo registro na �ltima posicao
			// System.out.println("Novo Registro!");

		}
	}

	public Registro get(int key) {
		Registro r = new Registro();
		r.setKey(key);
		int posicao = r.hashCode();
		Registro resultado = valores[posicao]; // tento encontrar o registro
		if (resultado != null && resultado.getKey() == key) { // � o que eu t� procurando?
			return resultado;
		} else {
			while (resultado != null) { // enquanto tiver gente na lista...
				resultado = resultado.getProximo(); // vou pro pr�ximo
				if (resultado != null && resultado.getKey() == key) {// � o cara?
					return resultado; // se for, retorno ele
				}
			}
		}
		return null;
	}
}
