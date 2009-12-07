import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Gera arquivos com todos os parametros possíveis
 * 
 * @author Isaac
 * 
 */
public class GeradorParametros {
	public static String fileSeparator = System.getProperty("file.separator");
	public static String filePath = "parametros";
	public static String lineSeparator = System.getProperty("line.separator");

	private static void criaAmbiente() {
		File folder = new File(filePath);
		folder.mkdir();
	}

	private static void criaArquivo(String dados) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(filePath
					+ fileSeparator + "parametros.txt"));
			out.write(dados);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

	}

	public static void main(String[] args) {
		String parametros = "";
		criaAmbiente();
		int idParametro = 0;
		// Tiro fraco
		for (double tiroFraco = 1; tiroFraco < 3; tiroFraco += 0.2)
			// Tiro médio
			for (double tiroMedio = tiroFraco; tiroMedio < 3; tiroMedio += 0.2)
				// Tiro forte
				for (double tiroForte = tiroMedio; tiroForte < 3; tiroForte += 0.2) {
					idParametro++;
					parametros += "NUM_SIMULACAO: " + idParametro
							+ lineSeparator;
					parametros += "TIRO_FRACO: " + tiroFraco + lineSeparator;
					parametros += "TIRO_MEDIO: " + tiroMedio + lineSeparator;
					parametros += "TIRO_FORTE: " + tiroForte + lineSeparator;
					parametros += lineSeparator;

				}
		criaArquivo(parametros);
		System.out.println("Criados " + idParametro + " Grupos de parametros");

	}
}
