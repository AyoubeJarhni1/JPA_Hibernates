pipeline {
    agent any
    tools {
        sonarQubeScanner 'SonarQube-Scanner'  // Nom du scanner configuré dans Jenkins
    }
    environment {
        SONARQUBE_URL = 'http://localhost:9000'  // URL de ton serveur SonarQube
        SONAR_TOKEN = credentials('jenkins-sonar')  
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                // Compiler ton projet (si nécessaire)
                sh './mvnw clean install -DskipTests'  // Exemple pour un projet Maven JPA/JEE
            }
        }
        stage('SonarQube Analysis') {
            steps {
                // Exécuter l'analyse SonarQube
                withSonarQubeEnv('SonarQube') {  // Nom du serveur SonarQube configuré dans Jenkins
                    sh 'sonar-scanner -Dsonar.login=${SONAR_TOKEN}'
                }
            }
        }
    }
    post {
        always {
            // Étape pour récupérer les résultats de SonarQube après l'analyse
            script {
                def scannerResults = waitForQualityGate()
                if (scannerResults.status != 'OK') {
                    error "SonarQube analysis failed: ${scannerResults.status}"
                }
            }
        }
    }
}
