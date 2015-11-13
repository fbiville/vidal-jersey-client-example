package io.github.fbiville.repository;

import io.github.fbiville.model.Pack;

import java.util.Collection;

public interface PackagesRepository {

    Collection<Pack> findAll();
}
