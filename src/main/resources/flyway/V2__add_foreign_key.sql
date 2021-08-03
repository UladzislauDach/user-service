alter table if exists permission
    add constraint permission_role_fk foreign key (role) references role;
alter table if exists users
    add constraint users_role_fk foreign key (role_id) references role;