cd dictionary/persistence
call mvn clean package install

cd ..
call mvn clean package install

cd ../minecraft-managers
call mvn clean package install

cd ..
call mvn clean package install