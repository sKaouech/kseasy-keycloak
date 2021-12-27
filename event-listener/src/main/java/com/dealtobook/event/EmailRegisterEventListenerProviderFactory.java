package com.dealtobook.event;

import org.keycloak.Config;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class EmailRegisterEventListenerProviderFactory implements EventListenerProviderFactory {

    @Override
    public EmailRegisterEventListenerProvider create(KeycloakSession keycloakSession) {
        return new EmailRegisterEventListenerProvider(keycloakSession);
    }

    @Override
    public void init(Config.Scope scope) {
        //
    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {
        //
    }

    @Override
    public void close() {
        //
    }

    @Override
    public String getId() {
        return "kseasy-email-register";
    }

}