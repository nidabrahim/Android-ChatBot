package com.youssefnida.winou.ChatResponses;

/**
 * Created by youssefNIDA on 31/03/2017.
 */

public interface PossibleResponses {

    /******************************
     * les salutations possible
     ******************************/

     String [] randomSalutation = {
            "Salut, ça va ?"
            ,"Salut ,cv ?"
            ,"slt comment tu va ?"
            ,"bonjour ,quoi de neuf aujourd'hui ?"
            ,"bjr, ca baigne ?"
            ,"bonjour, comment était ta journée ? raconte moi stp !"
            ,"je suis de mauvaise humeur aujourd'hui , pourquoi me derange tu ?"
            ,"Salut"
            ,"bonjour"
            ,"salut ,je cherchais à te parler aujourd'hui"
    };

    /***************************************
     * les reponses possible de message vide
     ***************************************/

     String [] randomReponse_du_vide = {
            "tu n'as rien dis ! je te ecoute encore!"
            ,"je suis deçu parce que vous m'envoyer un message vide, ecrivez quelque-chose !"
            ,"c'est pas grave ! tu peux reecrit "
            ,"comme tu ne veux rien dire , veux tu que je te parle de ma journée ?"
    };

    /*******************************
     * les reponses aleatoire
     *******************************/

    String [] randomReponses = {"Interessant, dit moi autant",
            "Hmmm.",
            "Tu pense reellement ça?",
            "Ne me dit pas."
    };


}
