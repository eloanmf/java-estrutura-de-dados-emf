package com.emf.arvoreavl;

public class ArvoreAVL {

	private Elemento ele;
	private ArvoreAVL dir;
	private ArvoreAVL esq;
	private int bal;

	public ArvoreAVL() {
		this.ele = null;
		this.dir = null;
		this.esq = null;
		this.bal = 0;
	}

	public ArvoreAVL(Elemento elem) {
		this.ele = elem;
		this.dir = null;
		this.esq = null;
		this.bal = 0;
		// System.out.println("Criou a arvore com elemento " + elem.getValor());
	}

	// remover o no da arvore
	public ArvoreAVL remover(Elemento elem) {
		// caso 1 - achei o elemento
		if (this.ele.getValor() == elem.getValor()) {
			// caso mais simples - o elemento está em um nó sem filhos
			if (this.dir == null && this.esq == null) {
				return null;
			} else {
				// caso 2 - eu tenho filhos na esquerda mas não tenho na direita.
				if (this.esq != null && this.dir == null) {
					return this.esq;
				} else if (this.dir != null && this.esq == null) {
					return this.dir;
				}
				// caso 4 - filho dos dois mados (esq e dir)
				else {
					// adotar a estratégia do maior dentre os menores!
					ArvoreAVL aux = this.esq;
					while (aux.dir != null) {
						aux = aux.dir;
					}
					// troco os elementos da árvore
					this.ele = aux.getEle(); // o nó atual recebe o elemento do aux
												// o maior entre os menores
					aux.setEle(elem); // insiro no nó folha (lá embaixo) o elemento a ser eliminado.
					this.esq = esq.remover(elem);
				}
			}
		} else if (elem.getValor() < this.ele.getValor()) {
			// se for menor é dado a responsabilidade á sub-arvore da esquerda.
			this.esq = this.esq.remover(elem);
		} else if (elem.getValor() > this.ele.getValor()) {
			// se for maior é dado a responsabilidade á sub-arvore da direita.
			this.dir = this.dir.remover(elem);
		}
		return this;
	}

	public int calcularAltura() {
		if (this.esq == null && this.dir == null) { // nao tenho nenhum filho
			return 1;
		} else if (this.esq != null && this.dir == null) { // filho so do lado esquerdo
			return 1 + this.esq.calcularAltura();
		} else if (this.esq == null && this.dir != null) { // filho do do lado direito
			return 1 + this.dir.calcularAltura();
		} else { // filho dos dois lados
			return 1 + Math.max(this.esq.calcularAltura(), this.dir.calcularAltura()); // pegar a maior altura entre
																						// direita e esquerda e pegar a
																						// minha altura pra poder
																						// retornar
		}
	}

	public void calcularBalanceamento() {
		if (this.dir == null && this.esq == null) {
			this.bal = 0;
		} else if (this.esq == null && this.dir != null) {
			this.bal = this.dir.calcularAltura() - 0; // 0 é só pra falar q esquerda nao tem altura mas é irrelevante
		} else if (this.esq != null && this.dir == null) {
			this.bal = 0 - this.esq.calcularAltura();
		} else {
			this.bal = this.dir.calcularAltura() - this.esq.calcularAltura();
		}
		if (this.dir != null)
			this.dir.calcularBalanceamento();
		if (this.esq != null)
			this.esq.calcularBalanceamento();
	}

	// metodo de verificacao e rotacao
	public ArvoreAVL verificaBalanceamento() {
		if (this.bal >= 2 || this.bal <= -2) {
			if (this.bal >= 2) {
				if (this.bal * this.dir.getBalanceamento() > 0) {
					System.out.println("Rotacao Simples Direita");
					return rotacaoSimplesDireita();
				} else {
					System.out.println("Rotacao Dupla Direita");
					return rotacaoDuplaDireita();
				}
			} else { // bal <= -2
				if (this.bal * this.esq.getBalanceamento() > 0) {
					System.out.println("Rotacao Simples Esquerda");
					return rotacaoSimplesEsquerda();
				} else {
					System.out.println("Rotacao Dupla Esquerda");
					return rotacaoDuplaEsquerda();
				}
			}
		}
		this.calcularBalanceamento();
		if (this.esq != null)
			this.esq = this.esq.verificaBalanceamento();
		if (this.dir != null)
			this.dir = this.dir.verificaBalanceamento();
		return this;
	}

	public ArvoreAVL rotacaoSimplesDireita() {
		ArvoreAVL filhoDir;
		ArvoreAVL filhoDoFilho = null;
		filhoDir = this.getDir();
		if (this.dir != null) {
			if (this.dir.getEsq() != null) {
				filhoDoFilho = filhoDir.getEsq();
			}
		}
		filhoDir.setEsq(this);
		this.setDir(filhoDoFilho);
		return filhoDir;
	}

	public ArvoreAVL rotacaoSimplesEsquerda() {
		ArvoreAVL filhoEsq;
		ArvoreAVL filhoDoFilho = null;
		filhoEsq = this.getEsq();
		if (this.esq != null) {
			if (this.esq.getDir() != null) {
				filhoDoFilho = filhoEsq.getDir();
			}
		}
		filhoEsq.setDir(this);
		this.setEsq(filhoDoFilho);
		return filhoEsq;
	}

