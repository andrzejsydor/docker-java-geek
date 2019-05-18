`docker network create test-net`

# Prime service

`docker build -t prime .`

`docker run -dit --name prime -p 8080:8080 --network test-net prime`

(http://localhost:8080/primes?number=30000000)[http://localhost:8080/primes?number=30000000]
(http://localhost:8080/primes?number=300000000)[http://localhost:8080/primes?number=300000000]

`docker logs prime`

# ab

`docker build -t ab .`

`docker run -dit --name ab --network test-net ab`

`docker exec -it ab sh`

`parallel "ab -n 10 -c 5 http://localhost:8080/api"`
