import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class Main {

	private static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
	
	public static final String STRING_FORMAT = "%s";
	public static final String REGEX_ESPACO = " ";
	public static final String ZERO = "0";

	public static final int PRIMEIRA_POSICAO = 0;
	public static final int SEGUNDA_POSICAO = 1;
	public static final String NOVO_VALOR = "";
	
	public static void main(String[] args) throws IOException {
		String[] valores = obterArray();
		while(!ultimaEntrada(valores)) {
			escreverResposta(converterParaLong(removerCaracteres(valores)));
			valores = obterArray();
		}
	}

	public static String removerCaracteres(String[] entrada) {
		return entrada[SEGUNDA_POSICAO].replace(entrada[PRIMEIRA_POSICAO], NOVO_VALOR);
	}
	
	public static BigDecimal converterParaLong(String valor) {
		return new BigDecimal(parametrizarValor(valor));
	}
	
	public static String parametrizarValor(String valor) {
		return valor.isEmpty() ? ZERO : valor;
	}
	
	public static boolean ultimaEntrada(String[] entrada) {
		return ehZero(entrada[PRIMEIRA_POSICAO]) && ehZero(entrada[SEGUNDA_POSICAO]);
	}
	
	public static boolean ehZero(String valor) {
		return ZERO.equals(valor);
	}
	
	public static String[] obterArray() throws IOException {
		return obterTexto().split(REGEX_ESPACO);
	}
	
	public static String obterTexto() throws IOException {
		return entrada.readLine();
	}
	
	public static void escreverResposta(BigDecimal resposta) {
		System.out.println(String.format(STRING_FORMAT, resposta));
	}
}
