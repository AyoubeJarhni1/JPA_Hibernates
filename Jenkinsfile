pipeline {
    agent any

    environment {
        SONARQUBE_URL = 'http://localhost:9000'  // URL de votre serveur SonarQube
        SONAR_TOKEN = credentials('jenkins-sonar') // Jeton d'accès SonarQube configuré dans Jenkins
    }

    stages {
        stage('Cloner le code depuis GitHub') {
            steps {
                git 'https://github.com/AyoubeJarhni1/JPA_Hibernates.git'
            }
        }

        stage('Analyser avec SonarQube') {
            steps {
                script {
                    // Exécuter l'analyse SonarQube
                    sh """
                    mvn sonar:sonar \
                        -Dsonar.host.url=${SONARQUBE_URL} \
                        -Dsonar.login=${SONAR_TOKEN}
                    """
                }
            }
        }
    }

    post {
        always {
            // Actions à effectuer après chaque exécution
            cleanWs()
        }

        success {
            echo 'L\'analyse de qualité du code a réussi.'
        }

        failure {
            echo 'L\'analyse de qualité du code a échoué.'
        }
    }
}
