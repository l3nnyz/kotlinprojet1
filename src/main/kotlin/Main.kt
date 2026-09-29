package org.example

import org.example.dresseur.Entraineur
import org.example.item.MonsterKube
import org.example.jeu.Partie
import org.example.monde.Zone
import org.example.monstre.EspeceMonstre
import org.example.monstre.IndividuMonstre

val especeAquamy = EspeceMonstre(
    id = 7,
    nom = "Aquamy",
    type = "Meteo",
    baseAttaque = 10,
    baseDefense = 11,
    baseVitesse = 9,
    baseAttaqueSpe = 14,
    baseDefenseSpe = 14,
    basePv = 55,
    modAttaque = 9.0,
    modDefense = 10.0,
    modVitesse = 7.5,
    modAttaqueSpe = 12.0,
    modDefenseSpe = 12.0,
    modPv = 27.0,
    description = "Créature vaporeuse semblable à un nuage, produit des gouttes pures.",
    particularites = "Fait baisser la température en s’endormant.",
    caractères = "Calme, rêveur, mystérieux"
)
val especeLaoumi = EspeceMonstre(
    id = 8,
    nom = "Laoumi",
    type = "Animal",
    baseAttaque = 11,
    baseDefense = 10,
    baseVitesse = 9,
    baseAttaqueSpe = 8,
    baseDefenseSpe = 11,
    basePv = 58,
    modAttaque = 11.0,
    modDefense = 8.0,
    modVitesse = 7.0,
    modAttaqueSpe = 6.0,
    modDefenseSpe = 11.5,
    modPv = 23.0,
    description = "Petit ourson au pelage soyeux, aime se tenir debout.",
    particularites = "Son grognement est mignon mais il protège ses amis.",
    caractères = "Affectueux, protecteur, gourmand"
)
val especeSpringleaf = EspeceMonstre(
    id = 1,
    nom = "Springleaf",
    type = "Graine",
    baseAttaque = 9,
    baseDefense = 11,
    baseVitesse = 10,
    baseAttaqueSpe = 12,
    baseDefenseSpe = 14,
    basePv = 60,
    modAttaque = 6.5,
    modDefense = 9.0,
    modVitesse = 8.0,
    modAttaqueSpe = 7.0,
    modDefenseSpe = 10.0,
    modPv = 34.0,
    description = "Petit monstre espiègle rond comme une graine, adore le soleil.",
    particularites = "Sa feuille sur la tête indique son humeur.",
    caractères = "Curieux, amical, timide"
)
val especeFlamkip = EspeceMonstre(
    id = 4,
    nom = "Flamkip",
    type = "Animal",
    baseAttaque = 12,
    baseDefense = 8,
    baseVitesse = 13,
    baseAttaqueSpe = 16,
    baseDefenseSpe = 7,
    basePv = 50,
    modAttaque = 10.0,
    modDefense = 5.5,
    modVitesse = 9.5,
    modAttaqueSpe = 9.5,
    modDefenseSpe = 6.5,
    modPv = 22.0,
    description = "Petit animal entouré de flammes, déteste le froid.",
    particularites = "Sa flamme change d’intensité selon son énergie.",
    caractères = "Impulsif, joueur, loyal"
)
val especeBugsyface = EspeceMonstre(
    id = 10,
    nom = "Bugsyface",
    type = "Insecte",
    baseAttaque = 10,
    baseDefense = 13,
    baseVitesse = 8,
    baseAttaqueSpe = 7,
    baseDefenseSpe = 13,
    basePv = 45,
    modAttaque = 7.0,
    modDefense = 11.0,
    modVitesse = 6.5,
    modAttaqueSpe = 8.0,
    modDefenseSpe = 11.5,
    modPv = 21.0,
    description = "Insecte à carapace luisante, se déplace par bonds et vibre des antennes.",
    particularites = "Sa carapace devient plus dure après chaque mue.",
    caractères = "Travailleur, sociable, infatigable"
)
val especeGalum = EspeceMonstre(
    id = 13,
    nom = "Galum",
    type = "Minéral",
    baseAttaque = 12,
    baseDefense = 15,
    baseVitesse = 6,
    baseAttaqueSpe = 8,
    baseDefenseSpe = 12,
    basePv = 55,
    modAttaque = 9.0,
    modDefense = 13.0,
    modVitesse = 4.0,
    modAttaqueSpe = 6.5,
    modDefenseSpe = 10.5,
    modPv = 13.0,
    description = "Golem ancien de pierre, yeux lumineux en garde.",
    particularites = "Peut rester immobile des heures comme une statue.",
    caractères = "Sérieux, stoïque, fiable"
)

val route1 = Zone(id = 1, nom = "Route 1", expZone = 50, especesMonstres = mutableListOf(especeAquamy))
val route2 = Zone(id = 2, nom = "Route 2", expZone = 100, especesMonstres = mutableListOf(especeLaoumi))

var joueur = Entraineur(1, "Sacha", 100)
var rival = Entraineur(2, "Rival", 500)

var kube1 = MonsterKube(1, "Kube","Petit kube pour capturer un monstre", 50.0)

val monstre1 = IndividuMonstre(1, "springleaf", expInit = 1500.0, espece = especeSpringleaf)
val monstre2 = IndividuMonstre(2, "flamkip", expInit = 1500.0, espece = especeFlamkip)
val monstre3 = IndividuMonstre(3, "aquamy", expInit = 1500.0, espece = especeAquamy)

fun nouvellePartie(): Partie {
    println("=== Bienvenue dans Monster Game ! ===")
    print("Entrez votre nom de joueur : ")
    val nom = readlnOrNull()?.trim() ?: "Dresseur"
    joueur.nom = nom
    return Partie(1, joueur, route1)
}

fun main() {
    /*var joueur = Entraineur(1,"Sacha",100)
    var rival = Entraineur(2, "Regis", 200)

    joueur.afficheDetail()
    joueur.argents += 15

    joueur.afficheDetail()*/


    /*
    println(especeAquamy.afficheArt())

    val route1 = Zone(id = 1, nom = "Route 1", expZone = 50, especesMonstres = mutableListOf(especeAquamy))
    val route2 = Zone(id = 2, nom = "Route 2", expZone = 100, especesMonstres = mutableListOf(especeLaoumi))

    route1.zoneSuivante = route2
    route2.zonePrecedente = route1
    */
    /*


    val kubeDeBase = MonsterKube(1, "Kube Basique", "Un kube standard pour capturer des monstres.", 0.3)

    monstre1.exp += 1500.0
    println("Nouveau Niveau: ${monstre1.niveau}, Nouvelle Attaque: ${monstre1.attaque}")

    monstre1.pv += 500
    println("Après +500 PV : ${monstre1.pv} / ${monstre1.pvMax}")

    monstre1.pv -= 1000
    println("Après -1000 PV : ${monstre1.pv} / ${monstre1.pvMax}")
    */
    route1.zoneSuivante = route2
    route2.zonePrecedente = route1

    joueur.sacAItems.add(kube1)

    val partie = nouvellePartie()
    partie.choixStarter(especeSpringleaf, especeFlamkip, especeAquamy)
    partie.jouer()
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