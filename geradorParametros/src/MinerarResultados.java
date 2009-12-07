import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class MinerarResultados {

	public static String fileSeparator = System.getProperty("file.separator");
	public static String lineSeparator = System.getProperty("line.separator");
	public static String filePath = "resultados";

	public static void main(String[] args) throws IOException {
		LinkedList<Integer> melhores = new LinkedList<Integer>();
		int maior = 0;
		LinkedList<Integer> ranking = sobrevivencia();
		int nRobo = 0;
		for (Integer result : ranking) {
			nRobo++;
			if(maior < result){
				melhores.clear();
				melhores.add(nRobo);
				maior=result;
			}else if (maior == result)
				melhores.add(nRobo);
		}
		System.out.println("Maior Sobrevivencia: "+maior);
		System.out.println(melhores);
	}

	public static LinkedList<Integer> sobrevivencia()
			throws IOException {
		LinkedList<Integer> saida = new LinkedList<Integer>();
		for (int i = 1; i <= 220; i++) {
			BufferedReader in = new BufferedReader(new FileReader(filePath
					+ fileSeparator + "batalha"+i+".txt"));
			while (in.ready()) {
				String lido = in.readLine();
				if (lido.contains("ufcg.Killer")) {
					String[] split = lido.split("\t");
					saida.add(Integer.parseInt(split[2]));
				}
			}
			in.close();
		}

		return saida;
	}
}
