pipeline {
    agent any

    environment {
        SONARQUBE_URL = 'http://localhost:9000'  // URL de votre serveur SonarQube
        SONAR_TOKEN = credentials('jenkins-sonar') // Jeton d'accès SonarQube configuré dans Jenkins
    }

    stages {
        stage('Cloner le code depuis GitHub') {
            steps {
                // Checkout code using Jenkins' SCM feature
                checkout scm
            }
        }

        stage('Analyser avec SonarQube') {
            steps {
                script {
                    // Exécuter l'analyse SonarQube
                    sh """
                    mvn sonar:sonar \
                        -Dsonar.host.url=${SONARQUBE_URL} \
                        -Dsonar.login=${SONAR_TOKEN} \
                        -Dsonar.projectKey=JPA_Hibernates  // Key unique pour le projet SonarQube
                    """
                }
            }
        }
    }

    post {
        always {
            // Actions à effectuer après chaque exécution
            cleanWs()  // Nettoyer l'espace de travail
        }

        success {
            echo 'L\'analyse de qualité du code a réussi.'
        }

        failure {
            echo 'L\'analyse de qualité du code a échoué.'
        }
    }
}
