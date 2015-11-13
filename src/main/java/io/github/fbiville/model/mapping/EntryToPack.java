package io.github.fbiville.model.mapping;

import fr.vidal.oss.jaxb.atom.core.Entry;
import io.github.fbiville.model.Pack;

import java.util.function.Function;

public class EntryToPack implements Function<Entry, Pack> {

    @Override
    public Pack apply(Entry entry) {
        return new Pack(entry.getId(), entry.getTitle());
    }
}
