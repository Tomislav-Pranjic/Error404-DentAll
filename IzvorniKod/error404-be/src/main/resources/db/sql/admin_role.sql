/* ----------------ADMIN ROLE---------------------- */
CREATE TABLE admin_role (roleId BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
                         role_name VARCHAR(25) UNIQUE NOT NULL,
                         PRIMARY KEY (roleid));

INSERT INTO admin_role (role_name) VALUES ( 'KORISNICKI' );
INSERT INTO admin_role (role_name) VALUES ( 'PRIJEVOZNI' );
INSERT INTO admin_role (role_name) VALUES ( 'SMJESTAJNI' );