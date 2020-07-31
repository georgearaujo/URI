package main;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {

	private static String texto = "%s km/l";
	
	private static Scanner entrada = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {
		BigDecimal distancia = obterNumero();
		BigDecimal combustivel = obterNumero();
		exibirResultado(calcularConsumo(distancia, combustivel));
	}
	
	public static BigDecimal obterNumero() {
		return entrada.nextBigDecimal();
	}
	
	public static void exibirResultado(BigDecimal consumo) {
		System.out.println(String.format(texto, consumo.toString()));
	}
	
	public static BigDecimal calcularConsumo(BigDecimal distancia, BigDecimal combustivel) {
		return distancia.divide(combustivel, 3, RoundingMode.UP);
	}
}
