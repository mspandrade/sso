create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove tinyint
);

create table oauth_client_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);

create table oauth_access_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication BLOB,
  refresh_token VARCHAR(255)
);

create table oauth_refresh_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication BLOB
);

create table oauth_code (
  code VARCHAR(255), authentication BLOB
);

INSERT INTO oauth_client_details 
   (client_id, client_secret, scope, authorized_grant_types, 
   authorities, access_token_validity, refresh_token_validity)
VALUES
   ('mobile_app', '$2a$10$xyto0j9iOwUDh6YGpnc/m.I4kGNgu8ohyr.EfNzEKSGtn9JyT/IVK', 'read,write,trust', 'password,refresh_token', 
   'ROLE_CLIENT,ROLE_TRUSTED_CLIENT', 900, 2592000);