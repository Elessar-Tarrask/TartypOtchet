package com.tartyp.otchet.entity;

import javax.persistence.*;

@Entity
@Table(name = "registries", schema = "synergy")
public class RegistriesEntity {
    private String registryId;
    private String name;
    private long translationId;

    @Id
    @Column(name = "registryID")
    public String getRegistryId() {
        return registryId;
    }

    public void setRegistryId(String registryId) {
        this.registryId = registryId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "translationID")
    public long getTranslationId() {
        return translationId;
    }

    public void setTranslationId(long translationId) {
        this.translationId = translationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegistriesEntity that = (RegistriesEntity) o;

        if (translationId != that.translationId) return false;

        if (registryId != null ? !registryId.equals(that.registryId) : that.registryId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = registryId != null ? registryId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (translationId ^ (translationId >>> 32));
        return result;
    }
}
