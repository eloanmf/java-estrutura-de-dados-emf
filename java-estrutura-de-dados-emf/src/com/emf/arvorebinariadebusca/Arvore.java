package com.emf.arvorebinariadebusca;

public class Arvore {
	
	private Elemento ele;
	private Arvore dir;
	private Arvore esq;
	
	public Arvore() {
		this.ele = null;
		this.dir = null;
		this.esq = null;
	}
	
	public Arvore(Elemento elem) {
		this.ele = elem;
		this.dir = null;
		this.esq = null;
		//System.out.println("Criou a arvore com elemento " + elem.getValor());
	}
	
	//metodos de controle
	public boolean isEmpty() {
		return (this.ele == null);
	}
	
	public void inserir(Elemento novo) {
		if (isEmpty()) {
			this.ele = novo;
		} else {
			Arvore novaArvore = new Arvore(novo);
			if (novo.getValor() < ele.getValor()) { //vou inserir do lado esquerdo
				if(this.esq == null) { //sou um nó vazio?
					this.esq = novaArvore;
					//System.out.println("Inserir o elemento "+ novo.getClass() + "á esquerda de "+this.ele.getValor());
				} else {
					this.esq.inserir(novo); //repassando a responsabilidade pra sub-árvore esq.
				}
			} else if (novo.getValor() > ele.getValor()) { //vou inserir do lado direita
				if(this.dir == null) {
					this.dir = novaArvore;
					//System.out.println("Inserir o elemento "+ novo.getClass() + "á direita de "+this.ele.getValor());
				} else {
					this.dir.inserir(novo);
				}
			}
			
		}
	}
	
	public void imprimirPreOrdem() {
		if(!isEmpty()) {
			System.out.print(this.ele.getValor() + " ");
			if(this.esq != null) {
				this.esq.imprimirPreOrdem();
			} 
			if(this.dir != null) {
				this.dir.imprimirPreOrdem();
			}
		}
	}
	
	public void imprimirOrdem() {
		if(!isEmpty()) {
			if(this.esq != null) {
				this.esq.imprimirOrdem();
			}
			System.out.print(this.ele.getValor() + " ");
			if(this.dir != null) {
				this.dir.imprimirOrdem();
			}
		}
	}
	
	public void imprimirPosOrdem() {
		if(!isEmpty()) {
			if(this.dir != null) {
				this.dir.imprimirPosOrdem();
			}
			if(this.esq != null) {
				this.esq.imprimirPosOrdem();
			}
			System.out.print(this.ele.getValor() + " ");
		}
	}
	
	public void imprimirOrdemInvestida() {
		if(!isEmpty()) {
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
		
		if(this.ele.getValor() == valor) {
			return true;
		}
		else {
			if(valor < this.ele.getValor()) {
				if(this.esq == null) {
					return false;
				} 
				else {
					return this.esq.busca(valor); //repassando a responsabiliade para a sub-arvore esquerda
				}
			}
			else if (valor > this.ele.getValor()) {
				if(this.dir ==null) {
					return false;
				} else {
					return this.dir.busca(valor);
				}
			}
			return false;
		} 
	}
	
	
	
	//gets e sets
	public Elemento getEle() {
		return ele;
	}

	public void setEle(Elemento ele) {
		this.ele = ele;
	}

	public Arvore getDir() {
		return dir;
	}

	public void setDir(Arvore dir) {
		this.dir = dir;
	}

	public Arvore getEsq() {
		return esq;
	}

	public void setEsq(Arvore esq) {
		this.esq = esq;
	}
	
	
}
