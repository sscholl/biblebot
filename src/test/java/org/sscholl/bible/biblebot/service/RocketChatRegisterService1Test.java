package org.sscholl.bible.biblebot.service;

import org.junit.Test;

public class RocketChatRegisterService1Test {

    RocketChatRegisterService service = new RocketChatRegisterService();

    @Test
    public void onApplicationEvent() {
        service.setUp();
        service.onApplicationEvent(null);
    }
}