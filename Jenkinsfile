pipeline {
    agent any
    tools {
        maven 'Maven'  // must match the name in Global Tool Configuration
    }
    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-credentials') // Jenkins stored credentials
        DOCKER_IMAGE = "shreyankgopal403/calculator"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/ShreyankGopal/Calculator.git'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        
        stage('Build Docker Image') {
            steps {
                sh "echo ${DOCKERHUB_CREDENTIALS_PSW} | /usr/local/bin/docker login -u ${DOCKERHUB_CREDENTIALS_USR} --password-stdin"
                sh "/usr/local/bin/docker build -t ${DOCKER_IMAGE}:${BUILD_NUMBER} ."
            }
        }

        

        stage('Push Docker Image') {
            steps {
                sh "echo ${DOCKERHUB_CREDENTIALS_PSW} | /usr/local/bin/docker login -u ${DOCKERHUB_CREDENTIALS_USR} --password-stdin"
                sh "/usr/local/bin/docker push ${DOCKER_IMAGE}:${BUILD_NUMBER}"
            }
        }
        stage('Deploy with Ansible') {
            steps {
                sh """
                    /opt/homebrew/bin/ansible-playbook -i inventory.ini deploy.yml \
                      --extra-vars "build_number=${BUILD_NUMBER}"
                """
            }
        }
    }

    post {
        success {
            echo "Build, Test, and Deployment successful! 🚀"
        }
        failure {
            echo "Build failed ❌"
        }
    }
}