	public ArvoreAVL rotacaoDuplaDireita() {
		ArvoreAVL arvore = this;
		ArvoreAVL filhoDir = this.getDir();
		ArvoreAVL filhoDoFilho = filhoDir.getEsq();
		ArvoreAVL noInserido = filhoDoFilho.getDir();
		// parte 1: alinhar os caras
		filhoDir.setEsq(noInserido);
		filhoDoFilho.setDir(filhoDir);
		this.setDir(filhoDoFilho);
		// parte 2: tornar o filho à direita a nova raiz
		ArvoreAVL novoFilhoDireita = this.getDir();
		arvore.setDir(null);
		novoFilhoDireita.setEsq(arvore);
		return novoFilhoDireita;
	}

	public ArvoreAVL rotacaoDuplaEsquerda() {
		ArvoreAVL arvore = this;
		ArvoreAVL filhoEsq = this.getEsq();
		ArvoreAVL filhoDoFilho = filhoEsq.getDir();
		ArvoreAVL noInserido = filhoDoFilho.getEsq();
		// parte 1: alinhar os caras
		filhoEsq.setDir(noInserido);
		filhoDoFilho.setEsq(filhoEsq);
		this.setEsq(filhoDoFilho);
		// parte 2: tornar o filho à esquerda a nova raiz
		ArvoreAVL novoFilhoEsquerda = this.getEsq();
		arvore.setEsq(null);
		novoFilhoEsquerda.setDir(arvore);
		return novoFilhoEsquerda;
	}

	// metodos de controle
	public boolean isEmpty() {
		return (this.ele == null);
	}

	public ArvoreAVL inserir(Elemento novo) {
		if (isEmpty()) {
			this.ele = novo;
		} else {
			ArvoreAVL novaArvore = new ArvoreAVL(novo);
			if (novo.getValor() < ele.getValor()) { // vou inserir do lado esquerdo
				if (this.esq == null) { // sou um nó vazio?
					this.esq = novaArvore;
					// System.out.println("Inserir o elemento "+ novo.getClass() + "á esquerda de
					// "+this.ele.getValor());
				} else {
					this.esq = this.esq.inserir(novo); // repassando a responsabilidade pra sub-árvore esq.
				}
			} else if (novo.getValor() > ele.getValor()) { // vou inserir do lado direita
				if (this.dir == null) {
					this.dir = novaArvore;
					// System.out.println("Inserir o elemento "+ novo.getClass() + "á direita de
					// "+this.ele.getValor());
				} else {
					this.dir = this.dir.inserir(novo);
				}
			}
		}
		return this;
	}

	public void imprimirPreOrdem() {
		if (!isEmpty()) {
			System.out.print(this.ele.getValor() + " ");
			if (this.esq != null) {
				this.esq.imprimirPreOrdem();
			}
			if (this.dir != null) {
				this.dir.imprimirPreOrdem();
			}
		}
	}

	public void imprimirOrdem() {
		if (!isEmpty()) {
			if (this.esq != null) {
				this.esq.imprimirOrdem();
			}
			System.out.print(this.ele.getValor() + " ");
			if (this.dir != null) {
				this.dir.imprimirOrdem();
			}
		}
	}

	public void imprimirPosOrdem() {
		if (!isEmpty()) {
			if (this.dir != null) {
				this.dir.imprimirPosOrdem();
			}
			if (this.esq != null) {
				this.esq.imprimirPosOrdem();
			}
			System.out.print(this.ele.getValor() + " ");
		}
	}

	public void imprimirOrdemInvestida() {
		if (!isEmpty()) {
			if (this.dir != null) {
				this.dir.imprimirOrdemInvestida();
			}
			System.out.print(this.ele.getValor() + " ");
			if (this.esq != null) {
				this.esq.imprimirOrdemInvestida();
			}

		}
	}

	public boolean busca(int valor) {
		if (isEmpty()) {
			return false;
		}

		if (this.ele.getValor() == valor) {
			return true;
		} else {
			if (valor < this.ele.getValor()) {
				if (this.esq == null) {
					return false;
				} else {
					return this.esq.busca(valor); // repassando a responsabiliade para a sub-arvore esquerda
				}
			} else if (valor > this.ele.getValor()) {
				if (this.dir == null) {
					return false;
				} else {
					return this.dir.busca(valor);
				}
			}
			return false;
		}
	}

	// gets e sets
	public Elemento getEle() {
		return ele;
	}

	public void setEle(Elemento ele) {
		this.ele = ele;
	}

	public ArvoreAVL getDir() {
		return dir;
	}

	public void setDir(ArvoreAVL dir) {
		this.dir = dir;
	}

	public ArvoreAVL getEsq() {
		return esq;
	}

	public void setEsq(ArvoreAVL esq) {
		this.esq = esq;
	}

	public int getBalanceamento() {
		return this.bal;
	}

	public void setBalanceamento(int bal) {
		this.bal = bal;
	}

	// metodo de depuracao
	public String printArvore(int level) {
		String str = this.toString() + "\n";
		for (int i = 0; i < level; i++) {
			str += "\t";
		}
		if (this.esq != null) {
			str += "+-ESQ: " + this.esq.printArvore(level + 1);
		} else {
			str += "+-ESQ: NULL";
		}
		str += "\n";
		for (int i = 0; i < level; i++) {
			str += "\t";
		}
		if (this.dir != null) {

			str += "+-DIR: " + this.dir.printArvore(level + 1);
		} else {
			str += "+-DIR: NULL";
		}
		str += "\n";
		return str;
	}

	public String toString() {
		return "[" + this.ele.getValor() + "] (" + this.bal + ")";
	}

}
