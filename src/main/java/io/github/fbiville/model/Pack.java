package io.github.fbiville.model;

import java.util.Objects;

public class Pack {

    private final String id;
    private final String name;

    public Pack(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Pack{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Pack other = (Pack) obj;
        return Objects.equals(this.id, other.id)
                && Objects.equals(this.name, other.name);
    }
}
