import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CriaPontobat {

	public static String fileSeparator = System.getProperty("file.separator");
	public static String lineSeparator = System.getProperty("line.separator");

	public static void main(String[] args) throws IOException {
		String comando = "java -Xmx512M -Dsun.io.useCanonCaches=false -cp libs"+fileSeparator+"robocode.jar robocode.Robocode -battle battles"+fileSeparator+"batalhaXXX.battle -nodisplay -results resultados"+fileSeparator+"batalhaXXX.txt";
		String saida = "";
		for (int i = 1; i <= 220; i++) {
			saida += comando.replaceAll("batalhaXXX", "batalha" + i)
					+ lineSeparator;
		}
		BufferedWriter out = new BufferedWriter(new FileWriter(
				"executaBatalhas.bat"));
		out.write(saida);
		out.close();
	}
}
