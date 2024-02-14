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
                deploy adapters: [tomcat7(credentialsId: 'a79dd2d4-dd3f-4341-a0e7-8c854e702de3', path: '', url: 'http://localhost:2525/')], contextPath: null, war: '*/.war'
            }

        }
    }
}