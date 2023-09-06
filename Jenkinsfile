pipeline {
    agent any
    tools {
        maven "MAVEN3"
        jdk "java-11-openjdk-amd64"
    }

    stages {
        stage('Fetch Code') {

            steps {
                git branch: 'main', url: 'https://github.com/cojuny/jenkins-maven-ci.git'
            }
        }

        stage('Build') {
            
            steps {
                sh 'mvn install -DskipTests'
            }

            post {
                success {
                    echo 'Archiving artifacts...'
                    archiveArtifacts artifacts: '**/*.jar'
                }
            }
        }

        stage('Unit Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Checkstyle Analysis') {
            steps {
                sh 'mvn checkstyle:checkstyle'
            }
        }

        stage('Sonar Analysis') {
            environment {
                scannerHome = tool 'sonar5.5'
            }
            steps {
                withSonarQubeEnv('sonar') {
                    sh '''${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=helloworld \
                    -Dsonar.projectName=helloworld \
                    -Dsonar.projectVersion=1.0 \
                    -Dsonar.sources=src/ \
                    -Dsonar.junit.reportsPath=target/surefire-reports/ \
                    -Dsonar.java.checkstyle.reportPaths=target/checkstyle-result.xml'''
                }

            }


    }
}