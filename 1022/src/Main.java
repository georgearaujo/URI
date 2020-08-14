import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {

	private static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

	private static final String IMPRESSAO = "%d/%d = %d/%d";
	private static final String SEPARADOR = " ";
	private static final Integer ESCALA = 3;
	private static final Integer ZERO = 0;
	private static final Integer UM = 1;
	private static final Integer DOIS = 2;
	private static final Integer TRES = 3;
	
	private static final Integer NUMERADOR_UM = 0;
	private static final Integer DENOMINADOR_UM = 2;
	private static final Integer SINAL = 3;
	private static final Integer NUMERADOR_DOIS = 4;
	private static final Integer DENOMINADOR_DOIS = 6;

	private static final String SOMA = "+";
	private static final String SUBTRACAO = "-";
	private static final String MULTIPLICACAO = "*";
	private static final String DIVISAO = "/";
	
	
	public static void main(String[] args) throws IOException {
		Integer passos = obterInteiro();
		
		while(!passos.equals(ZERO)) {
			String[] expressao = obterTexto().split(SEPARADOR);
			Integer[] resultado = obterResultado(expressao);
			resultado = simplificarExpressao(resultado);
			escreverResposta(resultado);
			passos --;
		}
	
	}

	public static Integer obterSimplificador(Integer numerador, Integer denominador) throws NoSuchFieldException {
		Integer simplificador = obterMenor(numerador, denominador);
		
		while (UM < simplificador) {
			if (numerador % simplificador == ZERO &&
					denominador % simplificador == ZERO) {
				return simplificador;
			}
			simplificador --;
		}
		
		throw new NoSuchFieldException();
	}
	
	
	
	public static Integer[] simplificarExpressao(Integer[] expressao) {
		Integer simplificador = UM;
		Integer[] respostaCompleta = new Integer[] {expressao[ZERO], 
													expressao[UM], 
													expressao[ZERO], 
													expressao[UM]};
		while(true) {
			try {
				simplificador = obterSimplificador(respostaCompleta[DOIS], respostaCompleta[TRES]);
				respostaCompleta[DOIS] = respostaCompleta[DOIS] / simplificador;
				respostaCompleta[TRES] = respostaCompleta[TRES] / simplificador;
			} catch (NoSuchFieldException e) {
				return respostaCompleta;
			}
		}
	}
	
	public static Integer[] montarResposta(Integer numerador, Integer denominador, Integer simplificador) {
		return new Integer[] {numerador, denominador, numerador / simplificador, denominador / simplificador};
	}
	
	public static Integer[] obterResultado(String[] expressao) throws IOException {
		Integer[] resultado;
		switch (expressao[SINAL]) {
			case SOMA:
				resultado = somar(obterBigDecimal(expressao[NUMERADOR_UM]),
						obterBigDecimal(expressao[DENOMINADOR_UM]),
						obterBigDecimal(expressao[NUMERADOR_DOIS]),
						obterBigDecimal(expressao[DENOMINADOR_DOIS]));
				break;
			case SUBTRACAO:
				resultado = subtrair(obterBigDecimal(expressao[NUMERADOR_UM]),
						obterBigDecimal(expressao[DENOMINADOR_UM]),
						obterBigDecimal(expressao[NUMERADOR_DOIS]),
						obterBigDecimal(expressao[DENOMINADOR_DOIS]));
				break;
			case MULTIPLICACAO:
				resultado = multiplicar(obterBigDecimal(expressao[NUMERADOR_UM]),
						obterBigDecimal(expressao[DENOMINADOR_UM]),
						obterBigDecimal(expressao[NUMERADOR_DOIS]),
						obterBigDecimal(expressao[DENOMINADOR_DOIS]));
				break;
			case DIVISAO:
				resultado = dividir(obterBigDecimal(expressao[NUMERADOR_UM]),
						obterBigDecimal(expressao[DENOMINADOR_UM]),
						obterBigDecimal(expressao[NUMERADOR_DOIS]),
						obterBigDecimal(expressao[DENOMINADOR_DOIS]));
				break;
			default:
				throw new IOException();
		}
		return resultado;
	}
	
	public static Integer obterMenor(Integer valor1, Integer valor2) {	
		Integer valor = valor1 > valor2 ? valor2 : valor1;
		return  valor < ZERO ? valor * -UM : valor;
	}
	
	public static BigDecimal obterBigDecimal(String valor) {
		return new BigDecimal(valor).setScale(ESCALA, RoundingMode.HALF_UP);
	}
	
	public static Integer obterInteiro() throws IOException {
		return Integer.valueOf(obterTexto());
	}
	
	public static String obterTexto() throws IOException {
		return entrada.readLine();
	}
	
	public static void escreverResposta(Integer[] valores) {
		System.out.println(String.format(IMPRESSAO, valores[ZERO], 
													valores[UM], 
													valores[DOIS], 
													valores[TRES]));
	}
	
	public static Integer[] somar(BigDecimal numeradorUm, BigDecimal denominadorUm, BigDecimal numeradorDois, BigDecimal denominadorDois) {
		return new Integer[] { somarValores(multiplicarValores(numeradorUm, denominadorDois), multiplicarValores(numeradorDois, denominadorUm)).intValue(),
							multiplicarValores(denominadorUm, denominadorDois).intValue()};
	}
	
	public static Integer[] subtrair(BigDecimal numeradorUm, BigDecimal denominadorUm, BigDecimal numeradorDois, BigDecimal denominadorDois) {
		return new Integer[] { subtrairValores(multiplicarValores(numeradorUm, denominadorDois), multiplicarValores(numeradorDois, denominadorUm)).intValue(), 
							multiplicarValores(denominadorUm, denominadorDois).intValue()};
	}
	
	public static Integer[] multiplicar(BigDecimal numeradorUm, BigDecimal denominadorUm, BigDecimal numeradorDois, BigDecimal denominadorDois) {
		return new Integer[] { multiplicarValores(numeradorUm, numeradorDois).intValue(), 
							multiplicarValores(denominadorUm, denominadorDois).intValue()};
	}
	
	public static Integer[] dividir(BigDecimal numeradorUm, BigDecimal denominadorUm, BigDecimal numeradorDois, BigDecimal denominadorDois) {
		return new Integer[] { multiplicarValores(numeradorUm, denominadorDois).intValue(), 
							multiplicarValores(denominadorUm, numeradorDois).intValue()};	
	}
	
	public static BigDecimal somarValores(BigDecimal numerador, BigDecimal denominador) {
		return numerador.add(denominador);
	}
	
	public static BigDecimal subtrairValores(BigDecimal numerador, BigDecimal denominador) {
		return numerador.subtract(denominador);
	}
	
	public static BigDecimal multiplicarValores(BigDecimal numerador, BigDecimal denominador) {
		return numerador.multiply(denominador);
	}
	
	public static BigDecimal dividirValores(BigDecimal numerador, BigDecimal denominador) {
		return numerador.divide(denominador);
	}
}

