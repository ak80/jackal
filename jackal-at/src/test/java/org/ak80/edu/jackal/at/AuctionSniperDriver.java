package org.ak80.edu.jackal.at;

import org.ak80.edu.jackal.Jackal;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

/**
 * Driver to testing jackal-ui
 */
public class AuctionSniperDriver {

  public AuctionSniperDriver(int timeoutInMillis) {
    // TODO implement
    open("/");
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
