# {{cookiecutter.project_name}}

#### {{cookiecutter.description}}

#### Dependencies

- Postgres
- Docker

#### Setup

The service template is initialized with Git Pre-commit hooks and will fail if 
following steps are not completed. SonarLint plugin is a must in the IDE. 

- Run `initialize.sh` after cloning the repository into local.
- Login to postgres console and run the commands in `resources/db-creation.sql`
- Install SonarLint Plugin on IntelliJ. Ensure that all code smells/bugs/threats 
  highlighted by SonarLint are addressed as per your project quality gate 
  setting, before committing. Go to File -> Settings -> Plugins. Search for
  SonarLint and install. Restart intellij after installation of the plugin.
  https://plugins.jetbrains.com/plugin/7973-sonarlint
- Add the repository {{cookiecutter.destination.git.name}} in {{cookiecutter.destination.git.owner}}'s 
  account on sonarcloud/sonarqube. 
  - If the organization of your version control is private, make {{cookiecutter.destination.git.name}}
    visible to sonarcloud/sonarqube. 
  - Now add the above project in sonarcloud/sonarqube account. 

Configure SonarLint with SonarCloud or SonarQube to receive custom code quality 
guidelines set by the organization. 
- Connect SonarLint to the organization's SonarCloud account or SonarQube
  installation. Go to File -> Settings -> Tools -> SonarLint. Add connection.
  Follow the instructions on-screen to connect.
- Bind project to sonarcloud/sonarqube. Go to File-> Settings -> Tools -> SonarLint
  -> Project Settings. Bind project, select connection and project from cloud.

#### Running the application

- Run `mvn clean install` to install all the dependencies.
- Run `mvn spring-boot:run` to run the application.
