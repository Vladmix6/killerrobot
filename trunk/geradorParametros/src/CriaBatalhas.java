import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CriaBatalhas {

	public static String fileSeparator = System.getProperty("file.separator");
	public static String lineSeparator = System.getProperty("line.separator");
	public static String filePath = "batalhas";

	public static void main(String[] args) {
		int nRobos = 220;
		File folder = new File(filePath);
		folder.mkdir();
		String dados = "robocode.battleField.width=800" + lineSeparator
				+ "robocode.battleField.height=600" + lineSeparator
				+ "robocode.battle.numRounds=100" + lineSeparator
				+ "robocode.battle.gunCoolingRate=0.1" + lineSeparator
				+ "robocode.battle.rules.inactivityTime=450" + lineSeparator;
		for (int i = 1; i <= nRobos; i++) {
			String robos = "robocode.battle.selectedRobots=sample.TrackFire,ufcg.Killer"+i;
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(filePath
						+ fileSeparator + "Batalha"+i+".battle"));
				out.write(dados+robos);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
}
