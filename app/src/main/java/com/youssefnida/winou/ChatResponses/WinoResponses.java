package com.youssefnida.winou.ChatResponses;

import org.joda.time.DateTime;

import java.util.Random;

/**
 * Created by youssefNIDA on 31/03/2017.
 */

public class WinoResponses {

    private static final String NOMBOT = "Winou";

    private boolean devinette_active = false;
    private String rep_devinette = "";

    //CA VA ?
    private String [] token_cava = {
            "ça va ?"
            ,"comment ça va ?"
            ,"ça marche ?"
            ,"ça roule ?"
            ,"tu vas bien ?"
            ,"comment vas-tu aujourd'hui ?"
    };

    //BONNE JOURNEE
    private String [] token_bn_journee = {
            "c'est beau aujourd'hui"
            ,"c'est une bonne journée"
    };

    //MON AMI
    private String [] token_mon_ami = {
            "mon pote"
            ,"mon cher ami"
            ,"frère"
    };

    //LISTE DES SYNONYMES DE BONJOUR
    private String [] token_bj = {
            "bonjour"
            ,"bonsoir"
            ,"salut"
            ,"slt"
    };

    //REPONSES POSITIVE DE CA VA ?
    private String [] token_cvbien = {
            "ca va bien merci"
            ,"je suis content"
            ,"super bien"
    };

    //REPONSES NEGATIVE DE CA VA ?
    private String [] token_cvmal = {
            "non ça marche pas bien ces journées"
            ,"je me sens mal"
            ,"ça ne va pas"
    };

    //LISTE DE SYNONYMES DE QUOI DE NEUF
    private String [] token_qdneuf = {
            "quoi de neuf ?"
            ,"tu raconte quoi ?"
            ,"c'est quoi des nouveautés ?"
    };

    //REPONSES POSSIBLE DE QUOI DE NEUF
    private String [] token_rep_qdneuf = {
            "j'ai bcp aimé tes photos"
            ,"laisse tomber y'a rien de spécial , je vais te raconter une blague"
            ,"tu sais tu m'a manqué aujourd'hui ?"
    };

    //REPONSES POSSIBLE DE CA VA MAL
    private String [] token_rep_cvmal = {
            "pourquoi ?"
            ,"qui ta rendu comme ça ?"
            ,"c'est triste d'entendre ça"
            ,"c'est qui celui là qui a rendu "+ get_token_mon_ami() +" comme ça ?"
    };

    //REPONSES DROLE
    private String [] token_drole = {
            "tes dents sont comme des étoiles ... si jaunes et si distantes les unes des autres"
    };


    //DEMANDE BLAGUES
    private String [] token_dmd_blagues = {
            "blague"
            ,"blagues"
            ,"fait moi rire"
    };

    //BLAGUES
    private String [] token_blagues = {
            "Une poule sort de son poulailler. Elle se plaint 'Quel froid de canard!' A ce moment, un canard passe et dit 'Tu as raison, j'ai la chair de poule' "
            ,"blagues"
            ,"fait moi rire"
    };

    //DEMANDE DEVINETTE
    private String [] token_dmd_devinette = {
            "lance un jeu"
            ,"donne moi une devinette"
    };

    //DEVINETTES
    private String [] token_devinettes = {
            "Mon premier est dans la bouche, mon second est une lettre de l'alphabet, mon tout est un verbe à l'infinitif"
            ,"J'ai quatre yeux, quatre oreilles et six pates. Qui suis-je?"
    };

    //REPONSE DEVINETTES
    private String [] token_rep_devinettes = {
            "Danser ( dent-c )"
            ,"Un cheval et son cavalier"
    };

    //SORTIR DEVINETTES
    private String [] token_stop_devinettes = {
            "arrete les devinettes"
            ,"stop le jeu"
    };

    //RECUPERER DEVINETTES
    public String get_token_devinettes()
    {
        Random r = new Random ();
        int i = r.nextInt(token_devinettes.length);
        rep_devinette = token_rep_devinettes [r.nextInt(i)];
        return token_devinettes [r.nextInt(i)];
    }

    //ENCORE
    private String [] token_encore = {
            "encore",
            "encore une autre"
    };

    //AUTRES
    private String [] token_autres = {
            "Hmmm.",
            "Tu pense reellement ça?",
            "Ne me dit pas."
    };

