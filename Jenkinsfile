pipeline {
    agent any

    environment {
        SONAR_TOKEN = credentials('jenkins-sonar')  // Assurez-vous que le token SonarQube est correctement configuré
    }

    stages {
        stage('Checkout SCM') {
            steps {
                checkout scm
            }
        }

        stage('Cloner le code depuis GitHub') {
            steps {
                // Utilisez Git pour cloner le code si nécessaire
                git 'https://github.com/AyoubeJarhni1/JPA_Hibernates.git'
            }
        }

        stage('Analyser avec SonarQube') {
            steps {
                script {
                    // Utilisez une commande compatible Windows ici (bat au lieu de sh)
                    bat '''
                    echo "Début de l'analyse SonarQube"
                    sonar-scanner -Dsonar.projectKey=mon-projet -Dsonar.host.url=http://sonarqube.local -Dsonar.login=%SONAR_TOKEN%
                    echo "Fin de l'analyse SonarQube"
                    '''
                }
            }
        }

        stage('Post Actions') {
            steps {
                cleanWs()  // Nettoie l'espace de travail après le build
            }
        }
    }
    post {
        failure {
            echo 'L\'analyse de qualité du code a échoué.'
        }
    }
}
