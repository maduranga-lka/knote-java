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
