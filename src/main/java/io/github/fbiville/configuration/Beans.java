package io.github.fbiville.configuration;

import fr.vidal.oss.jaxb.atom.core.AtomJaxb;
import fr.vidal.oss.jaxb.atom.core.Entry;
import io.github.fbiville.configuration.http.ApiCredentials;
import io.github.fbiville.configuration.http.VidalRequestFilter;
import io.github.fbiville.model.Pack;
import io.github.fbiville.model.mapping.EntryToPack;
import io.github.fbiville.model.mapping.RawResponseToPacks;
import io.github.fbiville.repository.AtomPackagesRepository;
import io.github.fbiville.repository.PackagesRepository;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Configuration;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.Collection;
import java.util.function.Function;

import static javax.ws.rs.client.ClientBuilder.newClient;

public class Beans {

    private final String host;
    private final String appId;
    private final String appKey;

    public Beans(String host, String appId, String appKey) {
        this.host = host;
        this.appId = appId;
        this.appKey = appKey;
    }

    public PackagesRepository packagesRepository() {
        return new AtomPackagesRepository(
                packagesMapper(),
                webTarget(host, appId, appKey)
        );
    }

    private Function<String, Collection<Pack>> packagesMapper() {
        return new RawResponseToPacks(
                feedUnmarshaller(),
                entryToPack()
        );
    }

    private WebTarget webTarget(String host, String appId, String appKey) {
        return newClient(clientConfiguration(appId, appKey))
                .target(host + "/rest");
    }

    private Configuration clientConfiguration(String appId, String appKey) {
        ClientConfig config = new ClientConfig();
        config.register(requestFilter(appId, appKey));
        return config;
    }

    private ClientRequestFilter requestFilter(String appId, String appKey) {
        return new VidalRequestFilter(apiCredentials(appId, appKey));
    }

    private ApiCredentials apiCredentials(String appId, String appKey) {
        return new ApiCredentials(appId, appKey);
    }

    private Unmarshaller feedUnmarshaller() {
        try {
            return AtomJaxb.newContext().createUnmarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private Function<Entry,Pack> entryToPack() {
        return new EntryToPack();
    }
}
