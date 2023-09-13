
# Hello World Continuous Integration

### Project Introduction

A Java example of Continuous Integration using Jenkins Pipeline as Code (PaaC). The function of the code is merely simple, print "Hello World!" when executed. The primary focus of the project is on demonstrating the automated CI pipeline for the code.

## What is Continuous Integration (CI)?

Continuous Integration (CI) is a software development practice of automating common tasks such as build or test. It is used by different contributors on the same software project, allowing the team to merge code changes in an efficient and stable manner.

Key principles of Continuous Integration include:

- **Frequent Integration:** Developers integrate their code changes into a central repository multiple times a day.
- **Automated Testing:** Automated tests are run after each integration to identify bugs and issues.
- **Quick Feedback:** Developers receive rapid feedback on the quality of their code every time the code is built.
- **Maintainable Build Process:** Build and deployment processes are automated and version-controlled.

## Jenkins Pipeline as Code (PaaC)

[Jenkins](https://www.jenkins.io/) is a popular open-source server application for automating software development tasks such as building, testing, and deploying code changes.

**Pipeline as Code (PaaC)** is an approach of building the entire pipleline using a single file. The file defines the processes of build, test, and deployment. Allowing the pipeline easier to manage, share, and improve. Jenkins uses Groovy source syntax which should be named as Jenkinsfile, the pipeline can be built by placing this file in the git repository and start Jenkins job as defining the repository url.


### Getting Started with Jenkins Pipeline as Code

To start using Jenkins Pipeline as Code, follow these steps:

1. **Install Jenkins:** If you haven't already, install and set up Jenkins on your server.
2. **Install Required Plugins:** Install any necessary Jenkins plugins for your project's build and deployment needs.
3. **Create a Jenkinsfile:** In your project's repository, create a file named `Jenkinsfile` to define your pipeline stages and steps. This file is written in the Groovy-based DSL (Domain-Specific Language) specifically designed for Jenkins pipelines.
4. **Configure Jenkins:** Configure your Jenkins server to recognize and use the `Jenkinsfile` from your repository.
5. **Run Your Pipeline:** Trigger your pipeline manually or set up webhooks or triggers to automate the execution of your pipeline whenever changes are pushed to the repository.
6. **Monitor and Improve:** Monitor the progress and success of your pipeline runs. Continuously refine and improve your pipeline as needed.

For more detailed information on setting up Jenkins Pipeline as Code and configuring Jenkins for your specific project, refer to the Jenkins documentation and resources.

### Servers and Tools Used

#### Virtual Machines Provisioned by Vagrant and Bash:

1. **Jenkins Server (192.168.56.55:8080):** Jenkins serves as the central automation server for building, testing, and deploying your projects.
2. **Nexus Server (192.168.56.55:8081):** Nexus is used for storing and versioning artifacts, facilitating dependency management in your projects.
3. **SonarQube Server (192.168.56.56):** SonarQube is employed for code quality analysis, helping to identify and rectify code issues.

#### Tools:

- **JDK Version 11:** Java Development Kit version 11 is utilized for building and running Java applications.
- **Apache Maven Version 3:** Maven version 3 is employed as the build and dependency management tool for your projects.
- **Slack:** Slack is used for notifying the results of the continuous integration after each build, facilitating team communication and collaboration.
- **GitHub Repository:** GitHub is the version control platform where your project's code is hosted and from which changes are fetched and integrated into your CI/CD pipelines.

tools

maven "MAVEN3"

    jdk "java-11-openjdk-amd64"

tool 'SonarQube Scanner 4.7.0'

git

settings

sonarqube server

build timestamp

slack

Plugins

Nexus Artifact Uploader

Pipeline Maven Integration

Pipeline Utility Steps

Slack Notification Plugin

SonarQube Scanner for Jenkins
