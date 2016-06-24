package org.myproject;

/**
 * Created by amey on 24/6/16.
 */

import com.codeborne.selenide.ElementsCollection;
import com.jcabi.aspects.Cacheable;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.SingleOutcome;
import com.jolbox.bonecp.BoneCPDataSource;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Created by amey on 4/28/16.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ComputerDatabaseAcceptanceTest {

    @Test
    public void shouldGoToListingPageByDefault() {
        //when
        open("http://localhost:9998");
        //then
        $("#main > h1").should(be(visible), have(exactText("574 computers found")));
        //and check first 3 rows in computer table list
        List<String>  exptectedNames = Arrays.asList("ACE", "Acer Extensa", "Acer Extensa 5220");
        ElementsCollection actualNames = $$("#main > table > tbody > tr > td:nth-child(1)");
        for (int i = 0; i <= 2; i++) {
            actualNames.get(i).should(have(exactText(exptectedNames.get(i))));
        }
    }

    @Test
    public void shouldFilterComputerByName() {
        //given
        open("http://localhost:9998");
        //when
        $("#searchbox").setValue("apple");
        $("#searchsubmit").click();
        //then should display message
        $("#main > h1").should(be(visible), have(exactText("13 computers found")));
        List<String>  exptectedNames = Arrays.asList("Apple I", "Apple II", "Apple II Plus");
        ElementsCollection actualNames = $$("#main > table > tbody > tr > td:nth-child(1)");
        for (int i = 0; i <= 2; i++) {
            actualNames.get(i).should(have(exactText(exptectedNames.get(i))));
        }
    }

    @Test
    public void shouldAddComputerSuccessfully() {
        //given
        open("http://localhost:9998");
        //when
        $("#add").click();
        //and
        $("#name").setValue("1 computer");
        $("#create").click();
        //then
        $("#main > div.alert-message.warning").should(be(visible), have(exactText("Done! Computer 1 computer has been created")));
        $("#main > h1").should(be(visible), have(exactText("575 computers found")));
        List<String>  exptectedNames = Arrays.asList("1 computer", "ACE", "Acer Extensa");
        ElementsCollection actualNames = $$("#main > table > tbody > tr > td:nth-child(1)");
        for (int i = 0; i <= 2; i++) {
            actualNames.get(i).should(have(exactText(exptectedNames.get(i))));
        }
    }

    @Test
    public void shouldNOTAddComputerIfNameNotProvided() {
        //given
        open("http://localhost:9998");
        //when
        $("#add").click();
        //and
        $("#create").click();
        //then
        $("#heading").should(be(visible), have(exactText("Add a computer")));
        $("div.clearfix.error").should(be(visible));
    }

    @Test
    public void shouldDeleteComputer() {
        //given
        open("http://localhost:9998");
        //when
        $(By.xpath("//a[text()='1 computer']")).click();
        $("#delete").click();
        //then
        $("#main > div.alert-message.warning").should(be(visible), have(exactText("Done! Computer has been deleted")));
        $("#main > h1").should(be(visible), have(exactText("574 computers found")));
        List<String>  exptectedNames = Arrays.asList("ACE", "Acer Extensa", "Acer Extensa 5220");
        ElementsCollection actualNames = $$("#main > table > tbody > tr > td:nth-child(1)");
        for (int i = 0; i <= 2; i++) {
            actualNames.get(i).should(have(exactText(exptectedNames.get(i))));
        }
    }

    @Cacheable(forever = true)
    private static DataSource source() {
        BoneCPDataSource src = new BoneCPDataSource();
        src.setDriverClass("org.h2.Driver");
        src.setJdbcUrl("jdbc:h2:tcp://localhost/~/tmp/test");
        src.setUsername("sa");
        return src;
    }

    private void executeQuery() throws SQLException {
        String name = new JdbcSession(source())
                .sql("SELECT name FROM company WHERE id = ?")
                .set(1)
                .select(new SingleOutcome<String>(String.class));
    }
}