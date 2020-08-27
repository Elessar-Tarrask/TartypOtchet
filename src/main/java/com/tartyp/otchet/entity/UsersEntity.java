package com.tartyp.otchet.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "synergy")
public class UsersEntity {
    private String userId;
    private String lastname;
    private String firstname;
    private String patronymic;
    private String email;
    private String login;
    private String password;
    private Date started;
    private Date finished;
    private Byte access;
    private Byte isadmin;
    private Byte isconfigurator;
    private byte webDeveloper;
    private Byte pointersAccess;
    private byte[] photo;
    private Integer photoWidth;
    private Integer photoHeight;
    private String locale;
    private Integer updateInterval;
    private String getctag;
    private String pointerCode;
    private Date expire;
    private String homedirectory;
    private String homedirectoryid;
    private String jid;
    private Byte strategyAccess;
    private String ldapHost;
    private String ldapDn;
    private String ldapDomain;
    private String version;
    private Byte readGettingStarted;
    private String calendarId;
    private Timestamp modified;
    private String emailForInvites;

    @Id
    @Column(name = "userID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "patronymic")
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "started")
    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    @Basic
    @Column(name = "finished")
    public Date getFinished() {
        return finished;
    }

    public void setFinished(Date finished) {
        this.finished = finished;
    }

    @Basic
    @Column(name = "access")
    public Byte getAccess() {
        return access;
    }

    public void setAccess(Byte access) {
        this.access = access;
    }

    @Basic
    @Column(name = "isadmin")
    public Byte getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(Byte isadmin) {
        this.isadmin = isadmin;
    }

    @Basic
    @Column(name = "isconfigurator")
    public Byte getIsconfigurator() {
        return isconfigurator;
    }

    public void setIsconfigurator(Byte isconfigurator) {
        this.isconfigurator = isconfigurator;
    }

    @Basic
    @Column(name = "webDeveloper")
    public byte getWebDeveloper() {
        return webDeveloper;
    }

    public void setWebDeveloper(byte webDeveloper) {
        this.webDeveloper = webDeveloper;
    }

    @Basic
    @Column(name = "pointersAccess")
    public Byte getPointersAccess() {
        return pointersAccess;
    }

    public void setPointersAccess(Byte pointersAccess) {
        this.pointersAccess = pointersAccess;
    }

    @Basic
    @Column(name = "photo")
    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Basic
    @Column(name = "photo_width")
    public Integer getPhotoWidth() {
        return photoWidth;
    }

    public void setPhotoWidth(Integer photoWidth) {
        this.photoWidth = photoWidth;
    }

    @Basic
    @Column(name = "photo_height")
    public Integer getPhotoHeight() {
        return photoHeight;
    }

    public void setPhotoHeight(Integer photoHeight) {
        this.photoHeight = photoHeight;
    }

    @Basic
    @Column(name = "locale")
    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Basic
    @Column(name = "update_interval")
    public Integer getUpdateInterval() {
        return updateInterval;
    }

    public void setUpdateInterval(Integer updateInterval) {
        this.updateInterval = updateInterval;
    }

    @Basic
    @Column(name = "getctag")
    public String getGetctag() {
        return getctag;
    }

    public void setGetctag(String getctag) {
        this.getctag = getctag;
    }

    @Basic
    @Column(name = "pointer_code")
    public String getPointerCode() {
        return pointerCode;
    }

    public void setPointerCode(String pointerCode) {
        this.pointerCode = pointerCode;
    }

    @Basic
    @Column(name = "expire")
    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    @Basic
    @Column(name = "homedirectory")
    public String getHomedirectory() {
        return homedirectory;
    }

    public void setHomedirectory(String homedirectory) {
        this.homedirectory = homedirectory;
    }

    @Basic
    @Column(name = "homedirectoryid")
    public String getHomedirectoryid() {
        return homedirectoryid;
    }

    public void setHomedirectoryid(String homedirectoryid) {
        this.homedirectoryid = homedirectoryid;
    }

    @Basic
    @Column(name = "jid")
    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    @Basic
    @Column(name = "strategy_access")
    public Byte getStrategyAccess() {
        return strategyAccess;
    }

    public void setStrategyAccess(Byte strategyAccess) {
        this.strategyAccess = strategyAccess;
    }

    @Basic
    @Column(name = "ldap_host")
    public String getLdapHost() {
        return ldapHost;
    }

    public void setLdapHost(String ldapHost) {
        this.ldapHost = ldapHost;
    }

    @Basic
    @Column(name = "ldap_dn")
    public String getLdapDn() {
        return ldapDn;
    }

    public void setLdapDn(String ldapDn) {
        this.ldapDn = ldapDn;
    }

    @Basic
    @Column(name = "ldap_domain")
    public String getLdapDomain() {
        return ldapDomain;
    }

    public void setLdapDomain(String ldapDomain) {
        this.ldapDomain = ldapDomain;
    }

    @Basic
    @Column(name = "version")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Basic
    @Column(name = "read_getting_started")
    public Byte getReadGettingStarted() {
        return readGettingStarted;
    }

    public void setReadGettingStarted(Byte readGettingStarted) {
        this.readGettingStarted = readGettingStarted;
    }

    @Basic
    @Column(name = "calendarID")
    public String getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    @Basic
    @Column(name = "modified")
    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    @Basic
    @Column(name = "emailForInvites")
    public String getEmailForInvites() {
        return emailForInvites;
    }

    public void setEmailForInvites(String emailForInvites) {
        this.emailForInvites = emailForInvites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return webDeveloper == that.webDeveloper &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(patronymic, that.patronymic) &&
                Objects.equals(email, that.email) &&
                Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                Objects.equals(started, that.started) &&
                Objects.equals(finished, that.finished) &&
                Objects.equals(access, that.access) &&
                Objects.equals(isadmin, that.isadmin) &&
                Objects.equals(isconfigurator, that.isconfigurator) &&
                Objects.equals(pointersAccess, that.pointersAccess) &&
                Arrays.equals(photo, that.photo) &&
                Objects.equals(photoWidth, that.photoWidth) &&
                Objects.equals(photoHeight, that.photoHeight) &&
                Objects.equals(locale, that.locale) &&
                Objects.equals(updateInterval, that.updateInterval) &&
                Objects.equals(getctag, that.getctag) &&
                Objects.equals(pointerCode, that.pointerCode) &&
                Objects.equals(expire, that.expire) &&
                Objects.equals(homedirectory, that.homedirectory) &&
                Objects.equals(homedirectoryid, that.homedirectoryid) &&
                Objects.equals(jid, that.jid) &&
                Objects.equals(strategyAccess, that.strategyAccess) &&
                Objects.equals(ldapHost, that.ldapHost) &&
                Objects.equals(ldapDn, that.ldapDn) &&
                Objects.equals(ldapDomain, that.ldapDomain) &&
                Objects.equals(version, that.version) &&
                Objects.equals(readGettingStarted, that.readGettingStarted) &&
                Objects.equals(calendarId, that.calendarId) &&
                Objects.equals(modified, that.modified) &&
                Objects.equals(emailForInvites, that.emailForInvites);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(userId, lastname, firstname, patronymic, email, login, password, started, finished, access, isadmin, isconfigurator, webDeveloper, pointersAccess, photoWidth, photoHeight, locale, updateInterval, getctag, pointerCode, expire, homedirectory, homedirectoryid, jid, strategyAccess, ldapHost, ldapDn, ldapDomain, version, readGettingStarted, calendarId, modified, emailForInvites);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }
}
