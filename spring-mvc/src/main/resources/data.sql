insert into user(login, password)
-- user - user
values ('user', '$2y$10$8UxvnodNNdU2S3NNZVYDrOm/qAqGphD8tqt5TNbMeyPRUFsxlOKR6');
insert into user(login, password, role)
--        api - api
values ('api', '$2y$10$5MoICnWWHWCEOAD.QRBXquI2PTHM9vL9TUWdfKN/t082en/58ieR2', 'API'),
--        admin - admin
       ('admin', '$2y$10$.n61hogvF4IjVVrMust.Oes.3UWCawn.nvoUUquqTA.LdPnvka0FW', 'ADMIN');