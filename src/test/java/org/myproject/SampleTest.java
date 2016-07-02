package org.myproject;

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
 * Created by amey on 2/7/16.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SampleTest {
    @Test
    public void should() {
        //given
        open("");
        //when
        //then
    }
}
