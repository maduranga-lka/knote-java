# Thank you https://salaboy.com/about/ for your great great article : https://learnk8s.io/spring-boot-kubernetes-guide

# knote-java

With spring boot, docker and kubernetes

## Initial Git Commands
mkdir knote-java  
git clone https://github.com/maduranga-lka/knote-java.git  
cd knote-java/  
git fetch --all  
git checkout -b dev  ( to switch to the dev branch)  
git pull origin dev ( Sync with the new changes)  
git status ( To check the files to be committed)  
git add * ( To stage files to be committed)  
git commit -m "Initial setup"  
git push origin dev  


## Setup Mongo
https://phoenixnap.com/kb/docker-mongodb
sudo docker pull mongo  
sudo mkdir -p /mongodata  
sudo docker run -it -v mongodata:/data/db -p 27017:27017 --name mongodb -d mongo  
sudo docker logs mongodb  
sudo docker exec -it mongodb bash  
mongo -host localhost -port 27017   
exit  

## Dockerize

docker build -t knote-java . ( -t knote-java is the tag name)  

### Since Mongo db and the spring boot apps are containers, in order to communicate the both containers should be in a docker network  
docker network create knote 

sudo docker run --name=mongodb  --rm -p 27017:27017 --network=knote -d  mongo  
  
sudo docker run --name=knote-java --rm --network=knote -p 8080:8080 -e MONGO_URL=mongodb://mongodb:27017/dev -d knote-java

## Push to Docker Registry

docker login  

docker tag knote-java maduranga/knote-java:1.0.0 

docker push maduranga/knote-java:1.0.0

### Accordingly the docker command for the app will be changed as below

sudo docker run --name=knote-java --rm --network=knote -p 8080:8080 -e MONGO_URL=mongodb://mongodb:27017/dev -d maduranga/knote-java:1.0.0 
sudo docker run --name=mongodb  --rm -p 27017:27017 --network=knote -d  maduranga/mongo:4.4.2

## Install kubectl
https://kubernetes.io/docs/tasks/tools/install-kubectl/  

## Install Minikube
https://minikube.sigs.k8s.io/docs/start/


