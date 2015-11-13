package io.github.fbiville.configuration.http;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;

import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.MediaType.APPLICATION_ATOM_XML_TYPE;

public class VidalRequestFilter implements ClientRequestFilter {

    private final ApiCredentials credentials;

    public VidalRequestFilter(ApiCredentials credentials) {
        this.credentials = credentials;
    }

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        MultivaluedMap<String, Object> headers = requestContext.getHeaders();
        headers.add(ACCEPT, APPLICATION_ATOM_XML_TYPE);
        headers.add("app_id", credentials.getApplicationId());
        headers.add("app_key", credentials.getApplicationKey());
    }
}
