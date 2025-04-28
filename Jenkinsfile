pipeline {
  agent any
  options {
    buildDiscarder(logRotator(numToKeepStr: '5'))
  }
  environment {
    MAVEN_OPTS = "-Dmaven.wagon.http.ssl.insecure=true"
  }
  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Analyse SonarQube') {
      steps {
        script {
          withSonarQubeEnv('Sq1') {
            bat 'mvnw.cmd clean verify org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar'
          }
        }
      }
    }
  }
}
