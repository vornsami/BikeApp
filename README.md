### Information

Bikeapp is a viewer for Helsinki city bike travel data. It is implemented through a java-based backend server and a react-based frontend.

By default the website deploys to http://localhost:3000.

### How to use

Data files are placed in a csv directory located in the same directory as the backend is running in. 
If you are running the app through source code, place the directory in the /backend directory.

If the backend is run using a .jar-file, place the directory in the directory you are running the command from. The jar-file can be built from the source code using maven with the command "mvn package". Jar-files can be run using the command 'java -jar jarfilename.jar'

The name of the csv-directory should be "csv".

The backend's configuration file "server.conf" should be placed in the /backend directory when the app is running through source code, or in the same directory as the run command is called from.

The application frontend should be run using npm. Before running the application, install all dependancies using 'npm install'.
Use command 'npm start' for the developement version. The application can be built using 'npm run build' for the production build. After the production build has been built, it can be started using the command 'serve -s build'.

The backend requires a mongodb database to exist for the application to use. A fast way to get the database running is by using docker-compose, but any other way works as well. Remember to configure the backend to use your database. 

### How to configure the app

The application has two configuration files, one for the frontend and one for the backend.
These files can be found in /frontend/src and /backend directories respectively. 

Frontend's config.js currently is used to set the backend's API URL. The default URL is htto://localhost:8080.

The backend's server.conf file is used for more variables. It has less important variables, such as the apps name and version, but this file also sets up the port the server uses and the database's URL and name.
The backend will not run if these variables are not present, so please be careful when modifying the file.

