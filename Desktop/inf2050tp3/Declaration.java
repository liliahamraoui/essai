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

import java.util.ArrayList;

public class Declaration {



    protected  String ordre;
    protected  String numeroDePermis;
    protected  String cycle;
    protected  String heuresTransfereesDuCyclePrecedent;
    protected  ArrayList<Activite> activites;


    public Declaration (String ordre, String numeroDePermis, String cycle,
                        String heuresTransfereesDuCyclePrecedent, ArrayList<Activite> activites) {

	    this.ordre=ordre;
        this.numeroDePermis = numeroDePermis;
        this.cycle = cycle;
        this.heuresTransfereesDuCyclePrecedent = heuresTransfereesDuCyclePrecedent;
        this.activites = activites;
    }
	
    public String getOrdre() {
        return ordre;
    }

    public String getNumeroDePermis() {
        return numeroDePermis;
    }

    public String getCycle() {
        return cycle;
    }

    public String getHeuresTransfereesDuCyclePrecedent() {
        return heuresTransfereesDuCyclePrecedent;
    }

    public ArrayList<Activite> getActivites() {
        return activites;
    }

    @Override
    public String toString() {
        return "Declaration :\n " +
                "     numero_de_permis = " + numeroDePermis + "\n" +
                "      cycle = " + cycle + "\n" +
                "      heures_transferees_du_cycle_precedent = " + heuresTransfereesDuCyclePrecedent + "\n" +
                "      activites = " + activites +"\n" ;
    }
}
