`docker network create test-net`

# Prime service

`docker build -t prime .`

`docker run -dit --name prime1 -p 8081:8080 --network test-net --cpus 1 prime`
`docker run -dit --name prime8 -p 8088:8080 --network test-net --cpus 8 prime`

(http://localhost:8080/primes?number=30000000)[http://localhost:8080/primes?number=30000000]
(http://localhost:8080/primes?number=300000000)[http://localhost:8080/primes?number=300000000]

`docker logs prime`

# ab

`docker build -t ab .`

`docker run -dit --name ab --network test-net ab`

`docker exec -it ab sh`

`curl http://prime1:8080/primes?number=10`
`curl http://prime1:8080/primes?number=10`

`ab -n 100 -c 50 http://prime1:8080/primes?number=10000000`
`ab -n 100 -c 50 http://prime8:8080/primes?number=10000000`
#`parallel "ab -n 10 -c 5 http://localhost:8080/api"`
`ab -n 100 -c 50 http://prime1:8080/primes?number=10000000`
`ab -n 1000 -c 500 http://prime8:8080/primes?number=10000000`