package gr.aueb.softeng.team02.model.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Date;

import gr.aueb.softeng.team02.model.AcademicYear;
import gr.aueb.softeng.team02.model.AcademicYearException;
import gr.aueb.softeng.team02.model.Circumscription;

public class AcademicYearTest {
    private AcademicYear year;
    LocalDate start;
    LocalDate end;

    LocalDate grade0dd;
    LocalDate gradeEven;

    @Before
    public void setUp() {
        LocalDate dateOdd2023 = LocalDate.of(2023, 2, 28);
        LocalDate dateEven2023 = LocalDate.of(2023, 6, 1);
        this.year = new AcademicYear("2022-2023", dateEven2023, dateOdd2023);
        this.start = LocalDate.of(2023, 2, 16);
        this.end = LocalDate.of(2023, 3, 25);
    }

    @Test
    public void checkEquals_HashCode() throws AcademicYearException {
        LocalDate dateOdd2022 = LocalDate.of(2022, 2, 28);
        LocalDate dateEven2022 = LocalDate.of(2022, 8, 1);
        AcademicYear year2 = new AcademicYear("2021-2022", dateEven2022, dateOdd2022);
        assertNotEquals(this.year, year2);
        assertNotEquals(this.year.hashCode(), year2.hashCode());

        year2.setAc_year("2022-2023");
        assertEquals(this.year, year2);
        assertEquals(year.hashCode(), year2.hashCode());

        Object other = new Object();
        assertNotEquals(this.year, other);

        AcademicYear yearTest = this.year;
        assertEquals(this.year, yearTest);

        yearTest = null;
        assertNotEquals(this.year, yearTest);

        yearTest = new AcademicYear();
        assertNotEquals(yearTest, this.year);
    }

    /*Check AddCircumscription*/
    @Test(expected = AcademicYearException.class)
    public void checkGetCircumscriptionException() throws AcademicYearException {
        Circumscription c1 = new Circumscription(1, 90, start, end);
        this.year.addCircumscription(c1);
        this.year.getCircumscription(1);
        this.year.getCircumscription(2);
    }

    @Test(expected = AcademicYearException.class)
    public void checkgetEctstPerSemester() throws AcademicYearException {
        Circumscription c1 = new Circumscription(1, 90, start, end);
        this.year.addCircumscription(c1);
        assertEquals(90, this.year.getEctsPerSemester(1));

        // In case it doesnt exist
        this.year.getEctsPerSemester(2);
    }

    @Test(expected = AcademicYearException.class)
    public void checkAddCircumscription() throws AcademicYearException {
        Circumscription c = new Circumscription(1, 80, start, end);

        this.year.addCircumscription(c);

        Circumscription c1 = new Circumscription(1, 90, start, end);
        Circumscription finalC = c1;
        Assertions.assertThrows(AcademicYearException.class, () -> {
            this.year.addCircumscription(finalC);
        });
        Circumscription c2 = new Circumscription(9, 90, start, end);
        Assertions.assertThrows(AcademicYearException.class, () -> {
            this.year.addCircumscription(c2);
        });
        Circumscription c3 = null;
        this.year.addCircumscription(c3);
    }

    @Test(expected = AcademicYearException.class)
    public void checkSetYear() throws AcademicYearException {
        this.year = new AcademicYear();
        Assertions.assertThrows(AcademicYearException.class, () -> {
            this.year.setAc_year(null);
        });
        this.year.setAc_year("2023-2024");
        this.year.setAc_year("2020--2021");
    }

    @Test
    public void checkGetDates() {
        LocalDate dateOdd2023 = LocalDate.of(2023, 2, 28);
        LocalDate dateEven2023 = LocalDate.of(2023, 6, 1);
        assertEquals(dateEven2023, this.year.getGradeDateEven());
        assertEquals(dateOdd2023, this.year.getGradeDateOdd());
    }

    @Test
    public void checkGetYear() {
        assertEquals(this.year.getAc_year(), "2022-2023");
    }
}
