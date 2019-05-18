
`docker build -t prime .`

`docker run -dit --name prime -p 8080:8080 prime`

(http://localhost:8080/primes?number=3)[http://localhost:8080/primes?number=3]

`docker logs prime`

