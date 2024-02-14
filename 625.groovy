pipeline{
    agent any
    tools{
        maven 'Maven3'
    }

    stages{
        stage ('Build'){
            steps{
                sh 'mvn clean package'

            }
            post{
                success{
                    echo "Archiving the Artifacts"
                    archiveArtifacts artifacts: '*/target/.war'
                }
            }
        }
        stage ('Deploy to tomcat server'){
            steps{
deploy adapters: [tomcat7(credentialsId: '3308c2bc-14af-43cd-963b-bd4a4ae3919e', path: '', url: 'http://localhost:2525/')], contextPath: null, war: '**/*.war'            }

        }
    }
}