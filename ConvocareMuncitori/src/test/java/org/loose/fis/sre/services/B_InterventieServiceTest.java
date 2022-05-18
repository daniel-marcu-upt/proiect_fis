package org.loose.fis.sre.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.loose.fis.sre.exceptions.DateNotAvailable;
import org.loose.fis.sre.model.Interventie;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class B_InterventieServiceTest {
    @BeforeAll
    static void setUp() throws Exception {
        InterventieService.init();
    }
    @Test
    void testDatabaseEmpty() {
        assertThat(InterventieService.getInterventiiCount()).isNotNull();
        assertThat(InterventieService.getInterventiiCount().equals(0));
    }
    @Test
    void testAddInterventie1() throws DateNotAvailable {
        Date d = new Date();
        InterventieService.addInterventie("client@gmail.com", "muncitor1@gmail.com", d);
        assertThat(InterventieService.getInterventiiCount().equals(1));

        List<Interventie> list=InterventieService.getInterventii("muncitor1@gmail.com");
        assertThat(list.size()).isEqualTo(1);
        Interventie i=list.get(0);
        assertThat(i).isNotNull();
        assertThat(i.getClient()).isEqualTo("client@gmail.com");
        assertThat(i.getMuncitor()).isEqualTo("muncitor1@gmail.com");
        assertThat(i.getId()).isEqualTo(0);
        assertThat(i.getData()).isEqualTo(d);
    }
    @Test
    void testAddInterventie2() throws DateNotAvailable {
        Date d = new Date(122, 3, 1);
        InterventieService.addInterventie("client@gmail.com", "muncitor1@gmail.com", d);
        assertThat(InterventieService.getInterventiiCount().equals(2));

        List<Interventie> list=InterventieService.getInterventii("muncitor1@gmail.com");
        assertThat(list.size()).isEqualTo(2);
        Interventie i=list.get(1);
        assertThat(i).isNotNull();
        assertThat(i.getClient()).isEqualTo("client@gmail.com");
        assertThat(i.getMuncitor()).isEqualTo("muncitor1@gmail.com");
        assertThat(i.getId()).isEqualTo(1);
        assertThat(i.getData()).isEqualTo(d);
    }
    @Test
    void testDateNotAvailable(){
        assertThrows(DateNotAvailable.class, () -> {
            Date d = new Date(122, 3, 1);
            InterventieService.addInterventie("client@gmail.com", "muncitor1@gmail.com", d);
        });
    }

}
