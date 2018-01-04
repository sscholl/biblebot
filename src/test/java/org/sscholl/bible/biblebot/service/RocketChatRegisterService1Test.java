package org.sscholl.bible.biblebot.service;

public class RocketChatRegisterService1Test {

    private static RocketChatRegisterBiblebotService service = new RocketChatRegisterBiblebotService();

    public static void main(String[] args) {
        service.rocketChatService.setUp();
        service.onApplicationEvent(null);
    }
}