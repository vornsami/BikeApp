### Information

Bikeapp is a viewer for Helsinki city bike travel data. It is implemented through a java-based backend server and a react-based frontend.

By default the website deploys to http://localhost:3000.

### How to use

Data files are placed in a csv directory located in the same directory as the backend is running in.
If you are running the app through source code, place the directory in the /backend directory.

If the backend is run through a .jar-file, place the directory in the same directory as the .jar-file.

If running through a docker-container, place the directory here.

The name of the directory should be "csv".

The application frontend should be run using npm. Before running the application, install all dependancies using 'npm install'.
Use command 'npm start' for the developement version. The application can be built using 'npm run build' for the production version. After the application has been built, it can be started using the command 'serve -s build'.

### How to configure the app

The application has two configuration files, one for the frontend and one for the backend.
These files can be found in /frontend/src and /backend directories respectively. 

Frontend's config.js currently is used to set the backend's API URL. The default URL is htto://localhost:8080.

The backend's server.conf file is used for more variables. It has less important variables, such as the apps name and version, but this file also sets up the port the server uses and the database's URL and name.
The backend will not run if these variables are not present, so please be careful when modifying the file.

