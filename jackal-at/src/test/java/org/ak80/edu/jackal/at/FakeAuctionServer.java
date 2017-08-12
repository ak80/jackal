package org.ak80.edu.jackal.at;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Fake Auction Server
 */
public class FakeAuctionServer {

  private final SingleMessageListener messageListener = new SingleMessageListener();

  public static final String ITEM_ID_AS_LOGIN = "auction-%s";
  public static final String AUCTION_RESOURCE = "Auction";
  public static final String XMPP_HOSTNAME = "localhost";
  public static final String AUCTION_PASSWORD = "auction";

  private final String itemId;
  private final XMPPConnection connection;
  private Chat currentChat;

  public FakeAuctionServer(String itemId) {
    this.itemId = itemId;
    this.connection = new XMPPConnection(XMPP_HOSTNAME);
  }

  public String getItemId() {
    return itemId;
  }

  public void startSellingItem() throws XMPPException {
    connection.connect();
    connection.login(String.format(ITEM_ID_AS_LOGIN, itemId),
        AUCTION_PASSWORD, AUCTION_RESOURCE);
    connection.getChatManager().addChatListener(this::chatCreated);
  }

  private void chatCreated(Chat chat, boolean createdLocally) {
    currentChat = chat;
    chat.addMessageListener(messageListener);
  }

  public void hasReceivedJoinRequestFromSniper() throws InterruptedException {
    messageListener.receivesMessage();
  }

  public void announceClosed() throws XMPPException {
    currentChat.sendMessage(new Message());
  }

  public void stop() {
    connection.disconnect();
  }

  private class SingleMessageListener implements MessageListener {

    private final ArrayBlockingQueue<Message> messages = new ArrayBlockingQueue<>(1);

    @Override
    public void processMessage(Chat chat, Message message) {
      messages.add(message);
    }

    public void receivesMessage() throws InterruptedException {
      assertThat("Message", messages.poll(5, TimeUnit.SECONDS), is(notNullValue()));
    }

  }
}
