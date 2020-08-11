import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {

	private static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
	
	public static final String STRING_FORMAT = "%.3f";
	public static final Integer KM_POR_LITRO = 12;
	public static final Integer ESCALA = 3;
	public static final String PONTO = ".";
	public static final String VIRGULA = ",";
	
	
	public static void main(String[] args) throws IOException {
		Integer tempo = obterInteiro();
		Integer distancia = obterInteiro();
		escreverResposta(calcularGastoCombustivel(calcularDistanciaPercorrida(tempo, distancia)));
	}

	public static BigDecimal calcularDistanciaPercorrida(Integer tempo, Integer distancia){
		return new BigDecimal(tempo).multiply(new BigDecimal(distancia));
	}
	
	public static BigDecimal calcularGastoCombustivel(BigDecimal distanciaPercorrida){
		return distanciaPercorrida.divide(new BigDecimal(KM_POR_LITRO), ESCALA, RoundingMode.HALF_UP);
	}
	
	public static Integer obterInteiro() throws IOException {
		return Integer.valueOf(Main.obterTexto());
	}
	
	public static String obterTexto() throws IOException {
		return entrada.readLine();
	}
	
	public static void escreverResposta(BigDecimal resposta) {
		System.out.println(String.format(STRING_FORMAT, resposta.doubleValue()).replace(VIRGULA, PONTO));
	}
}
