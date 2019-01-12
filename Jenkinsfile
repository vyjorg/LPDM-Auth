pipeline {
    agent any
    tools {
        maven 'Apache Maven 3.5.2'
    }
    stages{
        stage('Checkout') {
            steps {
                git 'https://github.com/vyjorg/LPDM-Auth'
            }
        }
        stage('Tests') {
            steps {
                sh 'mvn clean test'
            }
            post {
                always {
                    junit 'target/surefire-reports/**/*.xml'
                }
                failure {
                    error 'The tests failed'
                }
            }
        }
        stage('Push to DockerHub') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Deploy') {
            steps {
                sh "docker stop LPDM-AuthMS || true && docker rm LPDM-AuthMS || true"
                sh "docker pull vyjorg/lpdm-auth:latest"
                sh "docker run -d --name LPDM-AuthMS -p 28081:28081 --link LPDM-AuthDB --restart always --memory-swappiness=0 vyjorg/lpdm-auth:latest"
            }
        }
    }
}