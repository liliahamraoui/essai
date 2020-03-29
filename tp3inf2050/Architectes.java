/**
 * This software validates continuing education activity declarations for members of a professional order
 * Copyright (C) 2020 Équipe 8 INF2050
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class Architectes extends  Validation{

    public static final Date [] DATE_CYCLE1 ={formaterDate("2014-04-1"), formaterDate("2016-07-1")};
    public static final Date [] DATE_CYCLE2 ={formaterDate("2016-04-1"), formaterDate("2018-04-1")};
    public static final Date [] DATE_CYCLE3 ={formaterDate("2018-04-1"), formaterDate("2020-04-1")};

    private static String[] TAB_CYCLE_VALIDES = { "2014-2016","2016-2018","2018-2020"};
    public static int heuresTr=0;
    private static String cycleUtiliser="";
    private static Date dateDebutCycle=null;
    private static Date dateFinCycle=null;
    public static  int nbrHeuresValiderArchitectes=0;

    public static final String[] CATEGORIE_PRIORITAIRE = {
            "cours", "atelier", "séminaire", "colloque",
            "conférence", "lecture dirigée"
    };


    /**
     * Cette methode sert a initialiser les attributs de la class Architecte
     */
    public static void initialiserVariableCycle(){

        if(cycleUtiliser.equals(TAB_CYCLE_VALIDES[0])||cycleUtiliser.equals(TAB_CYCLE_VALIDES[1])){
            nbrHeuresValiderArchitectes=42;

            if(cycleUtiliser.equals(TAB_CYCLE_VALIDES[0])){
                dateDebutCycle= DATE_CYCLE1[0];
                dateFinCycle= DATE_CYCLE1[1];
            }else {
                dateDebutCycle= DATE_CYCLE2[0];
                dateFinCycle= DATE_CYCLE2[1];
            }
        }else{
            nbrHeuresValiderArchitectes=40;
            dateDebutCycle= DATE_CYCLE3[0];
            dateFinCycle= DATE_CYCLE3[1];
        }
    }


    /**
     * Cette methode prend en parametre un objets de type declaration et verifie si le cycle
     * est valide
     * @param declaration ...  la declaration contenant le cycle
     * @return vrai ou faux ... si le cycle est correct  vrai si non pas
     */
    public static boolean validerCycle(Declaration declaration){
        boolean estValide=false;

        String cycle=declaration.getCycle();
        for (String cycleValide : TAB_CYCLE_VALIDES) {
            if (cycle.equals(cycleValide)){
                estValide=true;
                cycleUtiliser=cycleValide;
            }
        }
        if(estValide==false){
            tabErr.add(MESSAGE_ERR_CYCLE);
        }
        return estValide;
    }


    /**
     * Cette methode prend en parametre un Arrayliste d'activités et calcule la somme totale des heures de
     * formation prioritaires
     * @param activites ...  Arrayliste contenant les activités
     * @return vrai ou faux  ... retourne vrai si la somme est plus grande que 17 ,faux si non
     */
    public static boolean validerHrFormationPrio(ArrayList<Activite> activites){

        String heursPrioritaire="";
        boolean estValide=false;
        String cat="";
        Integer heures=0;

        for (int i = 0; i <activites.size() ; i++) {
            cat=activites.get(i).getCategorie();
            for (int j = 0; j < CATEGORIE_PRIORITAIRE.length; j++) {
                if (CATEGORIE_PRIORITAIRE[j].equals(cat)){
                    cat=activites.get(i).getHeures();
                    heures=heures+Integer.parseInt(cat);
                }
            }
        }

        if (heures+heuresTr>17){
            estValide=true;
        }else{
            tabErr.add(MESSAGE_ERR_NBR_FORMATION_PRIO.replaceAll("%HH%",(17-heures)+"")) ;
        }
        return estValide;
    }


    /**
     * Cette méthode prend en paramètre un objet de type déclaration et vérifie si le nombre d'heure transférée du
     * cycle précédent est plus ou moin egale a 7 .
     * @param declaration ...  la declaration
     * @return vrai ou faux ... retourne faux si le nombre d'heure transférée du cycle précédent est plus de 7 et vrai si non
     */
    public static boolean validerheursTr(Declaration declaration){
        boolean estValide=false;
        heuresTr= (int) Double.parseDouble(
                declaration.getHeuresTransfereesDuCyclePrecedent());
        if (heuresTr >=0 && heuresTr<=7){
            estValide=true;
        }else if (heuresTr>7){
            heuresTr=7;
            tabErr.add(MESSAGE_ERR_NBR_TRANS);
        }
        return estValide;
    }

    /**
     * Cette methode prend en parametre un Arrayliste d'activités et retourne les activités en mettant à jour le nombre
     * d'heures si besoin dans un autre Arrayliste
     * @param activites ...  Arraylist contenant les activités
     * @return activites ... Arraylist contenant les activités mise à jours
     */
    public static ArrayList<Activite> validerMaximunAct(ArrayList<Activite> activites){

        String [] tabCatArc={"présentation","groupe de discussion","projet de recherche","rédaction professionnelle"};
        Validation.validerMaxCatAct(activites,tabCatArc[0],23);
        Validation.validerMaxCatAct(activites,tabCatArc[1],17);
        Validation.validerMaxCatAct(activites,tabCatArc[2],23);
        Validation.validerMaxCatAct(activites,tabCatArc[3],17);

        for (int i = 0; i <activites.size() ; i++) {
            Activite activite=activites.get(i);
            String cat=activite.getCategorie();
            Integer heure=0;

        }
        return activites;
    }


    /**
     * Cette méthode sert a valider la declaration d'un Architecte
     * @throws ParseException
     */
    public static void  validerArchitectes () throws ParseException {

        if (validerCycle(declaration) && validerDescription(activites) && validerNumeroPermis()&&
                validerFormatDate(activites)){

            initialiserVariableCycle();
            validerheursTr(declaration);
            validerHrFormationPrio(activites);
            activites = Validation.validerDate(activites,dateDebutCycle,dateFinCycle);
            activites = Validation.validerCatégorie(activites);
            activites = validerMaximunAct(activites);

            if (Validation.validerHrFormation((afficherNbrHrFormation(activites)+heuresTr),nbrHeuresValiderArchitectes)){
                declarationComplet=true;
            }
        }




    }

}
