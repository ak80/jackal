package org.ak80.edu.jackal.at;

import org.ak80.edu.jackal.Jackal;

/**
 * Start/stops the application and handles communication with it
 */
public class ApplicationRunner {

  public static final String SNIPER_ID ="sniper";
  public static final String SNIPER_PASSWORD ="sniper";

  public static final String ITEM_0 ="item-54321";
  public static final String ITEM_1 ="item-65432";

  private AuctionSniperDriver driver;

  // TODO seite 90 mittendrin

  public void startBiddingIn(final FakeAuctionServer auction) {
    startApplication();
    driver = new AuctionSniperDriver(1000);
    driver.showsSniperStatus(Jackal.Status.JOINING);
  }

  private void startApplication() {
    // TODO implement
  }

  public void showsSniperHasLostAuction() {
    driver.showsSniperStatus(Jackal.Status.LOST);
  }

  public void stop() {
    if (driver != null) {
      driver.dispose();
    }

  }

}
