package org.sscholl.bible.biblebot.service;

import org.sscholl.bible.adapter.rocketchat.service.RocketChatService;

public class RocketChatRegisterBiblebotServiceTest {

    private static RocketChatRegisterBiblebotService service = new RocketChatRegisterBiblebotService();

    public static void main(String[] args) {
        service.rocketChatService = new RocketChatService();
        service.rocketChatService.setUp();
        service.onApplicationEvent(null);
    }
}