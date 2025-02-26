package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;
	
	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtals);
	}
	
	public static class Marche{
		private Etal[] etals;
		
		public Marche(int nbEtals){
			etals = new Etal[nbEtals];
			for (int i = 0; i < etals.length; i++) {
				etals[i] = new Etal();
			}
		}
		
		public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit ) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		public int trouverEtalLibre() {
			for (int i = 0; i < etals.length; i++) {
				if (!etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}
		
		public Etal[] trouversEtals(String produit) {
			int nbEtalsProduit = 0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					nbEtalsProduit++;
				}
			}
			Etal[] etalsProduit = new Etal[nbEtalsProduit];
			
			for (int i = 0,j=0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					etalsProduit[j] = etals[i]; 
					j++;
				}
			}
		return etalsProduit;	
		}
		
		public Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].getVendeur() == gaulois) {
					return etals[i];
				}
			}
			return null;
		}
		
		public String afficherMarche() {
			StringBuilder texte = new StringBuilder();
			int etalsLibre = 0;
			for (int i = 0; i < etals.length; i++) {
				if(etals[i].isEtalOccupe()) {
					texte.append(etals[i].afficherEtal());
				}
				else {
					etalsLibre++;
				}
			}
			return texte + "Il reste " + etalsLibre +" non utilisés dans le marché";
		}
	}
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder texte = new StringBuilder();
		int etalLibre = marche.trouverEtalLibre();
		texte.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + produit +".\n");
		marche.utiliserEtal(etalLibre, vendeur, produit, nbProduit);
		texte.append("Le vendeur " + vendeur.getNom() + "vend des " + produit + " à l'étal n° " + etalLibre);
		return texte.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder texte = new StringBuilder();
		Etal[] etalsProduits = marche.trouversEtals(produit);
		if (etalsProduits.length == 0) {
			texte.append("Il n'y a pas de vendeur qui propose des " + produit + "au marché.\n");
			return texte.toString();
		}
		else if (etalsProduits.length == 1) {
			texte.append("Seul le vendeur " + etalsProduits[0].getVendeur().getNom() + " vend des " + produit + " sur le marché.\n");
			return texte.toString();
		}
		else {
			texte.append("Les vendeurs qui proposent des " + produit + " sont : \n");
			for (int i = 0; i < etalsProduits.length; i++) {
				texte.append("- " + etalsProduits[i].getVendeur().getNom() + "\n");
			}
		}
		return texte.toString();
	}
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}
	
	public String partirVendeur(Gaulois vendeur) {
		return rechercherEtal(vendeur).libererEtal();
	}
	
	public String afficherMarche() {
		StringBuilder texte = new StringBuilder();
		texte.append("Le marché du vilagge " + getNom() + "possède plusieurs étals: \n");
		return texte + marche.afficherMarche();
	}
	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}