    //RECUPERER UNE EXPRESSION SYNONYME DE CA VA?
    public String get_token_cava()
    {
        Random r = new Random ();
        return token_cava [r.nextInt(token_cava.length)];
    }

    //RECUPERER UNE EXPRESSION SYNONYME DE BONNE JOURNEE
    public String get_token_bn_journee()
    {
        Random r = new Random ();
        return token_bn_journee [r.nextInt(token_bn_journee.length)];
    }

    //RECUPERER UNE EXPRESSION SYNONYME DE MON AMI
    public String get_token_mon_ami()
    {
        Random r = new Random ();
        return token_mon_ami [r.nextInt(token_mon_ami.length)];
    }

    //RECUPERER UNE EXPRESSION SYNONYME DE BONJOUR
    public String get_token_bj()
    {
        Random r = new Random ();
        return token_bj [r.nextInt(token_bj.length)];
    }

    //RECUPERER UNE EXPRESSION SYNONYME DE CA VA BIEN
    public String get_token_cvbien()
    {
        Random r = new Random ();
        return token_cvbien [r.nextInt(token_cvbien.length)];
    }

    //RECUPERER UNE EXPRESSION SYNONYME DE CA VA MAL
    public String get_token_cvmal()
    {
        Random r = new Random ();
        return token_cvmal [r.nextInt(token_cvmal.length)];
    }

    //RECUPERER UNE EXPRESSION SYNONYME DE QUOI DE NEUF
    public String get_token_qdneuf()
    {
        Random r = new Random ();
        return token_qdneuf [r.nextInt(token_qdneuf.length)];
    }

    //RECUPERER UNE REPONSE DE QUOI DE NEUF
    public String get_token_rep_qdneuf()
    {
        Random r = new Random ();
        return token_rep_qdneuf [r.nextInt(token_rep_qdneuf.length)];
    }

    //RECUPERER UNE REPONSE DE CA VA MAL
    public String get_token_rep_cvmal()
    {
        Random r = new Random ();
        return token_rep_cvmal [r.nextInt(token_rep_cvmal.length)];
    }

    //RECUPERER UNE REPONSE DROLE
    public String get_token_drole()
    {
        Random r = new Random ();
        return token_drole [r.nextInt(token_drole.length)];
    }

    //RECUPERER UNE REPONSE DROLE
    public String get_token_autres()
    {
        Random r = new Random ();
        return token_autres [r.nextInt(token_autres.length)];
    }

    //RECUPERER UNE BLAGUE
    public String get_token_blagues(){

        Random r = new Random ();
        return token_blagues [r.nextInt(token_blagues.length)];
    }

    //GET SALUT SELON LE TEMPS
    public String getSalut(){

        String salut = "";

        DateTime dt = new DateTime();
        int hours = dt.getHourOfDay(); // gets hour of day

        if( hours <= 12 ) salut = "Bonjour";
        else if(12 < hours  && hours <= 18 ) salut = "Bon après midi";
        else if( 18 < hours ) salut = "Bonsoir";

        return salut;
    }

    //GET FIRST CONTENT MESSAGE
    public String getFirstContentMsg(){

        String msg = "";
        Random r = new Random ();
        int rndm = r.nextInt(2);

        switch(rndm){
            case 0: msg = get_token_cava() + " " + get_token_mon_ami(); break;
            case 1: msg = get_token_bn_journee(); break;

        }

        return msg;
    }

    /*********************
    /* GET FIRST MESSAGE */
    /*********************/
    public String getFirstMsg(){

        return getSalut() + ", " + getFirstContentMsg();
    }

    private boolean contenir(String[] tab, String mot){

        boolean c = false;

        int i =0;
        while( i < tab.length && (!tab[i].equals(mot)) ) i++;
        if( i < tab.length ) c = true;

        return c;
    }

