package com.dealtobook.email;

import org.keycloak.Config;
import org.keycloak.authentication.RequiredActionFactory;
import org.keycloak.authentication.RequiredActionProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

/**
 * @author skaouech
 */
public class ActivationClientAccountFactory implements RequiredActionFactory {

    @Override
    public void close() {

    }

    @Override
    public RequiredActionProvider create(KeycloakSession session) {
        return new ActivationClientAccount();
    }

    @Override
    public void init(Config.Scope config) {

    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {

    }

    @Override
    public String getDisplayText() {
        return "activation client account";
    }


    @Override
    public String getId() {
        return "ACTIVATION_CLIENT_ACCOUNT";
    }

}
