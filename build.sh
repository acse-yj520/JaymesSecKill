mvn clean package

docker image rm $(docker images|grep '<none>'|awk '{print $3}')

docker-compose -f docker-compose.yml build seckill-main

docker-compose -f docker-compose.yml up -d seckill-main