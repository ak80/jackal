package org.ak80.edu.jackal.at;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.ak80.edu.jackal.at.ApplicationRunner.ITEM_0;

/**
 * 1. When an auction is selling an item
 * 2. And an Auction Sniper has started to bid in that auction
 * 3. Then the auction will receive a Join request from the auction sniper
 * 4. When an auction announces that it is closed
 * 5. Then the Auction Sniper will show that the auction is lost
 */
public class AuctionSniperEndToEndTest {

  private final FakeAuctionServer auction = new FakeAuctionServer(ITEM_0);
  private final ApplicationRunner application = new ApplicationRunner();

  @Test
  public void sniperJoinsAuctionUntilAuctionCloses() throws Exception {
    auction.startSellingItem();
    application.startBiddingIn(auction);
    auction.hasReceivedJoinRequestFromSniper();
    auction.announceClosed();
    application.showsSniperHasLostAuction();
  }

  @AfterEach
  public void stopAuction() {
    auction.stop();
  }

  @AfterEach
  public void stopApplication() {
    application.stop();
  }

}
