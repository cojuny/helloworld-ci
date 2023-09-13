def COLOR_MAP = [
    'SUCCESS': 'good', 
    'FAILURE': 'danger',
]

pipeline {
    agent any

    environment {
        REPO = "cojuny/helloworld-ci"
    }

    tools {
        maven "MAVEN3"
        jdk "java-11-openjdk-amd64"
    }

    stages {
        stage('Fetch Code') {

            steps {
                git branch: 'main', url: "https://github.com/${REPO}.git"
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
                withSonarQubeEnv('sonarqube-server') {
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

        //stage("Quality Gate") {
        //    steps {
        //        timeout(time: 1, unit: 'HOURS') {
                    // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
                    // true = set pipeline to UNSTABLE, false = don't
        //            waitForQualityGate abortPipeline: true
        //        }
        //    }
        //}

        stage("Upload Artifact"){
            steps{
                nexusArtifactUploader(
                  nexusVersion: 'nexus3',
                  protocol: 'http',
                  nexusUrl: '192.168.56.56:8081',
                  groupId: 'com.cojuny',
                  version: "${env.BUILD_ID}-${env.BUILD_TIMESTAMP}",
                  repository: 'helloworld-repo',
                  credentialsId: 'nexus-credentials',
                  artifacts: [
                    [artifactId: 'helloworldapp',
                     classifier: '',
                     file: 'target/helloworld-1.0-SNAPSHOT.jar',
                     type: 'jar']
                    ]
                )
            }
        }
        
        
        stage('Github Release') {
            when {
                expression { currentBuild.resultIsBetterOrEqualTo('SUCCESS') }
            }
            steps {
                withCredentials([string(credentialsId: 'github-token', variable: 'TOKEN')]) {
                    script {

                       
                        def response = sh(script: """
                            curl -X POST \
                                -H "Accept: application/vnd.github+json" \
                                -H "Authorization: Bearer ${TOKEN}" \
                                -H "X-GitHub-Api-Version: 2022-11-28" \
                                https://api.github.com/repos/${REPO}/releases \
                                -d '{
                                    "tag_name": "v1.0.0",
                                    "target_commitish": "main",
                                    "name": "helloworld-ci Release",
                                    "body": "Continuous Integration for Java Maven helloworld",
                                    "draft": false,
                                    "prerelease": false
                                }'
                        """, returnStatus: true)

                        if (response == 0) {
                            currentBuild.result = 'SUCCESS'
                        } else {
                            currentBuild.result = 'FAILURE'
                        }
                    }
                }
            
            }
        }
    
    }
    
    post {
        always {
            echo 'Slack Notifications.'
            slackSend channel: '#jenkinscicd',
                color: COLOR_MAP[currentBuild.currentResult],
                message: "*${currentBuild.currentResult}:* Job ${env.JOB_NAME} build ${env.BUILD_NUMBER} \n More info at: ${env.BUILD_URL}"
        }
    }
}
