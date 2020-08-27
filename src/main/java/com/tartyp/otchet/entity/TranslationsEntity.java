package com.tartyp.otchet.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "translations", schema = "synergy")
@IdClass(TranslationsEntityPK.class)
public class TranslationsEntity {
    private long translationId;
    private String localeId;
    private String text;
    private byte setByUser;

    @Id
    @Column(name = "translationID")
    public long getTranslationId() {
        return translationId;
    }

    public void setTranslationId(long translationId) {
        this.translationId = translationId;
    }

    @Id
    @Column(name = "localeID")
    public String getLocaleId() {
        return localeId;
    }

    public void setLocaleId(String localeId) {
        this.localeId = localeId;
    }

    @Basic
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "set_by_user")
    public byte getSetByUser() {
        return setByUser;
    }

    public void setSetByUser(byte setByUser) {
        this.setByUser = setByUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TranslationsEntity that = (TranslationsEntity) o;
        return translationId == that.translationId &&
                setByUser == that.setByUser &&
                Objects.equals(localeId, that.localeId) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(translationId, localeId, text, setByUser);
    }
}
