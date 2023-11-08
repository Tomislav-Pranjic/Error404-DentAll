/* ----------------ADMIN ROLE---------------------- */
CREATE TABLE admin_role (roleId BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
                         role_name VARCHAR(25) UNIQUE NOT NULL,
                         PRIMARY KEY (roleid));

INSERT INTO admin_role (role_name) VALUES ( 'KORISNICKI' );
INSERT INTO admin_role (role_name) VALUES ( 'PRIJEVOZNI' );
INSERT INTO admin_role (role_name) VALUES ( 'SMJESTAJNI' );

/* ----------------ADMIN---------------------- */
CREATE TABLE admin(
    adminId BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    user_name VARCHAR(20) UNIQUE NOT NULL,
    name VARCHAR(20),
    PRIMARY KEY (adminId)
);

/* ----------------ADMIN ROLES----------------------
CREATE TABLE admin_roles(
    admin_id BIGINT,
    role_id BIGINT,
    FOREIGN KEY (admin_id) REFERENCES admin(adminid),
    FOREIGN KEY (role_id) REFERENCES admin_role(roleid),
    PRIMARY KEY (admin_id, role_id)
);

 */