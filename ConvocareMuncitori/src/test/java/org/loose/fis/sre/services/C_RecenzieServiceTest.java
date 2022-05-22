package org.loose.fis.sre.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.loose.fis.sre.model.Recenzie;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class C_RecenzieServiceTest {
    @BeforeAll
    static void setUp() throws Exception {
        RecenzieService.init();
    }
    @Test
    void testDatabaseEmpty() {
        assertThat(RecenzieService.getRecenziiCount()).isNotNull();
        assertThat(RecenzieService.getRecenziiCount().equals(0));
    }
    @Test
    void testAddRecenzie1() {
        RecenzieService.addRecenzie(0, 9, "client@gmail.com");
        assertThat(RecenzieService.getRecenziiCount().equals(1));
        assertThat(RecenzieService.getRecenzie(0, "client@gmail.com")).isEqualTo(1);
        Recenzie r1=RecenzieService.getRecenzie("0client@gmail.com");
        assertThat(r1).isNotNull();
        assertThat(r1.getInterventie()).isEqualTo(0);
        assertThat(r1.getNota()).isEqualTo(9);
        assertThat(r1.getUser()).isEqualTo("client@gmail.com");

        RecenzieService.addRecenzie(1, 10, "client@gmail.com");
        assertThat(RecenzieService.getRecenziiCount().equals(2));
        assertThat(RecenzieService.getRecenzie(1, "client@gmail.com")).isEqualTo(1);
        Recenzie r2=RecenzieService.getRecenzie("1client@gmail.com");
        assertThat(r2).isNotNull();
        assertThat(r2.getInterventie()).isEqualTo(1);
        assertThat(r2.getNota()).isEqualTo(10);
        assertThat(r2.getUser()).isEqualTo("client@gmail.com");


        assertThat(RecenzieService.getMedia("client@gmail.com")).isEqualTo(9.5f);
    }
    @Test
    void testAddRecenzie2() {
        RecenzieService.addRecenzie(1, 9, "muncitor1@gmail.com");
        assertThat(RecenzieService.getRecenziiCount().equals(3));
        assertThat(RecenzieService.getRecenzie(1, "muncitor1@gmail.com")).isEqualTo(1);

        Recenzie r1 = RecenzieService.getRecenzie("1muncitor1@gmail.com");
        assertThat(r1).isNotNull();
        assertThat(r1.getInterventie()).isEqualTo(1);
        assertThat(r1.getNota()).isEqualTo(9);
        assertThat(r1.getUser()).isEqualTo("muncitor1@gmail.com");
    }
}
