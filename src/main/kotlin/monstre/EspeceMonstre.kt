package org.example.monstre

import java.io.File
/**
 * Représente une espèce de monstre (et non un individu spécifique).
 *
 * Cette classe contient les caractéristiques de base, les modificateurs de croissance
 * ainsi que les données descriptives communes à tous les individus appartenant à cette espèce.
 *
 * @property id Identifiant unique de l'espèce de monstre.
 * @property nom Nom générique de l'espèce (ex: "Springleaf", "Canaros").
 * @property type Type élémentaire ou catégorie du monstre déterminant ses affinités.
 *
 * @property baseAttaque Score de base pour l'attaque physique (Atq) au niveau initial.
 * @property baseDefense Score de base pour la défense physique (Def) au niveau initial.
 * @property baseVitesse Score de base pour la vitesse au niveau initial, déterminant l'ordre d'action en combat.
 * @property baseAttaqueSpe Score de base pour l'attaque spéciale (Atq Spe).
 * @property baseDefenseSpe Score de base pour la défense spéciale (Def Spe) contre les attaques spéciales.
 * @property basePv Points de vie (PV) de base maximums d'un monstre de cette espèce au niveau initial.
 *
 * @property modAttaque Modificateur de croissance appliqué lors d'une montée de niveau pour recalculer l'attaque.
 * @property modDefense Modificateur de croissance appliqué lors d'une montée de niveau pour recalculer la défense.
 * @property modVitesse Modificateur de croissance appliqué lors d'une montée de niveau pour recalculer la vitesse.
 * @property modAttaqueSpe Modificateur de croissance appliqué lors d'une montée de niveau pour recalculer l'attaque spéciale.
 * @property modDefenseSpe Modificateur de croissance appliqué lors d'une montée de niveau pour recalculer la défense spéciale.
 * @property modPv Modificateur de croissance appliqué lors d'une montée de niveau pour recalculer les PV max.
 *
 * @property description Texte descriptif décrivant le comportement ou l'origine de l'espèce (255 caractères max).
 * @property particularites Particularités physiques ou éléments notables de l'espèce.
 * @property caractères Traits de caractère ou comportement prédominant de l'espèce.
 */
class EspeceMonstre(
    var id: Int,
    var nom: String,
    var type: String,
    val baseAttaque: Int,
    val baseDefense: Int,
    val baseVitesse: Int,
    val baseAttaqueSpe: Int,
    val baseDefenseSpe: Int,
    val basePv: Int,
    val modAttaque: Double,
    val modDefense: Double,
    val modVitesse: Double,
    val modAttaqueSpe: Double,
    val modDefenseSpe: Double,
    val modPv: Double,
    val description: String = "",
    val particularites: String = "",
    val caractères: String = ""
) {

    /**
     * Affiche la représentation artistique ASCII du monstre.
     *
     * @param deFace Détermine si l'art affiché est de face (true) ou de dos (false).
     *               La valeur par défaut est true.
     * @return Une chaîne de caractères contenant l'art ASCII du monstre avec les codes couleur ANSI.
     *         L'art est lu à partir d'un fichier texte dans le dossier resources/art.
     */
    fun afficheArt(deFace: Boolean = true): String {
        val nomFichier = if (deFace) "front" else "back"
        val art = File("src/main/resources/art/${this.nom.lowercase()}/$nomFichier.txt").readText()
        val safeArt = art.replace("/", "∕")
        return safeArt.replace("\\u001B", "\u001B")
    }
}