package org.example.web_socket.trade;

import com.google.gson.Gson;
import org.example.web_socket.WebSocket_client;
import org.example.web_socket.interfaces.Interface_taux;


import java.net.URI;

public class Trade extends WebSocket_client {
    private Interface_taux interface_taux;

    public Trade(URI serverUri) {
        super(serverUri);
    }

    public void setInterface_taux(Interface_taux interface_taux) {
        this.interface_taux = interface_taux;
    }

    public void decodage(String json) {
        Record_trade response = new Gson().fromJson(json, Record_trade.class);
        interface_taux.envoi(response.p());
        System.out.println("Taux de change EURO -> DOLLAR est " + response.p());
    }
}
