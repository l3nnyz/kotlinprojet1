package org.example.monde

import org.example.combat.CombatMonstre
import org.example.monstre.EspeceMonstre
import org.example.monstre.IndividuMonstre
import kotlin.random.Random

class Zone(
    val id: Int,
    val nom: String,
    val expZone: Int,
    val especesMonstres: MutableList<EspeceMonstre> = mutableListOf(),
    var zoneSuivante: Zone? = null,
    var zonePrecedente: Zone? = null
) {
    fun genereMonstre(): IndividuMonstre {
        val especeChoisie = especesMonstres.random()
        val variation = Random.nextDouble(0.8, 1.2) // +/- 20%
        val expAjustee = expZone * variation
        return IndividuMonstre(
            id = Random.nextInt(100, 999),
            nom = especeChoisie.nom,
            espece = especeChoisie,
            expInit = expAjustee
        )
    }

    fun rencontreMonstre() {
        val monstreSauvage = genereMonstre()
        println("\nUn ${monstreSauvage.nom} sauvage apparaît !")

        val premierMonstreValide = joueur.equipeMonstre.firstOrNull { it.pv > 0 }
        if (premierMonstreValide != null) {
            val combat = CombatMonstre(premierMonstreValide, monstreSauvage)
            combat.lanceCombat()
        } else {
            println("Tous vos monstres sont K.O. ! Impossible de combattre.")
        }
    }
}