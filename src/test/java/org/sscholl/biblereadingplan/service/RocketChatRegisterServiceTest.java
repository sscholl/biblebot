package org.sscholl.biblereadingplan.service;

import org.junit.Test;

public class RocketChatRegisterServiceTest {

    RocketChatRegisterService service = new RocketChatRegisterService();

    @Test
    public void onApplicationEvent() {
        service.setUp();
        service.onApplicationEvent(null);
    }
}