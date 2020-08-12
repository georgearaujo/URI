import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {

	private static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
	
	private static final Integer ESCALA = 3;
	
	public enum Notas {
		
		CEM_REAIS(0, "100.0", "NOTAS:\n%d nota(s) de R$ 100.00"),
		CINQUENTA_REAIS(1, "50.0", "%d nota(s) de R$ 50.00"),
		VINTE_REAIS(2, "20.0", "%d nota(s) de R$ 20.00"),
		DEZ_REAIS(3, "10.0", "%d nota(s) de R$ 10.00"),
		CINCO_REAIS(4, "5.0", "%d nota(s) de R$ 5.00"),
		DOIS_REAIS(5, "2.0", "%d nota(s) de R$ 2.00"),
		UM_REAL(6, "1.0", "MOEDAS:\n%d moeda(s) de R$ 1.00"),
		CINQUENTA_CENTAVOS(7, "0.50", "%d moeda(s) de R$ 0.50"),
		VINTE_E_CINCO_CENTAVOS(8, "0.25", "%d moeda(s) de R$ 0.25"),
		DEZ_CENTAVOS(9, "0.10", "%d moeda(s) de R$ 0.10"),
		CINCO_CENTAVOS(10, "0.05", "%d moeda(s) de R$ 0.05"),
		UM_CENTAVO(11 , "0.01", "%d moeda(s) de R$ 0.01");	
		
		private int indice;
		private String valor;
		private String mensagem;
		
		Notas (int indice, String valor, String mensagem){
			this.indice = indice;
			this.valor = valor;
			this.mensagem = mensagem;
		}
		
		public int getIndice() {
			return indice;
		}
		
		public BigDecimal getValor() {
			return new BigDecimal(valor);
		}
		
		public String getMensagem() {
			return mensagem;
		}
	}

	public static void main(String[] args) throws IOException {
		List<Notas> listaNotas = Arrays.asList(Notas.values());
		listaNotas.stream().sorted(Comparator.comparing(Notas::getIndice));
		BigDecimal valorTotal = obterBigDecimal();
		
		for(int i = 0; i < listaNotas.size(); i++) {
			Notas nota = listaNotas.get(i);
			BigDecimal quantidadeNota = obterQuantidadeNotas(nota.getValor(), valorTotal);
			valorTotal = removerValor(nota.getValor(), quantidadeNota, valorTotal);
			escreverResposta(String.format(nota.getMensagem(), quantidadeNota.intValue()));
		}
	}

	public static BigDecimal obterBigDecimal() throws IOException {
		return new BigDecimal(Main.obterTexto());
	}
	
	public static String obterTexto() throws IOException {
		return entrada.readLine();
	}
	
	public static BigDecimal obterQuantidadeNotas(BigDecimal valor, BigDecimal valorTotal) {
		return new BigDecimal(valorTotal.divide(valor, ESCALA, RoundingMode.HALF_EVEN).intValue());
	}
	
	public static BigDecimal removerValor(BigDecimal valor, BigDecimal multiplicador, BigDecimal valorTotal) {
		return valorTotal.subtract(valor.multiply(multiplicador));
	}
	
	public static void escreverResposta(String mensagem) {
		System.out.println(mensagem);
	}
	
}
