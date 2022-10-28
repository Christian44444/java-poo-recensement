package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.mesexceptions.AppExepts;

/**
 * Recherche et affichage de la population d'une ville
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationVilleService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws AppExepts {

		System.out.println("Quel est le nom de la ville recherchée ? ");
		String choix = scanner.nextLine();
		
		// Tester le choix département
		boolean exist = false;		

		List<Ville> villes = rec.getVilles();
		for (Ville ville : villes) {
			if (ville.getNom().equalsIgnoreCase(choix)
					|| ville.getNom().toLowerCase().startsWith(choix.toLowerCase())) {
				System.out.println(ville);
				exist = true;
			}
		}
		if (!exist) {
			throw new AppExepts(choix + " veuillez saisir une ville valide.");
		} 

	}

}
