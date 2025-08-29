pipeline {
    agent {
        docker {
            image 'maven:3.9.6-eclipse-temurin-17'
            args '--user root -v /var/run/docker.sock:/var/run/docker.sock -v /root/.m2:/root/.m2'
        }
    }

    stages {
        stage('Install Docker CLI in Build Container') {
            steps {
                sh '''
                  apt-get update
                  apt-get install -y docker.io
                  docker --version
                '''
            }
        }

        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/soram123/simple-maven-pipeline.git'
                sh 'ls -l'
            }
        }

        stage('Build Maven Project') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t simple-maven-app:latest .'
                sh 'docker images | grep simple-maven-app'
            }
        }

        stage('Run Docker Container') {
            steps {
                sh 'docker rm -f simple-maven-container || true'
                sh 'docker run -d --name simple-maven-container -p 9090:8080 simple-maven-app:latest'
                sh 'docker ps'
            }
        }
    }

    post {
        success {
            echo '✅ Build, Test, and Docker Run completed successfully inside Dockerized Jenkins pipeline!'
        }
        failure {
            echo '❌ Build failed inside Dockerized Jenkins pipeline.'
        }
    }
}

