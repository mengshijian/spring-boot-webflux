package com.msj.webflex;

import com.msj.webflex.client.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

  public static void main(String args[]) {
    SpringApplication.run(Application.class);
    Client client = new Client();
    client.doStuff();
  }
}
