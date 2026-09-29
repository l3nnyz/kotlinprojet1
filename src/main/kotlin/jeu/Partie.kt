package org.example.jeu

import org.example.dresseur.Entraineur
import org.example.monde.Zone
import org.example.monstre.EspeceMonstre
import org.example.monstre.IndividuMonstre


/**
 * Représente la partie en cours.
 * (Note : Jusqu'à 2 objets Partie peuvent exister simultanément dans le jeu).
 */
class Partie(
    val id: Int,
    val joueur: Entraineur,
    var zone: Zone
) {
    fun choixStarter(s1Espece: EspeceMonstre, s2Espece: EspeceMonstre, s3Espece: EspeceMonstre) {
        println("\n--- Choix de Starter ---")
        val s1 = IndividuMonstre(1, s1Espece.nom, espece = s1Espece)
        val s2 = IndividuMonstre(2, s2Espece.nom, espece = s2Espece)
        val s3 = IndividuMonstre(3, s3Espece.nom, espece = s3Espece)

        println("1 => ${s1.nom}")
        println("2 => ${s2.nom}")
        println("3 => ${s3.nom}")
        print("Choisissez votre monstre de départ : ")

        val choix = when (readlnOrNull()?.trim()) {
            "1" -> s1
            "2" -> s2
            "3" -> s3
            else -> s1
        }

        choix.renommer()
        joueur.equipeMonstre.add(choix)
        println("Vous avez choisi ${choix.nom} !")
    }

    fun modifierOrdreEquipe() {
        if (joueur.equipeMonstre.size <= 1) {
            println("Vous n'avez pas assez de monstres pour modifier l'ordre.")
            return
        }

        println("Emplacement du monstre à déplacer (1 à ${joueur.equipeMonstre.size}) : ")
        val pos1 = (readlnOrNull()?.toIntOrNull() ?: return) - 1

        println("Nouvel emplacement (1 à ${joueur.equipeMonstre.size}) : ")
        val pos2 = (readlnOrNull()?.toIntOrNull() ?: return) - 1

        if (pos1 in joueur.equipeMonstre.indices && pos2 in joueur.equipeMonstre.indices) {
            val temp = joueur.equipeMonstre[pos1]
            joueur.equipeMonstre[pos1] = joueur.equipeMonstre[pos2]
            joueur.equipeMonstre[pos2] = temp
            println("Ordre modifié avec succès.")
        } else {
            println("Positions invalides.")
        }
    }

    fun examineEquipe() {
        while (true) {
            println("\n--- Votre Équipe ---")
            joueur.equipeMonstre.forEachIndexed { i, m ->
                println("${i + 1} => ${m.nom} (Niv. ${m.niveau} | PV: ${m.pv}/${m.pvMax})")
            }
            println("\nTapez le numéro d'un monstre pour voir le détail, 'm' pour modifier l'ordre, 'q' pour quitter.")
            print("Choix : ")
            val input = readlnOrNull()?.trim() ?: "q"

            when (input) {
                "q" -> break
                "m" -> modifierOrdreEquipe()
                else -> {
                    val index = input.toIntOrNull()?.minus(1)
                    if (index != null && index in joueur.equipeMonstre.indices) {
                        joueur.equipeMonstre[index].afficheDetail()
                    }
                }
            }
        }
    }

    fun jouer() {
        while (true) {
            println("\n========================================")
            println("Zone actuelle : ${zone.nom}")
            println("1 => Rencontrer un monstre sauvage")
            println("2 => Examiner l'équipe de monstres")
            println("3 => Aller à la zone suivante")
            println("4 => Aller à la zone précédente")
            print("Votre choix : ")

            when (readlnOrNull()?.trim()) {
                "1" -> {
                    zone.rencontreMonstre()
                }
                "2" ->  {
                    examineEquipe()
                }
                "3" -> {
                    if (zone.zoneSuivante != null) {
                        zone = zone.zoneSuivante!!
                        println("Vous vous déplacez vers : ${zone.nom}")
                    } else {
                        println("Impossible, aucune zone suivante !")
                    }
                }
                "4" -> {
                    if (zone.zonePrecedente != null) {
                        zone = zone.zonePrecedente!!
                        println("Vous vous déplacez vers : ${zone.nom}")
                    } else {
                        println("Impossible, aucune zone précédente !")
                    }
                }
                else ->  {
                    println("Choix invalide.")
                }
            }
        }
    }
}