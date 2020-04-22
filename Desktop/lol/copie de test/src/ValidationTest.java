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

import static org.junit.jupiter.api.Assertions.*;

import jdk.jfr.StackTrace;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class ValidationTest {
    ArrayList<Activite> activites=new ArrayList<>();
    Activite activite1=new Activite("Cours sur la chimieee", "cours", "14", "2019-04-20");
    Activite activite2=new Activite("Cours sur l'informatique", "cours", "20", "2019-04-20");
    Declaration declaration1= new Declaration("T0007","Architectes","2016-2018",
            "4","Potter","Harry","0",activites);

    @Test
    void testValiderDescription() {
        activites.add(activite1);
        assertTrue(Validation.validerDescription(activites));
    }


    @Test
    void testValiderFormatDate() {
        activites.add(activite1);
        assertTrue(Validation.validerFormatDate(activites));
    }

    @Test
    void testFormaterDate() {
        assertEquals("Sat Apr 20 00:00:00 EDT 2019",(Validation.formaterDate(activite1.getDate()))+"");
    }

    @Test
    void testAfficherNbrHrFormation() {
        activites.add(activite1);
        assertEquals(14,Validation.afficherNbrHrFormation(activites));
    }

    @Test
    void testValiderHrFormation() {
        assertTrue(Validation.validerHrFormation(34,30));
    }



    @Test
    void testValiderMinCatAct() {
        activites.add(activite1);
        activites.add(activite2);
        assertTrue(Validation.validerMinCatAct(activites,"cours",20));
    }

    @Test
    void testValiderSexe(){
        assertTrue(Validation.validerFormatSexe(declaration1.getSexe()));
    }

    @Test
    void testValiderNom(){
        assertTrue(Validation.validerChaineNull(declaration1.getNom()));
    }

    @Test
    void testValiderPrenom(){
        assertTrue(Validation.validerChaineNull(declaration1.getPrenom()));
    }


}