CREATE TABLE KORISNIK
(
    korisnik_local_id INT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    korisnik_remote_id INT UNIQUE,
    ime VARCHAR(32) NOT NULL,
    prezime VARCHAR(10) NOT NULL,
    e_mail VARCHAR(64) NOT NULL,
    broj_mobitela VARCHAR(16) NOT NULL,
    smj_pref INT,
    datum_rodenja DATE NOT NULL,
    PRIMARY KEY (korisnik_local_id),
    FOREIGN KEY (smj_pref) REFERENCES TIP_SMJESTAJA(tip_id)
);

CREATE TABLE TRETMAN_INFO
(
    tretman_id INT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    dat_dol DATE,
    vrij_dol TIME,
    dat_odl DATE,
    vrij_odl TIME,
    adr_dol INT,
    adr_odl INT,
    vozac_dovozi INT,
    vozac_odvozi INT,
    smjestaj_id INT,
    dat_tretmana DATE NOT NULL,
    vrijeme_zakljucavanja TIMESTAMP NOT NULL,
    korisnik_id INT NOT NULL,
    PRIMARY KEY (tretman_id),
    FOREIGN KEY (adr_dol) REFERENCES ADRESA(adresa_id),
    FOREIGN KEY (adr_odl) REFERENCES ADRESA(adresa_id),
    FOREIGN KEY (vozac_dovozi) REFERENCES VOZAC(vozac_id),
    FOREIGN KEY (vozac_odvozi) REFERENCES VOZAC(vozac_id),
    FOREIGN KEY (smjestaj_id) REFERENCES SMJESTAJ(smjestaj_id),
    FOREIGN KEY (korisnik_id) REFERENCES KORISNIK(korisnik_local_id)
)