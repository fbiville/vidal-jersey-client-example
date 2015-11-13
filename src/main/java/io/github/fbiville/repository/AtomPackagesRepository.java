package io.github.fbiville.repository;

import io.github.fbiville.model.Pack;

import javax.ws.rs.client.WebTarget;
import java.util.Collection;
import java.util.function.Function;

public class AtomPackagesRepository implements PackagesRepository {

    private final Function<String, Collection<Pack>> mapper;
    private final WebTarget webTarget;

    public AtomPackagesRepository(Function<String, Collection<Pack>> mapper,
                                  WebTarget webTarget) {

        this.mapper = mapper;
        this.webTarget = webTarget;
    }

    @Override
    public Collection<Pack> findAll() {
        String response = webTarget
                .path("api/packages")
                .request()
                .get()
                .readEntity(String.class);

        return mapper.apply(response);
    }
}
