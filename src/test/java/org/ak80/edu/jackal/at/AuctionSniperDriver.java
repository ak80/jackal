package org.ak80.edu.jackal.at;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.ak80.edu.jackal.Jackal;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Driver to testing jackal-ui
 */
public class AuctionSniperDriver {

  public AuctionSniperDriver(int timeoutInMillis) {
    // TODO wäre FF nicht besser?
    System.setProperty("webdriver.chrome.driver", "../chromedriver");
    System.setProperty("selenide.browser", "Chrome");

    open("/jackal");
//    $("#status").shouldHave(text("Hello, Johny!")); // Waits until element gets text

    // TODO
    //open("/login");
    //$(By.name("user.name")).setValue("johny");
    //$("#submit").click();
    //$(".loading_progress").should(disappear); // Waits until element disappears
    //$("#username").shouldHave(text("Hello, Johny!")); // Waits until element gets text
    showsSniperStatus(Jackal.Status.JOINING);
  }

  public void showsSniperStatus(Jackal.Status status) {
    $("#status").shouldHave(text(status.name()));
  }

  public void dispose() {
    // TODO implement?
  }
}
