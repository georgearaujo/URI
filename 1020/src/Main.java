import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
	
	public static final String TEXTO_SAIDA = "%d ano(s)\n%d mes(es)\n%d dia(s)";
	public static final Integer ANO_EM_DIAS = 365;
	public static final Integer MES_EM_DIAS = 30;
	
	public static void main(String[] args) throws IOException {
		Integer dias = obterInteiro();
		Integer anos = obterTempo(ANO_EM_DIAS, dias);
		dias = removerTempo(ANO_EM_DIAS, anos, dias);
		Integer meses = obterTempo(MES_EM_DIAS, dias);
		dias = removerTempo(MES_EM_DIAS, meses, dias);
		escreverResposta(
				anos, 
				meses, 
				dias);
	}

	public static Integer obterInteiro() throws IOException {
		return Integer.valueOf(Main.obterTexto());
	}
	
	public static String obterTexto() throws IOException {
		return entrada.readLine();
	}
	
	public static Integer obterTempo(Integer tempo, Integer diasTotais) {
		return diasTotais / tempo;
	}
	
	public static Integer removerTempo(Integer tempo, Integer multiplicador, Integer diasTotais) {
		return diasTotais - tempo * multiplicador;
	}
	
	public static void escreverResposta(Integer anos, Integer meses, Integer dias) {
		System.out.println(String.format(TEXTO_SAIDA, anos, meses, dias));
	}
}
