package com.JCDiamante.SpringAPICall;

import org.springframework.util.StreamUtils;

import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static java.nio.charset.Charset.defaultCharset;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        WireMockServer wireMockServer = new WireMockServer(options().port(8080));
        wireMockServer.start();

        String responseBody = StreamUtils.copyToString(
                Main.class.getClassLoader().getResourceAsStream("books-response.json"),
                defaultCharset());

        wireMockServer.stubFor(get(urlEqualTo("/books"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseBody)));
    }
}
