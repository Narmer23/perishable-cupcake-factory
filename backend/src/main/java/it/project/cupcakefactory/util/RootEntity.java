package it.project.cupcakefactory.util;

import java.util.UUID;

public abstract class RootEntity {

    public abstract UUID getId();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RootEntity)) return false;

        RootEntity that = (RootEntity) o;

        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
