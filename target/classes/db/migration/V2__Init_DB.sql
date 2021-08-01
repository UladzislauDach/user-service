insert into role (created_at, name)
values (current_timestamp, 'admin');

insert into permission (created_at, value)
values (current_timestamp, 'test_value');

insert into users (created_at, first_name, last_name, login, password, role_id)
values (current_timestamp, 'Jack', 'Shepard', 'Jack12', 123, 1);
update permission set role= 1 where id = 1;