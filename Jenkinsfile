pipeline {
    agent any
    tools {
        sonarQubeScanner 'SonarQube-Scanner'  // Nom du scanner SonarQube configuré dans Jenkins
    }
    environment {
        SONARQUBE_URL = 'http://localhost:9000'  // URL de ton serveur SonarQube
        SONAR_TOKEN = credentials('jenkins-sonar')  // Nom du secret du token SonarQube dans Jenkins
    }
    stages {
        stage('Checkout') {
            steps {
                // Vérifier que Jenkins récupère la branche 'main' du dépôt
                checkout scm
                // Si vous devez forcer une branche spécifique, décommentez cette ligne :
                // sh 'git checkout main' 
            }
        }
        stage('Build') {
            steps {
                // Compiler le projet (si nécessaire), ici avec Maven pour un projet JPA/JEE
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
                def scannerResults = waitForQualityGate()  // Attend les résultats de l'analyse
                if (scannerResults.status != 'OK') {  // Vérifie si l'analyse est valide
                    error "SonarQube analysis failed: ${scannerResults.status}"  // Échec si l'analyse est invalide
                }
            }
        }
    }
}