    /**********************
     /*     GET REPONSE   */
    /**********************/
    public String getResponse(String message) {

        String reponse = "";


        if( contenir(token_stop_devinettes,message) ){

            //DESACTIVER LE JEU DEVINETTE
            devinette_active = false;
            reponse = "Ok";
        }
        else
        if( devinette_active == true ){

            if( contenir(token_encore,message)  ){
                //ENVOYER UNE DEVINETTE
                reponse = get_token_devinettes();
            }
            else
            if( message.equals(rep_devinette) ) reponse = "Bravo, ci ça";
            else reponse = "Non, c'est "+ rep_devinette;
        }
        else
        //MESSAGE CONTIENT SEULEMENT <TOKEN_BJ>
        if( contenir(token_bj,message) )
        {
            //RANDOM : <CA VA> ou <QDNEUF>
            Random r = new Random ();
            int rndm = r.nextInt(2);
            switch(rndm){
                case 0: reponse = get_token_cava(); break;      //CA VA ?
                case 1: reponse = get_token_qdneuf(); break;    //QUOI DE NEUF ?
            }
        }
        else
        if( contenir(token_cava,message) )
        {
            //RANDOM : <CV BIEN> ou <CV MAL>
            Random r = new Random ();
            int rndm = r.nextInt(2);
            switch(rndm){
                case 0: reponse = get_token_cvbien(); break;      //CA VA BIEN
                case 1: reponse = get_token_cvmal(); break;      //CA VA MAL ?
            }
        }
        else
        if( contenir(token_qdneuf,message) ) reponse = get_token_rep_qdneuf();
        else
        if( contenir(token_cvmal,message) ) reponse = get_token_rep_cvmal();
        else
        if( contenir(token_cvbien,message) ) reponse = get_token_drole();
        else
        if( message.contains("date") ){
            DateTime dt = new DateTime();
            reponse = "Aujourd'hui est " + dt.getDayOfMonth()+"/"+dt.getMonthOfYear()+"/"+dt.getYear();
        }
        else
        if( message.contains("heure") ){
            DateTime dt = new DateTime();
            reponse = "Maintenant c'est " + dt.getHourOfDay()+":"+dt.getMonthOfYear();
        }
        else
        if( contenir(token_dmd_blagues,message) ) reponse = get_token_blagues();
        else
        if( contenir(token_dmd_devinette,message) ) {

            //ACTIVER LE JEU DEVINETTE
            devinette_active = true;
            //ENVOYER UNE DEVINETTE
            reponse = get_token_devinettes();
        }
        else
            reponse = get_token_autres();

        return reponse;

    }





    /******************************
     * les salutations possible
     ******************************/

