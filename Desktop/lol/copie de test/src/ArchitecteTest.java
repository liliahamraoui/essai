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

import jdk.jfr.StackTrace;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class ArchitectesTest {
    ArrayList<Activite> activites = new ArrayList<>();
    Activite activite1 = new Activite("Cours sur la chimieee", "cours", "14", "2019-04-20");
    Activite activite2 = new Activite("Cours sur l'informatique", "cours", "20",
            "2019-04-20");
    Declaration declaration1 = new Declaration("T0007", "Architectes", "2016-2020",
            "4", "Potter", "Harry", "0", activites);

    @Test
    void testValiderCycle() {
        assertTrue(Architectes.validerCycle(declaration1));
    }

    @Test
    void testValiderheursTr() {
        assertTrue(Architectes.validerheursTr(declaration1));
    }

    @Test
    void testValiderNumPermis(){
        assertTrue(Architectes.validerNumeroPermis(declaration1));
    }

    @Test
    void testValiderHrFormationPrio (){
        activites.add(activite1);
        activites.add(activite2);
        assertTrue(Architectes.validerHrFormationPrio(activites));
    }

    @Test
    void testValiderheursTr (){
        assertTrue(Architextes.validerheursTr(declaration1));
    }



}