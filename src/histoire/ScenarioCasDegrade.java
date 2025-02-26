package histoire;

import villagegaulois.Etal;
import personnages.Gaulois;
public class ScenarioCasDegrade {
	public static void main(String[] args) {
		Etal etal = new Etal();
		etal.libererEtal();
		System.out.println("Fin du test");
		
		Gaulois gaulois = new Gaulois("Jean Eudix",6);
		//etal.occuperEtal(gaulois, "pomme", 10);
		etal.acheterProduit(9, gaulois);
	}
}
