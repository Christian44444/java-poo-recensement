package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.mesexceptions.AppExepts;

/**
 * Recherche et affichage de la population d'une région
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationRegionService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws AppExepts {

		System.out.println("Quel est le nom (ou le début de nom) de la région recherchée ? ");
		String choix = scanner.nextLine();

		List<Ville> villes = rec.getVilles();
		// Tester le choix département
		boolean exist = false;		
				
		int somme = 0;
		String nom = null;
		for (Ville ville : villes) {
			if (ville.getNomRegion().toLowerCase().equals(choix.toLowerCase())
					|| ville.getCodeRegion().toLowerCase().equals(choix.toLowerCase())) {
				somme += ville.getPopulation();
				nom = ville.getNomRegion();
				exist = true;
			}
		}
		if (!exist) {
			throw new AppExepts(choix + " veuillez saisir un département valide.");
		} 
		
		if (somme > 0) {
			System.out.println("Population de la région " + nom + " : " + somme);
		} else {
			System.out.println("Région " + choix + " non trouvée.");
		}
	}

}
