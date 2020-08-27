package com.tartyp.otchet.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Data
public class TranslationsEntityPK implements Serializable {
    private long translationId;

    public TranslationsEntityPK(long translationId, String localeId) {
        this.translationId = translationId;
        this.localeId = localeId;
    }

    private String localeId;

    @Column(name = "translationID")
    @Id
    public long getTranslationId() {
        return translationId;
    }

    public void setTranslationId(long translationId) {
        this.translationId = translationId;
    }

    @Column(name = "localeID")
    @Id
    public String getLocaleId() {
        return localeId;
    }

    public void setLocaleId(String localeId) {
        this.localeId = localeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TranslationsEntityPK that = (TranslationsEntityPK) o;
        return translationId == that.translationId &&
                Objects.equals(localeId, that.localeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(translationId, localeId);
    }
}
