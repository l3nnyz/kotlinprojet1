package org.example.combat

import org.example.monstre.IndividuMonstre
import org.example.entraineur.joueur // On importe le joueur global ou passe-le en argument selon ton projet

class CombatMonstre(
    var monstreJoueur: IndividuMonstre,
    var monstreSauvage: IndividuMonstre
) {
    var round: Int = 1

    /**
     * Vérifie si le joueur a perdu le combat.
     * Condition de défaite : Aucun monstre de l'équipe du joueur n'a de PV > 0.
     */
    fun gameOver(): Boolean {
        return joueur.equipeMonstre.none { it.pv > 0 }
    }

    /**
     * Indique si le joueur a gagné le combat (monstre sauvage K.O. ou capturé).
     */
    fun joueurGagne(): Boolean {
        // Le joueur gagne si le monstre sauvage n'a plus de PV ou s'il a été capturé (ex: entraineur != null)
        return monstreSauvage.pv <= 0 || monstreSauvage.entraineur == joueur
    }

    /**
     * Action de l'adversaire (attaque si toujours en vie).
     */
    fun actionAdversaire() {
        if (monstreSauvage.pv > 0) {
            monstreSauvage.attaquer(monstreJoueur)
        }
    }

    /**
     * Action du joueur.
     * @return true si le combat continue, false s'il doit s'arrêter (fuite, etc.).
     */
    fun actionJoueur(): Boolean {
        println("\n--- Que voulez-vous faire ? ---")
        println("1 => Attaquer")
        println("2 => Utiliser un item")
        println("3 => Changer de monstre")
        println("4 => Fuir")
        println("5 => Attendre")
        print("Votre choix : ")

        when (readlnOrNull()?.trim()) {
            "1" -> {
                monstreJoueur.attaquer(monstreSauvage)
                if (monstreSauvage.pv <= 0) {
                    println("Le monstre sauvage est K.O. !")
                    // Gain d'expérience
                    val expGagnee = monstreSauvage.niveau * 50.0
                    println("${monstreJoueur.nom} gagne $expGagnee EXP !")
                    monstreJoueur.exp += expGagnee
                }
            }
            "2" -> {
                if (joueur.sacAItems.isEmpty()) {
                    println("Votre sac à items est vide !")
                } else {
                    println("Choisissez un item :")
                    joueur.sacAItems.forEachIndexed { i, item ->
                        println("${i + 1} => ${item.nom}")
                    }
                    val choix = readlnOrNull()?.toIntOrNull()
                    if (choix != null && choix in 1..joueur.sacAItems.size) {
                        val item = joueur.sacAItems[choix - 1]
                        if (item is org.example.item.Utilisable) {
                            val reussite = item.utiliser(monstreSauvage)
                            if (reussite) {
                                joueur.sacAItems.removeAt(choix - 1)
                                if (monstreSauvage.entraineur == joueur) {
                                    joueur.equipeMonstre.add(monstreSauvage)
                                    return false // Le combat s'arrête en cas de capture
                                }
                            }
                        }
                    }
                }
            }
            "3" -> {
                println("Choisissez un monstre :")
                val monstresValides = joueur.equipeMonstre.filter { it.pv > 0 }
                monstresValides.forEachIndexed { i, m ->
                    println("${i + 1} => ${m.nom} (PV: ${m.pv}/${m.pvMax})")
                }
                val choix = readlnOrNull()?.toIntOrNull()
                if (choix != null && choix in 1..monstresValides.size) {
                    monstreJoueur = monstresValides[choix - 1]
                    println("Vous envoyez ${monstreJoueur.nom} au combat !")
                }
            }
            "4" -> {
                println("Vous fuyez le combat !")
                return false
            }
            "5" -> {
                println("Vous attendez sans rien faire...")
            }
            else -> {
                println("Choix invalide.")
            }
        }
        return true
    }

    /**
     * Affiche les détails des deux monstres.
     */
    fun afficheCombat() {
        println("\n========================================")
        println("MONSTRE SAUVAGE : ${monstreSauvage.nom} | PV: ${monstreSauvage.pv}/${monstreSauvage.pvMax}")
        println(monstreSauvage.espece.afficheArt(false))
        println("----------------------------------------")
        println("VOTRE MONSTRE : ${monstreJoueur.nom} | PV: ${monstreJoueur.pv}/${monstreJoueur.pvMax}")
        println(monstreJoueur.espece.afficheArt(true))
        println("========================================")
    }

    /**
     * Tour de jeu (gestion des priorités par vitesse).
     */
    fun jouer() {
        afficheCombat()
        if (monstreJoueur.vitesse >= monstreSauvage.vitesse) {
            val continueCombat = actionJoueur()
            if (!continueCombat) return
            if (!joueurGagne()) actionAdversaire()
        } else {
            actionAdversaire()
            if (!gameOver()) {
                val continueCombat = actionJoueur()
                if (!continueCombat) return
            }
        }
    }

    /**
     * Lance la boucle de combat.
     */
    fun lanceCombat() {
        while (!gameOver() && !joueurGagne()) {
            jouer()
            println("======== Fin du Round : $round ========")
            round++
        }
        if (gameOver()) {
            joueur.equipeMonstre.forEach { it.pv = it.pvMax }
            println("Game Over !")
        } else if (joueurGagne()) {
            println("Victoire !")
        }
    }
}