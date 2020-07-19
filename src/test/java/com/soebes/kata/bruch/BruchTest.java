package com.soebes.kata.bruch;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BruchTest {

  @Nested
  class Signum {
    @Test
    void signum_bei_z_pos_n_pos() {
      Bruch bruch = new Bruch(1, 2);
      assertThat(bruch.signum()).isEqualTo(+1);
    }

    @Test
    void signum_bei_z_neg_n_pos() {
      Bruch bruch = new Bruch(-1, 2);
      assertThat(bruch.signum()).isEqualTo(-1);
    }

    @Test
    void signum_bei_z_pos_n_neg() {
      Bruch bruch = new Bruch(1, -2);
      assertThat(bruch.signum()).isEqualTo(-1);
    }

    @Test
    void signum_bei_z_neg_n_neg() {
      Bruch bruch = new Bruch(-1, -2);
      assertThat(bruch.signum()).isEqualTo(+1);
    }
  }

  @Nested
  class CompareTo {
    @Test
    void bruch_eins_und_zwei_identisch() {
      Bruch bruch_eins = new Bruch(1, 2);
      Bruch bruch_zwei = new Bruch(1, 2);

      assertThat(bruch_eins.compareTo(bruch_zwei)).isEqualTo(0);
    }

    @Test
    void bruch_eins_groesser_bruch_zwei() {
      Bruch bruch_eins = new Bruch(1, 1);
      Bruch bruch_zwei = new Bruch(1, 2);

      assertThat(bruch_eins.compareTo(bruch_zwei)).isGreaterThan(0);
    }

    @Test
    void bruch_eins_kleiner_bruch_zwei() {
      Bruch bruch_eins = new Bruch(1, 3);
      Bruch bruch_zwei = new Bruch(1, 2);

      assertThat(bruch_eins.compareTo(bruch_zwei)).isLessThan(0);
    }

  }

  @Nested
  class Verification {
    @Test
    void equals() {
      EqualsVerifier.forClass(Bruch.class).usingGetClass().verify();
    }

    @Test
    void check_to_string() {
      Bruch bruch = new Bruch(1, 2);
      assertThat(bruch.toString()).isEqualTo("Bruch[zaehler=1, nenner=2]");
    }
  }

  @Nested
  class UngueltigeWerte {
    @Test
    void nenner_muss_ungleich_null_sein() {
      assertThatIllegalArgumentException().isThrownBy(() -> new Bruch(1, 0)).withMessage("nenner darf nicht 0 sein.");
    }

    @Test
    void compare_to_null() {
      assertThatIllegalArgumentException().isThrownBy(() -> new Bruch(1, 2).compareTo(null)).withMessage("vergleich darf nicht null sein.");
    }
  }

  @Nested
  class Normalisieren {

    @Test
    void normalisiere_0_x() {
      Bruch bruch = new Bruch(0, 6);

      assertThat(bruch.getZaehler()).isEqualTo(0);
      assertThat(bruch.getNenner()).isEqualTo(1);
    }

    @Test
    void normalisiere_unechten_bruch() {
      Bruch unechterBruch = new Bruch(4, 6);
      assertThat(unechterBruch.getZaehler()).isEqualTo(2);
      assertThat(unechterBruch.getNenner()).isEqualTo(3);
    }
  }

  @Nested
  class Multiplikation {

    @Test
    void multiplikation() {
      Bruch multiplikator = new Bruch(2, 3);
      Bruch multiplikand = new Bruch(4, 5);

      Bruch produkt = multiplikator.multipliziere(multiplikand);

      assertThat(produkt).isEqualTo(new Bruch(8, 15));
    }
  }

  @Nested
  class Dividieren {
    @Test
    void dividiere_1_2_durch_2_5() {
      Bruch dividend = new Bruch(1, 2);
      Bruch divisor = new Bruch(2, 5);

      Bruch quotient = dividend.dividieren(divisor);

      assertThat(quotient).isEqualTo(new Bruch(5, 4));
    }

    @Test
    void dividiere_same() {
      Bruch dividend = new Bruch(1, 2);
      Bruch divisor = new Bruch(1, 2);

      Bruch quotient = dividend.dividieren(divisor);

      assertThat(quotient).isEqualTo(new Bruch(1, 1));
    }
  }

  @Nested
  class Subtraktion {

    @Test
    void subtrahiere_ein_weniger_zwei() {
      Bruch minuend = new Bruch(2, 3);
      Bruch subtrahend = new Bruch(1, 5);

      Bruch differenz = minuend.subtrahiere(subtrahend);

      assertThat(differenz).isEqualTo(new Bruch(7, 15));
    }

    @Test
    void subtrahiere_bei_gleichem_nenner() {
      Bruch minuend = new Bruch(5, 9);
      Bruch subtrahend = new Bruch(2, 9);

      Bruch differenz = minuend.subtrahiere(subtrahend);

      assertThat(differenz).isEqualTo(new Bruch(3, 9));
    }

    @Test
    void subtrahiere_x() {
      Bruch minuend = new Bruch(21, 7);
      Bruch subtrahend = new Bruch(12, 7);

      Bruch differenz = minuend.subtrahiere(subtrahend);

      assertThat(differenz).isEqualTo(new Bruch(9, 7));
    }
  }

  @Nested
  class Addition {
    @Test
    void chaining() {
      Bruch summand_1 = new Bruch(1, 2);
      Bruch summand_2 = new Bruch(1, 3);
      Bruch summand_3 = new Bruch(1, 4);

      Bruch summe = summand_1.addiere(summand_2).addiere(summand_3);

      assertThat(summe).isEqualTo(new Bruch(26, 24));
    }

    @Test
    void chaining_second() {
      Bruch summand_1 = new Bruch(1, 2);
      Bruch summand_2 = new Bruch(1, 3);
      Bruch summand_3 = new Bruch(1, 4);

      Bruch summe = summand_1.addiere(summand_2).addiere(summand_3);

      assertThat(summe).isEqualTo(new Bruch(13, 12));
    }

    @Test
    void add() {
      Bruch summand_1 = new Bruch(2, 3);
      Bruch summand_2 = new Bruch(1, 5);
      Bruch summe = summand_1.addiere(summand_2);
      assertThat(summe).isEqualTo(new Bruch(13, 15));
    }

    @Test
    void add_1_3_plus_2_3_should_be_1_1() {
      Bruch summand_1 = new Bruch(1, 3);
      Bruch summand_2 = new Bruch(2, 3);
      Bruch summe = summand_1.addiere(summand_2);
      assertThat(summe).isEqualTo(new Bruch(3, 3));
    }

    @Test
    void add_1_3_plus_2_3_should_be_1_1_gekuerzt() {
      Bruch summand_1 = new Bruch(1, 3);
      Bruch summand_2 = new Bruch(2, 3);
      Bruch summe = summand_1.addiere(summand_2);
      assertThat(summe.getZaehler()).isEqualTo(1);
      assertThat(summe.getNenner()).isEqualTo(1);
    }

  }


}
