docker pull selenium/standalone-chrome
docker run -d --name selenium -p 4444:4444 -v --shm-size="2g" selenium/standalone-chrome
./mvnw clean test -DisLocal=false
read
docker stop selenium
docker rm selenium