package org.example

import org.example.dresseur.Entraineur
import org.example.monde.Zone
import org.example.monstre.EspeceMonstre

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    /*var joueur = Entraineur(1,"Sacha",100)
    var rival = Entraineur(2, "Regis", 200)

    joueur.afficheDetail()
    joueur.argents += 15

    joueur.afficheDetail()*/

    val especeAquamy = EspeceMonstre(id = 7, nom = "Aquamy", type = "Meteo", baseAttaque = 10, baseDefense = 11, baseVitesse = 9, baseAttaqueSpe = 14, baseDefenseSpe = 14, basePv = 55, modAttaque = 9.0, modDefense = 10.0, modVitesse = 7.5, modAttaqueSpe = 12.0, modDefenseSpe = 12.0, modPv = 27.0, description = "Créature vaporeuse semblable à un nuage, produit des gouttes pures.", particularites = "Fait baisser la température en s’endormant.", caractères = "Calme, rêveur, mystérieux")
    val especeLaoumi = EspeceMonstre(id = 8, nom = "Laoumi", type = "Animal", baseAttaque = 11, baseDefense = 10, baseVitesse = 9, baseAttaqueSpe = 8, baseDefenseSpe = 11, basePv = 58, modAttaque = 11.0, modDefense = 8.0, modVitesse = 7.0, modAttaqueSpe = 6.0, modDefenseSpe = 11.5, modPv = 23.0, description = "Petit ourson au pelage soyeux, aime se tenir debout.", particularites = "Son grognement est mignon mais il protège ses amis.", caractères = "Affectueux, protecteur, gourmand")


    println(especeAquamy.afficheArt())

    val route1 = Zone(id = 1, nom = "Route 1", expZone = 50, especesMonstres = mutableListOf(especeAquamy))
    val route2 = Zone(id = 2, nom = "Route 2", expZone = 100, especesMonstres = mutableListOf(especeLaoumi))

    route1.zoneSuivante = route2
    route2.zonePrecedente = route1

    

}

/**
 * Change la couleur du message donné selon le nom de la couleur spécifié.
 * Cette fonction utilise les codes d'échappement ANSI pour appliquer une couleur à la sortie console. Si un nom de couleur
 * non reconnu ou une chaîne vide est fourni, aucune couleur n'est appliquée.
 *
 * @param message Le message auquel la couleur sera appliquée.
 * @param couleur Le nom de la couleur à appliquer (ex: "rouge", "vert", "bleu"). Par défaut c'est une chaîne vide, ce qui n'applique aucune couleur.
 * @return Le message coloré sous forme de chaîne, ou le même message si aucune couleur n'est appliquée.
 */
fun changeCouleur(message: String, couleur:String=""): String {
    val reset = "\u001B[0m"
    val codeCouleur = when (couleur.lowercase()) {
        "rouge" -> "\u001B[31m"
        "vert" -> "\u001B[32m"
        "jaune" -> "\u001B[33m"
        "bleu" -> "\u001B[34m"
        "magenta" -> "\u001B[35m"
        "cyan" -> "\u001B[36m"
        "blanc" -> "\u001B[37m"
        else -> "" // pas de couleur si non reconnu
    }
    return "$codeCouleur$message$reset"
}