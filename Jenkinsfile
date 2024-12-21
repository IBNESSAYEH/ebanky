pipeline {
  agent any

  environment {
    SONARQUBE_URL = 'http://sonarqub:9000'
    SONARQUBE_TOKEN = 'squ_3e54c20925e97925f95fd4c3b14b0b3f0ad790e3'
    SONAR_PROJECT_KEY = "ebanky"
    EMAIL_RECIPIENT = 'abdellatifibnessayeh@gmail.com'
  }
  stages {
    stage('Build') {
      steps {
        echo 'Building the project...'
        sh 'mvn clean install -X'
      }
    }
    stage('SonarQube Scan') {
      steps {
        echo 'Running SonarQube analysis...'
        withSonarQubeEnv('sonarqub') {
          sh '''
            mvn sonar:sonar \
            -Dsonar.host.url=${SONARQUBE_URL} \
            -Dsonar.projectKey=$SONAR_PROJECT_KEY \
            -Dsonar.login=${SONARQUBE_TOKEN}
          '''
        }
      }
    }
    stage('Quality Gate Check') {
      steps {
        script {
          def qualityGate = sh(
            script: """
              curl -s -u "$SONARQUBE_TOKEN:" \
              "$SONARQUBE_URL/api/qualitygates/project_status?projectKey=$SONAR_PROJECT_KEY" \
              | jq -r '.projectStatus.status'
            """,
            returnStdout: true
          ).trim()
          if (qualityGate != "OK") {
            error "Quality Gate failed! Stopping the build."
          }
        }
      }
    }
    stage('Build Docker Image') {
      steps {
        echo "Building Docker Image..."
        sh 'docker build -t springboot-app .'
      }
    }
    stage('Deploy Docker Container') {
      steps {
        sh """
          docker stop springboot-app-container || true
          docker rm springboot-app-container || true
          docker run -d -p 8080:8080 --name springboot-app-container springboot-app
        """
      }
    }
  }
   post {
          success {
              script {
                  def message = """
                  The Jenkins pipeline for ${env.JOB_NAME} #${env.BUILD_NUMBER} completed successfully.
                  URL: ${env.BUILD_URL}
                  """
                  sh """
                      echo "$message" | mail -s "Pipeline Success: ${env.JOB_NAME} #${env.BUILD_NUMBER}" ${EMAIL_RECIPIENT}
                  """
              }
          }
          failure {
              script {
                  def message = """
                  The Jenkins pipeline for ${env.JOB_NAME} #${env.BUILD_NUMBER} failed.
                  URL: ${env.BUILD_URL}
                  """
                  sh """
                      echo "$message" | mail -s "Pipeline Failure: ${env.JOB_NAME} #${env.BUILD_NUMBER}" ${EMAIL_RECIPIENT}
                  """
              }
          }
      }
}