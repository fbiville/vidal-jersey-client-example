package io.github.fbiville.model.mapping;

import fr.vidal.oss.jaxb.atom.core.Entry;
import fr.vidal.oss.jaxb.atom.core.Feed;
import io.github.fbiville.model.Pack;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RawResponseToPacks implements Function<String, Collection<Pack>> {

    private final Unmarshaller unmarshaller;
    private final Function<Entry, Pack> entryMapper;

    public RawResponseToPacks(Unmarshaller unmarshaller,
                              Function<Entry, Pack> entryMapper) {

        this.unmarshaller = unmarshaller;
        this.entryMapper = entryMapper;
    }

    @Override
    public Collection<Pack> apply(String response) {
        Feed feed = readFeed(response);
        return feed.getEntries().stream()
                .map(entryMapper)
                .collect(Collectors.toList());
    }

    private Feed readFeed(String response) {
        try (Reader reader = new StringReader(response)) {
            return (Feed) unmarshaller.unmarshal(reader);
        } catch (IOException | JAXBException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
