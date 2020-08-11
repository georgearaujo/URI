import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
	
	public static final String TEXTO_SAIDA = "%s:%s:%s";
	public static final Integer HORA_EM_SEGUNDOS = 3600;
	public static final Integer MINUTO_EM_SEGUNDOS = 60;
	
	public static void main(String[] args) throws IOException {
		Integer segundos = obterInteiro();
		Integer horas = obterTempo(HORA_EM_SEGUNDOS, segundos);
		segundos = removerTempo(HORA_EM_SEGUNDOS, horas, segundos);
		Integer minutos = obterTempo(MINUTO_EM_SEGUNDOS, segundos);
		segundos = removerTempo(MINUTO_EM_SEGUNDOS, minutos, segundos);
		escreverResposta(
				horas.toString(), 
				minutos.toString(), 
				segundos.toString());
	}

	public static Integer obterInteiro() throws IOException {
		return Integer.valueOf(Main.obterTexto());
	}
	
	public static String obterTexto() throws IOException {
		return entrada.readLine();
	}
	
	public static Integer obterTempo(Integer tempo, Integer segundosTotais) {
		return segundosTotais / tempo;
	}
	
	public static Integer removerTempo(Integer tempo, Integer multiplicador, Integer segundosTotais) {
		return segundosTotais - tempo * multiplicador;
	}
	
	public static void escreverResposta(String horas, String minutos, String segundos) {
		System.out.println(String.format(TEXTO_SAIDA, horas, minutos, segundos));
	}
}
