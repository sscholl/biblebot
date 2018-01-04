package org.sscholl.bible.biblereadingplan.service;

import org.sscholl.bible.adapter.rocketchat.service.RocketChatService;

public class RocketChatRegisterBibleleseplanServiceTest {

    private static RocketChatRegisterBibleleseplanService service = new RocketChatRegisterBibleleseplanService();

    public static void main(String[] args) {
        service.rocketChatService = new RocketChatService();
        service.rocketChatService.setUp();
        service.onApplicationEvent(null);
    }
}