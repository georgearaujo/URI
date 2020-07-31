package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class Main {

	private static final int ZERO = 0;
	private static final int DESLOCAMENTO_DIREITA = 3;
	private static final int DESLOCAMENTO_ESQUERDA = -1;
	private static final String UTF_8 = "UTF-8";
	
	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		long quantidadeRodadas = obterNumero();
		while(quantidadeRodadas > ZERO) {
			String texto = obterTexto();
			texto = deslocarMaiusculasMinusculas(texto);
			texto = inverterTexto(texto);
			texto = deslocarMetadeDosCaracteres(texto, metadeTruncada(texto.length()));
			System.out.println(texto);
			quantidadeRodadas --;
		}
	}

	public static String deslocarMetadeDosCaracteres(String texto, long metadeTamanho) throws UnsupportedEncodingException {
		byte[] novoTexto = texto.getBytes();
		for(int i = 0; i < texto.length(); i++) {
			if(i >= metadeTamanho) {
				novoTexto[i] += DESLOCAMENTO_ESQUERDA;
			}
		}
		return new String(novoTexto, UTF_8);
	}
	
	public static String deslocarMaiusculasMinusculas(String texto) throws UnsupportedEncodingException {
		byte[] novoTexto = texto.getBytes();
		for(int i = 0; i < texto.length(); i++) {
			if(ehCaracter(texto.charAt(i))) {
				novoTexto[i] += DESLOCAMENTO_DIREITA;
			}
		}
		return new String(novoTexto, UTF_8);
	}

	public static long metadeTruncada(long valor) {
		return valor/2;
	}
	
	public static long obterNumero() throws IOException {
		return Long.valueOf(obterTexto());
	}
	
	public static String obterTexto() throws IOException {
		return in.readLine();
	}
	
	public static String inverterTexto(String texto) {
		return new StringBuffer(texto).reverse().toString();
	}
	
	public static String adicionarCaracter(String texto, byte caracter) {
		return texto.concat(new String());
	}
	
	public static boolean ehCaracter(char caracter) {
		return Character.isAlphabetic(caracter);
	}
}
