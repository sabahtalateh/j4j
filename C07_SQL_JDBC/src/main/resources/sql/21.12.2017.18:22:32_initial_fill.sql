-- Fill Roles and Permissions.
INSERT INTO permissions (id, type) VALUES (1, 'VIEW_ORDERS');
INSERT INTO permissions (id, type) VALUES (2, 'EDIT_ORDERS');
INSERT INTO permissions (id, type) VALUES (3, 'VIEW_CATEGORIES');
INSERT INTO permissions (id, type) VALUES (4, 'EDIT_CATEGORIES');
INSERT INTO permissions (id, type) VALUES (5, 'VIEW_ROLES');
INSERT INTO permissions (id, type) VALUES (6, 'EDIT_ROLES');
INSERT INTO permissions (id, type) VALUES (7, 'VIEW_CLIENTS');
INSERT INTO permissions (id, type) VALUES (8, 'EDIT_CLIENTS');
INSERT INTO permissions (id, type) VALUES (9, 'VIEW_ORDER_STATUSES');
INSERT INTO permissions (id, type) VALUES (10, 'EDIT_ORDER_STATUSES');

INSERT INTO role (id, name) VALUES (1, 'admin');
INSERT INTO role (id, name) VALUES (2, 'manager');
INSERT INTO role (id, name) VALUES (3, 'client');

-- Fill role permissions.
INSERT INTO role_permission (role_id, permission_id) VALUES
  (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10), -- Admin.
  (2, 1), (2, 2), (2, 3), (2, 4), (2, 7), (2, 9), (2, 10), -- Manager.
  (3, 1), (3, 3), (3, 9) -- Regular client.
;

-- Fill Clients and assign roles to them.
INSERT INTO "user" (id, login, password) VALUES
  (1, 'admin', MD5('admin')),
  (2, 'user1', MD5('123')),
  (3, 'user2', MD5('123')),
  (4, 'mgr1', MD5('123'));

INSERT INTO user_role (user_id, role_id) VALUES
  (1, 1), -- admin - admin.
  (2, 3), -- user1 - client.
  (3, 3), -- user2 - client.
  (4, 2) -- mgr1 - manager.
;

-- Fill order statuses.
INSERT INTO order_status (name) VALUES ('NEW'), ('PROCESSING'), ('DONE');
