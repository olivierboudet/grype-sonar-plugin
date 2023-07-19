# Building

To build the plugin JAR file, call:

```
mvn clean package
```

The JAR will be deployed to `target/grype-sonar-plugin-VERSION.jar`. Copy this to your SonarQube's `extensions/plugins/` directory, and re-start SonarQube.

# Prerequisites

* [NodeJS](https://nodejs.org/en/)

### Scripts

* `npm install` to install your dependencies.
* `npm start` to start a proxy server on port 3000 to debug your JS code.  
  *Note: This plugin must first be deployed and installed on your SonarQube instance, otherwise the extension paths will not be registered. See above under Back-end > Building*  
  This will proxy to a running SonarQube instance, but allow you to use your own local JavaScript instead of what was bundled with your plugin. Once started, you can access `http://localhost:3000` in your browser, and use SonarQube as you normally would.  
  You can use a different port by using the `PORT` environment variable. Example:  
  ```
  PORT=6060 npm start
  ```
  You can control to which SonarQube instance you proxy to by setting the `PROXY_URL` environment variable to any valid URL (defaults to `http://localhost:9000`). Example:  
  ```
  PROXY_URL=https://sonarqube.example.com npm start
  ```
* `npm test` to start watching your files for changes, and run tests accordingly.
* `npm run build` to build your front-end code.  
  Usually, you will not need to call this; instead, this should be part of your package building process.  
  See Back-end > Building above.

### Testing

This project uses [Jest](https://jestjs.io/) for testing. Running `npm test` will run Jest in `--watch` mode. You can find the configuration for Jest in `package.json`.


