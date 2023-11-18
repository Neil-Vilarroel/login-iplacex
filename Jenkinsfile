pipeline {
    agent any

    environment {
        JFROG_USERNAME = 'neil.villarroel@hotmail.com'
        JFROG_TOKEN = 'cmVmdGtuOjAxOjE3MzE4MTE3ODQ6WUdFaHF2SnVwVGlaUnNSbFNWUzVVN3YwZTVq'
    }

    stages {
        stage('Configurar Docker y Git') {
            steps {
                script {
                    // Comando para iniciar sesión en el repositorio Docker de JFrog Artifactory
                    def dockerLoginCmd = "docker login -u ${JFROG_USERNAME} -p ${JFROG_TOKEN} nvillarroel.jfrog.io"
                    sh dockerLoginCmd

                    // Clonar el repositorio Git
                    git 'https://tu-usuario-git@github.com/Neil-Vilarroel/login-iplacex.git'
                }
            }
        }

        stage('Construir y Publicar en Artifactory') {
            steps {
                script {
                    // Configurar el archivo settings.xml para Maven
                    def mavenSettings = """
                        <settings>
                            <!-- Tus configuraciones de Maven aquí -->
                            <servers>
                                <server>
                                    <id>default-docker-local</id>
                                    <username>${JFROG_USERNAME}</username>
                                    <password>${JFROG_TOKEN}</password>
                                </server>
                            </servers>
                        </settings>
                    """
                    writeFile(file: '.m2/settings.xml', text: mavenSettings)

                    // Construir y publicar en Artifactory
                    sh 'mvn clean install deploy'
                }
            }
        }

        // Otros pasos de tu pipeline
        // ...

        stage('Fin del Pipeline') {
            steps {
                script {
                    // Cerrar sesión de Docker al final del pipeline
                    sh 'docker logout nvillarroel.jfrog.io'
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
