pipeline {
    agent any
    tools {
        maven 'Maven'  // must match the name in Global Tool Configuration
    }
    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-credentials') // Jenkins stored credentials
        DOCKER_IMAGE = "shreyankgopal403/calculator"
    }
    // this is a changed file
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

        stage('Build Multi-Arch Docker Image') {
            steps {
                sh "echo ${DOCKERHUB_CREDENTIALS_PSW} | /usr/local/bin/docker login -u ${DOCKERHUB_CREDENTIALS_USR} --password-stdin"
                sh """
                    /usr/local/bin/docker buildx build --platform linux/amd64,linux/arm64 \
                      -t ${DOCKER_IMAGE}:${BUILD_NUMBER} \
                      -t ${DOCKER_IMAGE}:latest \
                      --load .
                """
            }
        }

        stage('Push Multi-Arch Docker Image') {
            steps {
                sh "echo ${DOCKERHUB_CREDENTIALS_PSW} | /usr/local/bin/docker login -u ${DOCKERHUB_CREDENTIALS_USR} --password-stdin"
                sh """
                    /usr/local/bin/docker buildx build --platform linux/amd64,linux/arm64 \
                      -t ${DOCKER_IMAGE}:${BUILD_NUMBER} \
                      -t ${DOCKER_IMAGE}:latest \
                      --push .
                """
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
