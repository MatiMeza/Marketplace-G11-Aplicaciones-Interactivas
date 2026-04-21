CREATE DATABASE IF NOT EXISTS marketplace;

CREATE USER IF NOT EXISTS 'marketplace'@'localhost' IDENTIFIED BY 'Marketplace9!';

GRANT ALL PRIVILEGES ON marketplace.* TO 'marketplace'@'localhost';

FLUSH PRIVILEGES;