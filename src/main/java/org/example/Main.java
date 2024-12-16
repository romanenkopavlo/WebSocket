package org.example;

import org.example.web_socket.configuration.Configuration_json;
import org.example.web_socket.configuration.Record_config;
import org.example.web_socket.crypto.Crypto;
import org.example.web_socket.trade.Trade;

import java.net.URI;
import java.net.URISyntaxException;

public class Main {
    static Crypto webSocket_client_crypto;
    static Trade webSocket_client_trade;

    public static void main(String[] args) {
        try {
            Record_config config = Configuration_json.init();
            System.out.println(config.trade());
            System.out.println(config.crypto());
            webSocket_client_trade = new Trade(new URI(config.trade()));
            webSocket_client_trade.connect();

            webSocket_client_crypto = new Crypto(new URI(config.crypto()));
            webSocket_client_crypto.connect();

            webSocket_client_trade.setInterface_taux(webSocket_client_crypto);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}