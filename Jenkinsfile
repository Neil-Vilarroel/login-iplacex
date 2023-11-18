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
    steps {
        script {
            def jfrogExecutable = "C:\\ProgramData\\chocolatey\\bin\\jfrog.exe"
            def uploadCommand = [
                "${jfrogExecutable}",
                "rt", "u",
                "--url", "https://nvillarroel.jfrog.io/artifactory/",
                "--access-token", "%ARTIFACTORY_ACCESS_TOKEN%",
                "--flat", "target/construction-project-1.0-SNAPSHOT.war"
            ]

            def uploadCommandOutput = bat(script: uploadCommand.join(' '), returnStatus: true, returnStdout: true).trim()

            echo "Salida del comando de carga: ${uploadCommandOutput}"

            if (uploadCommandOutput.contains("Uploaded")) {
                echo "Archivo cargado exitosamente en Artifactory."
            } else {
                error "Error al cargar el archivo en Artifactory. La salida del comando no contiene 'Uploaded'."
            }
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
