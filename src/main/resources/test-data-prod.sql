insert into UserEntity(userId, username, password, token, role) values (nextval('hibernate_sequence'),'admin', 'admin', 'f6fdffe48c908deb0f4c3bd36c032e72','ROLE_ADMIN');
insert into UserEntity(userId, username, password, token, role) values (nextval('hibernate_sequence'),'user1', 'user1', '332bc0ddba3c4bc792c4829ff3834ca9','ROLE_CUSTOMER');
insert into UserEntity(userId, username, password, token, role) values (nextval('hibernate_sequence'),'user2', 'user2', '2c7f98ea10b9c780bb2b05602ad29268','ROLE_CUSTOMER');




insert into ContactEntity(id, userIdFk, email, firstname, lastname, mailingcity, mailingcountry, mailingpostalcode, mailingstreet, phone ) values (nextval('hibernate_sequence'), 1,  'admin@admin.de', 'Admin', 'Admin', '', '', '', '', '');
insert into ContactEntity(id, userIdFk, email, firstname, lastname, mailingcity, mailingcountry, mailingpostalcode, mailingstreet, phone ) values (nextval('hibernate_sequence'), 2,  'max@gmx.de', 'Max', 'Bauer', 'Sindelfingen', 'Germany', '71063', 'Planiestr. 10', '0703112345');
insert into ContactEntity(id, userIdFk, email, firstname, lastname, mailingcity, mailingcountry, mailingpostalcode, mailingstreet, phone ) values (nextval('hibernate_sequence'), 3,  'peter@gmx.de', 'Peter', 'Metzger', 'Stuttgart', 'Germany', '70376', 'Naststr. 27', '071112345');