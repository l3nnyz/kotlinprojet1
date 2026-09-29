package org.example.item

import org.example.monstre.IndividuMonstre
import kotlin.random.Random

class MonsterKube(
    id: Int,
    nom: String,
    description: String,
    var chanceCapture: Double
) : Item(id, nom, description), Utilisable {

    override fun utiliser(cible: IndividuMonstre): Boolean {
        val ratioVie = cible.pv.toDouble() / cible.pvMax.toDouble()
        val chanceEffective = (chanceCapture * (1.5 - ratioVie)).coerceAtLeast(0.05)

        val reussite = Random.nextDouble(0.0, 1.0) <= chanceEffective

        if (reussite) {
            println("Capture réussie ! ${cible.nom} a été capturé.")
        } else {
            println("echec de la capture... ${cible.nom} s'est libéré!!!")
        }

        return reussite
    }
}