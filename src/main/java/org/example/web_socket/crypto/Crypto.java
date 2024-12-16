package org.example.web_socket.crypto;

import com.google.gson.Gson;
import org.example.web_socket.WebSocket_client;
import org.example.web_socket.interfaces.Interface_taux;

import java.net.URI;

public class Crypto extends WebSocket_client implements Interface_taux {
    String taux = "1";

    public Crypto(URI serverUri) {
        super(serverUri);
    }

    public void decodage(String json) {
        Record_crypto response = new Gson().fromJson(json, Record_crypto.class);

        if (response.bitcoin() != null) {
            System.out.print("Bitcoin en €: " + String.format("%.4f", (Double.parseDouble(response.bitcoin()) / Double.parseDouble(taux))));
            System.out.println("   Bitcoin en $: " + response.bitcoin());
        }
        if (response.ethereum() != null) {
            System.out.print("Ethereum en €: " + String.format("%.4f", (Double.parseDouble(response.ethereum()) / Double.parseDouble(taux))));
            System.out.println("   Ethereum en $: " + response.ethereum());
        }
    }

    @Override
    public void envoi(String taux) {
        this.taux = taux;
    }
}
