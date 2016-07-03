package org.myproject;

import com.codeborne.selenide.ElementsCollection;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.myproject.DatabaseHelper.*;

/**
 * Created by amey on 2/7/16.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SampleTest {

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("selenide.baseUrl", "http://www.google.com");
        System.setProperty("browser", "chrome");
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
//        System.setProperty("browser", "phantomjs");
//        System.setProperty("phantomjs.binary.path", "c://phantomjs");
    }

    @Test
    public void should() {
        //given
//        insertRecord("employee", "(id, name)", "(1, 'name1')");
//        updateRecord("employee", "name='name2', city='Pune'","id=1");
//        deleteRecord("employee", "id=1");

        //when
        //then
    }
}
