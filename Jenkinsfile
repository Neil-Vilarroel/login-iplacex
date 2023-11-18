pipeline {
    agent any

    tools {
        maven 'Maven'
    }  

    environment {
        ARTIFACTORY_ACCESS_TOKEN = credentials('artifactory-access-token')
    }

    stages {
        stage('Obtener código fuente') {
            steps {
                echo "ARTIFACTORY_ACCESS_TOKEN: ${ARTIFACTORY_ACCESS_TOKEN}"
                // Clonar el repositorio Git
                git 'https://github.com/Neil-Vilarroel/login-iplacex.git'
            }
        }

        stage('Análisis del código') {
            steps {
                // Ejecutar el análisis estático de código (puede ser SonarQube, etc.)
                script {
                    bat 'mvn clean install'
                }
            }
        }

        /*
        stage('Instalar JFrog CLI') {
            steps {
                script {
                    bat 'choco install jfrog-cli -y'
                }
            }
        }
        */

        stage('Upload to Artifactory') {
            agent {
                docker {
                    image 'releases-docker.jfrog.io/jfrog/jfrog-cli-v2:2.2.0' 
                    reuseNode true
                }
            }
            steps {
                script {
                    bat 'jfrog rt upload --url https://nvillarroel.jfrog.io/artifactory/ --access-token %ARTIFACTORY_ACCESS_TOKEN% target/construction-project-1.0-SNAPSHOT.war java-web-app/'
                }
            }
        }

        stage('Pruebas') {
            steps {
                script {
                    bat 'mvn test'
                }
            }
        }

        stage('Deployment') {
            steps {
                // Realizar el deployment del proyecto
                script {
                    bat 'mvn clean deploy'
                }
            }
        }
    }

    post {
        success {
            echo 'El Pipeline se ejecutó con éxito.'
        }
        failure {
            echo 'El Pipeline falló. Revisa los registros para más detalles.'
        }
    }
}
