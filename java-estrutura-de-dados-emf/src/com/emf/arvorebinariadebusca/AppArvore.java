package com.emf.arvorebinariadebusca;

public class AppArvore {

	public static void main(String[] args) {
		
		Elemento elem = new Elemento(8);
		
		Arvore arvore = new Arvore(new Elemento(20));
		arvore.inserir(new Elemento(10));
		arvore.inserir(new Elemento(5));
		arvore.inserir(new Elemento(2));
		arvore.inserir(new Elemento(8));
		arvore.inserir(new Elemento(15));
		arvore.inserir(new Elemento(12));
		arvore.inserir(new Elemento(18));
		arvore.inserir(new Elemento(30));
		arvore.inserir(new Elemento(25));
		arvore.inserir(new Elemento(22));
		arvore.inserir(new Elemento(28));
		arvore.inserir(new Elemento(35));
		arvore.inserir(new Elemento(32));
		arvore.inserir(new Elemento(38));
		
		System.out.println("O elemento 5" + (arvore.busca(5) ?" existe" : " nao existe"));
		System.out.println("O elemento 12" + (arvore.busca(12) ?" existe" : " nao existe"));
		System.out.println("O elemento 18" + (arvore.busca(18) ?" existe" : " nao existe"));
		System.out.println("O elemento 35" + (arvore.busca(35) ?" existe" : " nao existe"));
		
		
		arvore.imprimirPreOrdem();
		System.out.println();
		arvore.imprimirOrdem();
		System.out.println();
		arvore.imprimirPosOrdem();
		System.out.println();
		arvore.imprimirOrdemInvestida();
		System.out.println("");
		
		elem.setValor(38);
		arvore = arvore.remover(elem);
		System.out.println("Nova arvore....");
		arvore.imprimirOrdem();

		elem.setValor(35);
		arvore = arvore.remover(elem);
		System.out.println();
		arvore.imprimirOrdem();
		
		elem.setValor(20);
		arvore = arvore.remover(elem);
		System.out.println();
		arvore.imprimirOrdem();
		
		
		
		
	}

}
