package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.mesexceptions.AppExepts;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws AppExepts {

		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();

		System.out.println("Choississez une population minimum (en milliers d'habitants): ");
		String saisieMin = scanner.nextLine();
		
		System.out.println("Choississez une population maximum (en milliers d'habitants): ");
		String saisieMax = scanner.nextLine();
		
		// Si l'utilisateur saisit une lettre au de chiffres pour le min
		if (!NumberUtils.isDigits(saisieMin)) {
			throw new AppExepts(saisieMin + " veuillez saisir un nombre pour la valeur minimum");
		}
		// Si l'utilisateur saisit une lettre au de chiffres pour le max
		if (!NumberUtils.isDigits(saisieMax)) {
			throw new AppExepts(saisieMax + " veuillez saisir un nombre pour la valeur maximum.");
		}
		// Nombre entier ne sert à rien car isDigits ne supporte pas les décimals
		if (saisieMin.contains(".")) {
			throw new AppExepts(saisieMin + " veuillez saisir un entier pour la valeur minimum.");
		}
		if (saisieMax.contains(".")) {
			throw new AppExepts(saisieMax + " veuillez saisir un entier pour la valeur maximum.");
		}
		
		int min = Integer.parseInt(saisieMin) * 1000;
		int max = Integer.parseInt(saisieMax) * 1000;
		// Si les min ou max sont négatifs ou le min sup au max
		if (min <0) {
			throw new AppExepts(saisieMin + " veuillez saisir un nombre positif ou nul pour la valeur minimum.");
		}
		if (max <0) {
			throw new AppExepts(saisieMax + " veuillez saisir un nombre positif ou nul pour la valeur maximum.");
		}
		if (max <= min) {
			throw new AppExepts(saisieMin + " veuillez saisir un nombre min inférieur au max: " + saisieMax + ".");
		}
		// Tester le choix département
		boolean exist = false;		
		List<Ville> villes = rec.getVilles();
		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
				exist = true;
				if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
					System.out.println(ville);
				}
			}
		}
		if (!exist) {
			throw new AppExepts(choix + " veuillez saisir un département valide.");
		} 
	}

}
