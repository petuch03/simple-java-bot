package org.petuch03;

import io.github.cdimascio.dotenv.Dotenv;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.Collections;

import static org.petuch03.Mode.*;

public class CoolBotanBot extends TelegramLongPollingBot {
    public Mode current_mode = NONE;
    public void onUpdateReceived(Update update) {
//        if (update.hasCallbackQuery()){
//            CallbackQuery callbackQuery = update.getCallbackQuery();
//            String buttonData = callbackQuery.getData();
//
//            switch (buttonData) {
//                case "SCRIPTING":
//                    current_mode = SCRIPTING;
//                    break;
//                case "TRANSLATION":
//                    current_mode = TRANSLATION;
//                    break;
//                case "TRACKING":
//                    current_mode = TRACKING;
//                    break;
//            }
//
//            SendMessage msg = new SendMessage()
//                    .setChatId(callbackQuery.getMessage().getChatId())
//                    .setText("Mode set to " + buttonData);
//            try {
//                execute(msg);
//            } catch (TelegramApiException e) {
//                throw new RuntimeException(e);
//            }
//        }
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                scriptingModeMethod(message);
//                switch (current_mode) {
//                    case SCRIPTING:
//                        scriptingModeMethod(message);
//                        break;
//                    case NONE:
//                        setMode(message);
//                        break;
//                    default:
//                        echo(message);
//                }

            }
        }
    }

    public String getBotUsername() {
        Dotenv dotenv = Dotenv.load();
        return dotenv.get("BOT_USERNAME");
    }


    public String getBotToken() {
        Dotenv dotenv = Dotenv.load();
        return dotenv.get("BOT_TOKEN");
    }

    public void scriptingModeMethod(Message message) {
        String text = message.getText();
        String to_send = JavascriptExecutor.executeJavascript(text);
        SendMessage sendMessage = new SendMessage()
                .setChatId(message.getChatId())
                .setText(to_send);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void echo(Message message) {
        SendMessage sendMessage = new SendMessage()
                .setChatId(message.getChatId())
                .setText(message.getText());
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void setMode(Message message) {

        SendMessage sendMessage = new SendMessage()
                .setChatId(message.getChatId())
                .setText("Select a mode:");

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        InlineKeyboardButton mode1Button = new InlineKeyboardButton()
                .setText("SCRIPTING")
                .setCallbackData("SCRIPTING");

        InlineKeyboardButton mode2Button = new InlineKeyboardButton()
                .setText("TRANSLATION")
                .setCallbackData("TRANSLATION");

        InlineKeyboardButton mode3Button = new InlineKeyboardButton()
                .setText("TRACKING")
                .setCallbackData("TRACKING");

        markup.setKeyboard(Arrays.asList(
                Collections.singletonList(mode1Button),
                Collections.singletonList(mode2Button),
                Collections.singletonList(mode3Button)));

        sendMessage.setReplyMarkup(markup);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }
}
