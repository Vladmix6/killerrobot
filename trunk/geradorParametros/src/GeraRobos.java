import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class GeraRobos {

	public static String fileSeparator = System.getProperty("file.separator");
	public static String lineSeparator = System.getProperty("line.separator");
	public static String filePath = "robos";

	public static void main(String[] args) throws IOException {
		File folder = new File(filePath);
		folder.mkdir();
		String codigo = corpoPadrao();
		LinkedList<LinkedList<Double>> parametros = parametros();
		int nRobo = 0;
		for (LinkedList<Double> linkedList : parametros) {
			nRobo++;
			String temp = codigo.replaceAll("Killer000", "Killer"+nRobo);
			temp = temp.replaceAll("FRACO", linkedList.get(0)+"");
			temp = temp.replaceAll("MEDIO", linkedList.get(1)+"");
			temp = temp.replaceAll("FORTE", linkedList.get(2)+"");
			
			BufferedWriter out = new BufferedWriter(new FileWriter(filePath
					+ fileSeparator + "Killer"+nRobo+".java"));
			out.write(temp);
			out.close();
		}
	}

	public static String corpoPadrao() throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("killer.txt"));
		String codigo = "";
		while (in.ready()) {
			codigo += in.readLine() + lineSeparator;
		}
		in.close();
		return codigo;
	}

	public static LinkedList<LinkedList<Double>> parametros()
			throws IOException {
		LinkedList<LinkedList<Double>> saida = new LinkedList<LinkedList<Double>>();

		BufferedReader in = new BufferedReader(new FileReader("parametros"
				+ fileSeparator + "parametros.txt"));
		String codigo = "";
		LinkedList<Double> temp = new LinkedList<Double>();
		while (in.ready()) {
			String lido = in.readLine();
			if (lido.startsWith("NUM_SIMULACAO:")) {
				temp = new LinkedList<Double>();
				saida.add(temp);
			} else if (lido.startsWith("TIRO_FRACO:")) {
				String[] aux = lido.split(" ");
				temp.add(Double.parseDouble(aux[1]));
			} else if (lido.startsWith("TIRO_MEDIO:")) {
				String[] aux = lido.split(" ");
				temp.add(Double.parseDouble(aux[1]));
			} else if (lido.startsWith("TIRO_FORTE:")) {
				String[] aux = lido.split(" ");
				temp.add(Double.parseDouble(aux[1]));
			}
		}
		in.close();

		return saida;
	}
}
