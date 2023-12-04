/* ----------------ADMIN ROLE---------------------- */
CREATE TABLE ADMIN_ULOGA (uloga_id INT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
                         uloga_ime VARCHAR(25) UNIQUE NOT NULL,
                         PRIMARY KEY (uloga_id));

INSERT INTO ADMIN_ULOGA (uloga_ime) VALUES ( 'KORISNICKI' );
INSERT INTO ADMIN_ULOGA (uloga_ime) VALUES ( 'PRIJEVOZNI' );
INSERT INTO ADMIN_ULOGA (uloga_ime) VALUES ( 'SMJESTAJNI' );

CREATE TABLE ADMIN(   admin_id INT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
                      user_name VARCHAR(20) UNIQUE NOT NULL,
                      password_hash VARCHAR(100) NOT NULL,
                      PRIMARY KEY (admin_id)
);

CREATE TABLE ADMIN_ULOGE(
                            admin_id INT,
                            uloga_id INT,
                            FOREIGN KEY (admin_id) REFERENCES ADMIN(admin_id),
                            FOREIGN KEY (uloga_id) REFERENCES ADMIN_ULOGA(uloga_id),
                            PRIMARY KEY (admin_id, uloga_id)
);

CREATE TABLE ADRESA
(
    adresa_id INT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    mjesto VARCHAR(128) NOT NULL,
    ulica VARCHAR(128) NOT NULL,
    broj INT NOT NULL,
    PRIMARY KEY (adresa_id)
);

CREATE TABLE VOZILO
(
    reg_vozila VARCHAR(8) NOT NULL,
    model VARCHAR(32) NOT NULL,
    boja VARCHAR(32) NOT NULL,
    kapacitet INT NOT NULL,
    PRIMARY KEY (reg_vozila)
);

CREATE TABLE VOZAC
(
    vozac_id INT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    ime VARCHAR(16) NOT NULL,
    prezime VARCHAR(16) NOT NULL,
    e_mail VARCHAR(64) NOT NULL,
    broj_mobitela VARCHAR(16) NOT NULL,
    poc_rad_vrij TIME NOT NULL,
    reg_vozila VARCHAR(8) NOT NULL,
    radni_dani VARCHAR(7) NOT NULL,
    PRIMARY KEY (vozac_id),
    FOREIGN KEY (reg_vozila) REFERENCES VOZILO(reg_vozila)
);

CREATE TABLE TIP_SMJESTAJA
(
    tip_id INT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    tip_ime VARCHAR(32) NOT NULL,
    tip_velicina INT NOT NULL,
    tip_br_kreveta INT NOT NULL,
    PRIMARY KEY (tip_id)
);

CREATE TABLE SMJESTAJ
(
    smjestaj_id INT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    smjestaj_tip INT NOT NULL,
    adr_id INT NOT NULL,
    br_zvjezdica INT NOT NULL,
    vlasnistvo BOOLEAN NOT NULL,
    dostupno_do DATE NOT NULL,
    PRIMARY KEY (smjestaj_id),
    FOREIGN KEY (adr_id) REFERENCES ADRESA(adresa_id),
    FOREIGN KEY (smjestaj_tip) REFERENCES TIP_SMJESTAJA(tip_id)
);
/* BOOLEAN vrijednost je podržana u PostgreSQL-u */

CREATE TABLE KORISNIK
(
    korisnik_local_id INT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    korisnik_remote_id INT UNIQUE,
    ime VARCHAR(32) NOT NULL,
    prezime VARCHAR(10) NOT NULL,
    e_mail VARCHAR(64) NOT NULL,
    broj_mobitela VARCHAR(16) NOT NULL,
    dat_dol DATE NOT NULL,
    adr_dol INT NOT NULL,
    dat_odl DATE NOT NULL,
    adr_odl INT NOT NULL,
    smj_pref INT NOT NULL,
    smjestaj_id INT,
    vozac_dovozi INT,
    vozac_odvozi INT,
    PRIMARY KEY (korisnik_local_id),
    FOREIGN KEY (smjestaj_id) REFERENCES SMJESTAJ(smjestaj_id),
    FOREIGN KEY (vozac_dovozi) REFERENCES VOZAC(vozac_id),
    FOREIGN KEY (vozac_odvozi) REFERENCES VOZAC(vozac_id),
    FOREIGN KEY (adr_dol) REFERENCES ADRESA(adresa_id),
    FOREIGN KEY (adr_odl) REFERENCES ADRESA(adresa_id),
    FOREIGN KEY (smj_pref) REFERENCES TIP_SMJESTAJA(tip_id)
);