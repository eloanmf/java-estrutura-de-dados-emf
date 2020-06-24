package com.emf.hash;

import java.util.Scanner;

public class MinhaApp {

	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) {

		
		Scanner teclado = new Scanner(System.in);
		int key;
		
		
		MapaHash mapa = new MapaHash();
		
		Registro r1 = new Registro(789, "Registro 1");
		Registro r2 = new Registro(889, "Registro 2");
		
		System.out.println(r1.hashCode());
		System.out.println(r2.hashCode());
		
		
		
		mapa.put(new Registro(7865, "Registro 1"));
		mapa.put(new Registro(123, "Registro 2"));
		mapa.put(new Registro(987, "Registro 1"));
		mapa.put(new Registro(9832, "Registro 2"));
		
		//calcular baseado no valor chave que é o key do registro a posicao do vetor ele ta
		
        do{
            System.out.println("Digite um codigo de registro");
            key = teclado.nextInt();
            if (key != -1){
                Registro r = mapa.get(key);  //r recebe o mapa passando a chave
                if (r != null){
                    System.out.println("Registro Recuperado = "+r.getKey()+ " - "+r.getValue());
                }
                else{
                    System.out.println("Registro nao existente");
                }
            } 
        } while (key != -1);
		
		
	
		
		
		
		

	}

}
