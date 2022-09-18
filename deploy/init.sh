#!/bin/sh


docker exec -it mysql mysql -uroot -p
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' ;
FLUSH PRIVILEGES;

#nacos
#curl -L https://github.com/alibaba/nacos/blob/master/config/src/main/resources/META-INF/nacos-db.sql -o nacos/nacos-db.sql