pipeline {
    agent none

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
                node {
                    git 'https://github.com/Neil-Vilarroel/login-iplacex.git'
                }
            }
        }

        stage('Análisis del código') {
            steps {
                // Ejecutar el análisis estático de código (puede ser SonarQube, etc.)
                node {
                    bat 'mvn clean install'
                }
            }
        }

        stage('Upload to Artifactory') {
            steps {
                node {
                    script {
                        bat 'jfrog rt upload --url https://nvillarroel.jfrog.io/artifactory/ --access-token %ARTIFACTORY_ACCESS_TOKEN% target/construction-project-1.0-SNAPSHOT.war java-web-app/'
                    }
                }
            }
        }

        stage('Pruebas') {
            steps {
                node {
                    script {
                        bat 'mvn test'
                    }
                }
            }
        }

        stage('Deployment') {
            steps {
                // Realizar el deployment del proyecto
                node {
                    script {
                        bat 'mvn clean deploy'
                    }
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
