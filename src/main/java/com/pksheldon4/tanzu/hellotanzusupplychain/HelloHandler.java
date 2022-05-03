package com.pksheldon4.tanzu.hellotanzusupplychain;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static java.lang.Thread.*;

@Component
public class HelloHandler {

    public Mono<ServerResponse> sayHello(ServerRequest request) {
        try {
            long delay = Math.round(Math.random() * 3000);
            sleep(delay);
            System.out.printf("Delaying: %s milliseconds\n", delay);
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue("Hello Tanzu Supply Chain -  Update to include random delay (in milliseconds): " + delay));
        } catch (InterruptedException ie) {
            //Ignore
        }
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue("Why did I get here"));
    }
}
