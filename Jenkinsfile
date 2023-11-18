pipeline {
    agent any

    tools {
        maven 'Maven'
    }  

    stages {
        stage('Obtener código fuente') {
            steps {
            
                // Clonar el repositorio Git
                git 'https://github.com/Neil-Vilarroel/login-iplacex.git'
            }
        }

        stage('Análisis del código') {
            steps {
                // Ejecutar el análisis estático de código (puede ser SonarQube, etc.)
                bat 'mvn clean install -s settings.xml'
            }
        }



        stage('Pruebas') {
            steps {
                script {
                    bat 'mvn test -s settings.xml'
                }
            }
        }

        stage('Deployment') {
            steps {
                // Realizar el deployment del proyecto
                script {
                    bat 'mvn clean deploy -s settings.xml'
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
