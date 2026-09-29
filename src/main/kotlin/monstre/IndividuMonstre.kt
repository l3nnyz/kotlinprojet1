package org.example.monstre

import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.random.Random

/**
 * Cette classe représente chaque individu monstre avec lequel le joueur ou un entraîneur peut interagir.
 * Deux individus peuvent appartenir à la même espèce (ex: deux Canaros distincts).
 *
 * @property id Identifiant unique de l'individu.
 * @property nom Nom attribué à l'individu.
 * @property espece Espèce d'origine du monstre.
 * @property entraineur Entraîneur auquel appartient le monstre (null s'il est sauvage).
 */
class IndividuMonstre(
    var id: Int,
    var nom: String,
    val espece: EspeceMonstre,
    var entraineur: Any? = null, // Remplacer Any? par Entraineur? si la classe existe dans votre projet
    expInit: Double = 0.0
) {

    var niveau: Int = 1
    var attaque: Int = espece.baseAttaque + Random.nextInt(-2, 3)
    var defense: Int = espece.baseDefense + Random.nextInt(-2, 3)
    var vitesse: Int = espece.baseVitesse + Random.nextInt(-2, 3)
    var attaqueSpe: Int = espece.baseAttaqueSpe + Random.nextInt(-2, 3)
    var defenseSpe: Int = espece.baseDefenseSpe + Random.nextInt(-2, 3)
    var pvMax: Int = espece.basePv + Random.nextInt(-5, 6)

    val potentiel: Double = Random.nextDouble(0.5, 2.0)

    /**
     * Points de vie actuels du monstre.
     * Ne peut pas être inférieur à 0 ni supérieur à [pvMax].
     */
    var pv: Int = pvMax
        get() = field
        set(nouveauPv) {
            field = nouveauPv.coerceIn(0, pvMax)
        }

    /**
     * Expérience cumulée du monstre.
     * Déclenche un ou plusieurs levelUp() lors du dépassement du palier d'expérience du niveau suivant.
     */
    var exp: Double = 0.0
        get() = field
        set(nouvelleExp) {
            field = nouvelleExp
            while (field >= palierExp(niveau + 1)) {
                levelUp()
            }
        }

    init {
        this.exp = expInit // Applique le setter et déclenche un éventuel level-up initial
    }

    /**
     * Calcule l'expérience totale nécessaire pour atteindre un niveau donné.
     *
     * @param niveau Niveau cible.
     * @return Expérience cumulée nécessaire pour atteindre ce niveau.
     */
    fun palierExp(niveau: Int): Double {
        return 100.0 * (niveau - 1.0).pow(2.0)
    }

    /**
     * Augmente le niveau du monstre et recalcule ses caractéristiques.
     */
    fun levelUp() {
        niveau++

        val gainAttaque = (espece.modAttaque * potentiel).roundToInt() + Random.nextInt(-2, 3)
        val gainDefense = (espece.modDefense * potentiel).roundToInt() + Random.nextInt(-2, 3)
        val gainVitesse = (espece.modVitesse * potentiel).roundToInt() + Random.nextInt(-2, 3)
        val gainAttaqueSpe = (espece.modAttaqueSpe * potentiel).roundToInt() + Random.nextInt(-2, 3)
        val gainDefenseSpe = (espece.modDefenseSpe * potentiel).roundToInt() + Random.nextInt(-2, 3)
        val gainPvMax = (espece.modPv * potentiel).roundToInt() + Random.nextInt(-5, 6)

        this.attaque += gainAttaque
        this.defense += gainDefense
        this.vitesse += gainVitesse
        this.attaqueSpe += gainAttaqueSpe
        this.defenseSpe += gainDefenseSpe

        this.pvMax += gainPvMax
        this.pv += gainPvMax // Augmente les PV actuels du nombre de PV Max gagnés

        println("$nom monte au niveau$niveau !")
    }

    /**
     * Attaque un autre [IndividuMonstre] et inflige des dégâts.
     * Dégâts calculés : `dégâts = attaque - (défense / 2)` (minimum 1 dégât).
     *
     * @param cible Monstre cible de l'attaque.
     */
    fun attaquer(cible: IndividuMonstre) {
        val degats = (this.attaque - (cible.defense / 2)).coerceAtLeast(1)
        cible.pv -= degats
        println("$nom attaque ${cible.nom} et lui inflige$degats degâts !")
    }

    /**
     * Demande au joueur de renommer le monstre via la console.
     * Si l'utilisateur entre un texte vide, le nom n'est pas modifié.
     */
    fun renommer() {
        print("Entrez le nouveau nom pour $nom (laisser vide pour pas changer) : ")
        val nouveauNom = readlnOrNull()?.trim()
        if (!nouveauNom.isNullOrEmpty()) {
            println("$nom est renommé en $nouveauNom.")
            this.nom = nouveauNom
        } else {
            println("Nom inchangé.")
        }
    }

    /**
     * Affiche les caractéristiques détaillées du monstre ainsi que son visuel ASCII art.
     */
    fun afficheDetail() {
        println("========================================")
        println("DÉTAILS DU MONSTRE : $nom (${espece.nom})")
        println("Niveau : $niveau | EXP : $exp / ${palierExp(niveau + 1)}")
        println("PV : $pv / $pvMax")
        println("Attaque : $attaque \\vert{} Défense : $defense")
        println("Attaque Spé : $attaqueSpe | Défense Spé : $defenseSpe")
        println("Vitesse : $vitesse \\vert{} Potentiel :$potentiel")
        println("========================================")
        println(espece.afficheArt(true))
    }
}