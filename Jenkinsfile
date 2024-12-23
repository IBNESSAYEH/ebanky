pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'jdk17'
    }

    environment {
        DOCKER_IMAGE = 'ebanky'
        DOCKER_TAG = "${BUILD_NUMBER}"
        SONAR_TOKEN = credentials('SonarToken')
        MAIL_TO = 'abdellatifibnessayeh@gmail.com'
    }

    options {
        timeout(time: 1, unit: 'HOURS')
        disableConcurrentBuilds()
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    try {
                        deleteDir()
                        echo "Cloning Git repository..."
                        checkout([$class: 'GitSCM',
                            branches: [[name: '*/main']],
                            userRemoteConfigs: [[
                                url: 'https://github.com/IBNESSAYEH/ebanky.git'
                            ]]
                        ])
                        echo "Repository cloned successfully."
                    } catch (Exception e) {
                        error "Failed to clone repository: ${e.getMessage()}"
                    }
                }
            }
        }

        stage('Environment Check') {
            steps {
                script {
                    try {
                        sh '''
                            echo "Git version:"
                            git --version
                            echo "Current Git branch:"
                            git branch --show-current
                            echo "Git status:"
                            git status
                            echo "Java version:"
                            java -version
                            echo "Maven version:"
                            mvn -version
                            echo "Working directory contents:"
                            pwd
                            ls -la
                        '''
                    } catch (Exception e) {
                        error "Environment check failed: ${e.getMessage()}"
                    }
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    try {
                        timeout(time: 15, unit: 'MINUTES') {
                            sh 'mvn clean install -DskipTests'
                        }
                    } catch (Exception e) {
                        error "Build failed: ${e.getMessage()}"
                    }
                }
            }
        }

        stage('Unit Tests') {
            steps {
                script {
                    try {
                        timeout(time: 10, unit: 'MINUTES') {
                            sh """
                                mvn test \
                                -Dmaven.test.failure.ignore=true \
                                -Dsurefire.rerunFailingTestsCount=2
                            """
                        }
                    } catch (Exception e) {
                        unstable "Some tests failed but continuing: ${e.getMessage()}"
                    }
                }
            }
            post {
                always {
                    junit(
                        allowEmptyResults: true,
                        testResults: '**/target/surefire-reports/*.xml',
                        skipPublishingChecks: true
                    )
                    jacoco(
                        execPattern: 'target/jacoco.exec',
                        classPattern: 'target/classes',
                        sourcePattern: 'src/main/java'
                    )
                }
            }
        }

        stage('Code Quality Analysis') {
            when {
                expression { currentBuild.result != 'FAILURE' }
            }
            steps {
                script {
                    try {
                        timeout(time: 10, unit: 'MINUTES') {
                            sh """
                                mvn sonar:sonar \
                                -Dsonar.projectKey=ebanky \
                                 -Dsonar.projectName=ebanky \
                                 -Dsonar.host.url=http://sonarqube:9000 \
                                 -Dsonar.login=${SONAR_TOKEN}
                            """
                        }
                    } catch (Exception e) {
                        error "Code quality analysis failed: ${e.getMessage()}"
                    }
                }
            }
        }

        stage('Build Docker Image') {
                    steps {
                        script {
                            docker.build("${DOCKER_IMAGE}:${DOCKER_TAG}")
                            docker.build("${DOCKER_IMAGE}:latest")
                        }
                    }
        }



        stage('Deploy') {
                    steps {
                        script {
                            docker.image("${DOCKER_IMAGE}:${DOCKER_TAG}").run('-p 8081:8080')
                        }
                    }
                }
    }

    post {
            success {
                mail to: 'abdellatifibnessayeh@gmail.com',
                     subject: "Pipeline Success - ebanky",
                     body: "Le pipeline Jenkins s'est terminé avec succès !"
            }
            failure {
                mail to: 'abdellatifibnessayeh@gmail.com',
                     subject: "Pipeline Failure - ebanky",
                     body: "Le pipeline Jenkins a échoué. Veuillez vérifier les logs."
            }
            aborted {
                mail to: 'abdellatifibnessayeh@gmail.com',
                      subject: "Pipeline Aborted - ebanky",
                      body: "Le pipeline Jenkins a été interrompu manuellement ou automatiquement."
                }
        }
}