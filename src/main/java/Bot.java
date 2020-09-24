import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.io.IOException;
import java.util.*;

public class Bot extends TelegramLongPollingBot {

    String [][] services = {
        {"OpenWeather", "Работает"},
        {"Weatherbit", "Работает"},
        {"Gismeteo", "Не работает"}
    };



    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());

        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage(message.getChatId(), text);
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyToMessageId(message.getMessageId());
        try {
            setButtons(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {
        Model model = new Model();
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "/Помощь":
                    sendMsg(message, "Чем могу помочь?");
                    break;
                case "/Настройки":
                    sendMsg(message, "Что будем настраивать?");
                    break;
                case "/Список сервисов":
                    sendMsg(message, getRunningServices());
                    break;
                default:
                    try {
                        sendMsg(message, OpenWeather.getWeather(message.getText(), model));
                    } catch (IOException e) {
                        sendMsg(message, "Такой город на OpenWeather не найден");
                    }
                    try {
                        sendMsg(message, Weatherbit.getWeather(message.getText(), model));
                    } catch (IOException e) {
                        sendMsg(message, "Такой город на Weatherbit не найден");
                    }

                    break;
            }
        }
    }

    public void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("/Помощь"));
        keyboardFirstRow.add(new KeyboardButton("/Настройки"));
        keyboardFirstRow.add(new KeyboardButton("/Список сервисов"));

        keyboardRows.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRows);

    }

    public String getRunningServices(){
        String result = "";
        for (String[] o:services) {
            result += o[0] + " - " + o[1] + "\n";
        }
        return result;
    }

    public String getBotUsername() {
        return "Weather_from_Brazik_bot";
    }

    public String getBotToken() {
        return "1398450815:AAEITvdIs24KPzVIZ8OCVWZ92fAfngnaWoU";
    }
}