    private String [] randomSalutation = {
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

    private String [] randomReponse_du_vide = {
            "tu n'as rien dis ! je te ecoute encore!"
            ,"je suis deçu parce que vous m'envoyer un message vide, ecrivez quelque-chose !"
            ,"c'est pas grave ! tu peux reecrit "
            ,"comme tu ne veux rien dire , veux tu que je te parle de ma journée ?"
    };

    /****************************
     * message d'accueill
     * @return une salutation
     ****************************/

    public String getSalutation()
    {
        Random r = new Random ();
        return randomSalutation [r.nextInt(randomSalutation.length)];
    }

    /*********************************************************
     * Repondre a un message
     * @param message_utilisateur utilisateur
     * @return un reponse baser le sens qu'on donne au message
     **********************************************************/

    public String getReponse(String message_utilisateur)
    {
        String reponse = "";
        if (message_utilisateur.length() == 0)
        {
            Random r = new Random ();
            reponse = randomReponse_du_vide[r.nextInt(randomReponse_du_vide.length)];;
        }

        else if (trouverMotCle(message_utilisateur, "non") >= 0)
        {
            reponse = "pourquoi vous etes si negative";
        }
        else if (trouverMotCle(message_utilisateur, "maman") >= 0
                || trouverMotCle(message_utilisateur, "papa") >= 0
                || trouverMotCle(message_utilisateur, "pere") >= 0
                || trouverMotCle(message_utilisateur, "mere") >= 0)
        {
            reponse = "Parle moi un peu de ta famille stp!";
        }

        // reponse si on trouve "je veux que"
        else if (trouverMotCle(message_utilisateur, "je veux que", 0) >= 0)
        {
            reponse = transformJeVeuxQueMessage(message_utilisateur);
        }
        // reponse si on trouve "je veux"
        else if (trouverMotCle(message_utilisateur, "je veux", 0) >= 0)
        {
            reponse = transformJeVeuxMessage(message_utilisateur);
        }

        else
        {
            //A supprimer
            reponse = getAleatoireReponse();

            // reponse a la phrase contenant "tu" et "moi"<tu 'quelque-chose' moi>
           /* int psn = trouverMotCle(message_utilisateur, "tu", 0);

            if (psn >= 0 && trouverMotCle(message_utilisateur, "moi", psn) >= 0)
            {
                reponse = transformTuMoiMessage(message_utilisateur);
            }
            else
            {
                // reponse a la phrase contenant "je" et "toi"<je 'quelque-chose' toi>
                psn = trouverMotCle(message_utilisateur, "je", 0);

                if (psn >= 0
                        && trouverMotCle(message_utilisateur, "toi", psn) >= 0)
                {
                    reponse = transformJeToiMessage(message_utilisateur);
                }
                else
                {
                    reponse = getAleatoireReponse();
                }
            }*/
        }
        return reponse;
    }

    /*********************************************************
     * Repondre a un message du genre "je veux que
     * @param message_utilisateur utilisateur
     * @return un reponse baser le sens qu'on donne au message
     **********************************************************/

    private String transformJeVeuxQueMessage(String message_utilisateur)
    {

        message_utilisateur = message_utilisateur.trim();
        String derniere_lettre = message_utilisateur.substring(message_utilisateur.length() - 1);
        if (derniere_lettre.equals("."))
        {
            message_utilisateur = message_utilisateur.substring(0, message_utilisateur.length() - 1);
        }
        int psn = trouverMotCle (message_utilisateur, "je veux que", 0);
        String reste_du_message_utilisateur = message_utilisateur.substring(psn + 11).trim();
        return "tu voudrais vraiment que " + reste_du_message_utilisateur + "?";
    }

    /*********************************************************
     * Repondre a un message du genre "je veux que
     * @param message_utilisateur utilisateur
     * @return un reponse baser le sens qu'on donne au message
     **********************************************************/

    private String transformJeVeuxMessage(String message_utilisateur)
    {

        message_utilisateur = message_utilisateur.trim();
        String derniere_lettre = message_utilisateur.substring(message_utilisateur.length() - 1);
        if (derniere_lettre.equals("."))
        {
            message_utilisateur = message_utilisateur.substring(0, message_utilisateur.length() - 1);
        }
        int psn = trouverMotCle (message_utilisateur, "je veux que", 0);
        String reste_du_message_utilisateur = message_utilisateur.substring(psn + 11).trim();
        return "tu voudrais vraiment que " + reste_du_message_utilisateur + "?";
    }

    /*********************************************************
     * Trouver notre mot clé dans la phrse de l'utilisateur
     * @param message_utilisateur utilisateur,mot clé(but),debutPosition
     * @return la position de notre but(entier si oui ,sinon -1)
     **********************************************************/

    private int trouverMotCle(String message_utilisateur, String but,int debutPosition)
    {
        String phrase = message_utilisateur.trim().toLowerCase();
        but = but.toLowerCase();

        //chercher la position de notre but

        int psn = phrase.indexOf(but, debutPosition);

        // rafiner pour etre sure

        while (psn >= 0)
        {
            // trouver le mot de longueur 1 avant et apres un mot

            String avant = " ", apres = " ";
            if (psn > 0)
            {
                avant = phrase.substring(psn - 1, psn);
            }
            if (psn + but.length() < phrase.length())
            {
                apres = phrase.substring(psn + but.length(),psn + but.length() + 1);
            }

            // si avant et apres ne sont pas des lettres alors ,on a trouver le mot(but)
            if (((avant.compareTo("a") < 0) || (avant.compareTo("z") > 0))
                    && ((apres.compareTo("a") < 0) || (apres.compareTo("z") > 0)))
            {
                return psn;
            }

            //chercher une nouvelle occurence de notre but
            psn = phrase.indexOf(but, psn + 1);

        }
        return -1;
    }

    /*********************************************************
     * Trouver notre mot clé dans la phrse de l'utilisateur
     * @param message_utilisateur utilisateur,mot clé(but)
     * @return la position de notre but(entier si oui ,sinon -1)
     **********************************************************/

    private int trouverMotCle(String message_utilisateur, String mot_cle)
    {
        return trouverMotCle(message_utilisateur, mot_cle, 0);
    }

    /*********************************************************
     * repondre par une phrase aleatoire
     * @return reponse aleatoire
     **********************************************************/
    private String getAleatoireReponse()
    {
        Random r = new Random ();
        return randomReponses [r.nextInt(randomReponses.length)];
    }

    private String [] randomReponses = {"Interessant, dit moi autant",
            "Hmmm.",
            "Tu pense reellement ça?",
            "Ne me dit pas."
    };


    public WinoResponses(){}

}
