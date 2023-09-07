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
                scannerHome = tool 'SonarQube Scanner 4.7.0'
            }
            steps {
                withSonarQubeEnv('sonar') {
                    sh '''${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=helloworld \
                    -Dsonar.projectName=helloworld \
                    -Dsonar.projectVersion=1.0 \
                    -Dsonar.sources=src/ \
                    -Dsonar.java.binaries=target/test-classes/com/cojuny/ \
                    -Dsonar.junit.reportsPath=target/surefire-reports/ \
                    -Dsonar.java.checkstyle.reportPaths=target/checkstyle-result.xml'''
                }
            }
        }

        stage("Quality Gate") {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
                    // true = set pipeline to UNSTABLE, false = don't
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage("UploadArtifact"){
            steps{
                nexusArtifactUploader(
                  nexusVersion: 'nexus3',
                  protocol: 'http',
                  nexusUrl: '192.168.56.56:8081',
                  groupId: 'com.cojuny',
                  version: "${env.BUILD_ID}-${env.BUILD_TIMESTAMP}",
                  repository: 'helloworld-repo',
                  credentialsId: 'nexuslogin',
                  artifacts: [
                    [artifactId: 'helloworldapp',
                     classifier: '',
                     file: 'target/helloworld-1.0-SNAPSHOT.jar',
                     type: 'jar']
                    ]
                )
            }
        }
    }
}