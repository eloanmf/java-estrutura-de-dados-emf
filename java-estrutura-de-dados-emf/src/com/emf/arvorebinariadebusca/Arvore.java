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
	
	//remover o no da arvore
	public Arvore remover(Elemento elem) {
		//caso 1 - achei o elemento
		if(this.ele.getValor() == elem.getValor()) {
			//caso mais simples - o elemento est� em um n� sem filhos
			if(this.dir == null && this.esq == null) {
				return null;
			} else {
				//caso 2 - eu tenho filhos na esquerda mas n�o tenho na direita.
				if(this.esq != null && this.dir == null) {
					return this.esq;
				} else if (this.dir != null && this.esq == null) {
					return this.dir;
				}
				// caso 4 - filho dos dois mados (esq e dir)
				else {
					//adotar a estrat�gia do maior dentre os menores!
					Arvore aux = this.esq;
					while(aux.dir != null) {
						aux = aux.dir;
					}
					//troco os elementos da �rvore
					this.ele = aux.getEle();  //o n� atual recebe o elemento do aux
											 // o maior entre os menores
					aux.setEle(elem);		//insiro no n� folha (l� embaixo) o elemento a ser eliminado.
					this.esq = esq.remover(elem);
				}	
			}		
		}
		else if(elem.getValor() < this.ele.getValor()) {
			//se for menor � dado a responsabilidade � sub-arvore da esquerda.
			this.esq = this.esq.remover(elem);
		}
		else if(elem.getValor() > this.ele.getValor()) {
			//se for maior � dado a responsabilidade � sub-arvore da direita.
			this.dir = this.dir.remover(elem);
		}
		return this;
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
				if(this.esq == null) { //sou um n� vazio?
					this.esq = novaArvore;
					//System.out.println("Inserir o elemento "+ novo.getClass() + "� esquerda de "+this.ele.getValor());
				} else {
					this.esq.inserir(novo); //repassando a responsabilidade pra sub-�rvore esq.
				}
			} else if (novo.getValor() > ele.getValor()) { //vou inserir do lado direita
				if(this.dir == null) {
					this.dir = novaArvore;
					//System.out.println("Inserir o elemento "+ novo.getClass() + "� direita de "+this.ele.getValor());
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
