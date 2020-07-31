package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		escreverResposta(calcularTempo(obterNumeroInteiro()));
	}

	public static int obterNumeroInteiro() throws IOException {
		return Integer.valueOf(obterTexto());
	}
	
	public static String obterTexto() throws IOException {
		return new BufferedReader(new InputStreamReader(System.in)).readLine();
	}
	
	public static void escreverResposta(int resposta) {
		System.out.print(String.format("%d minutos", resposta));
	}
	
	private static int calcularTempo(int distancia) {
		return distancia * 2;
	}
}
