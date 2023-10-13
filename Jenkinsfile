@Library("LibJenkins") _
// library 'LibJenkins'
pipeline {
    agent any

    environment {
        M2_HOME = '/usr/share/java/maven-3'
        PATH = "${env.M2_HOME}/bin:${env.PATH}"
    }

    // tools {
    //     maven 'Maven 3.6.3'
    //     // jdk 'Java 11.0.20.1'
    // }

    stages {
        stage('Build') {
            steps {
                helloworld(rodzaj:"Jenkins",level:"basic")
                echo "Building another  one.."
                sh '''
                pwd
                ls
                cd myapp
                pip install -r requirements.txt
                '''
            }
        }
        stage('Test') {
            steps {
                installPythonApp()
                echo "Testing.."
            }
        }
        stage('MVN') {
            steps {
                    sh '''
                    pwd
                    ls
                     
                    mvn install
                    '''
            } 
        } //export MAVEN_HOME='/opt/apache-maven-3.6.3/bin'
        stage('Deliver') {

            steps {
                externalLib(name:"Jenkins", dayOfWeek:"basic")
                sh '''
                export MAVEN_HOME='/opt/apache-maven-3.6.3/bin'
                '''
                pipelineMaven(['skipTests' : false, 'skipInstall': false])
            }
        }
    }
    // post {
    //     always {
    //         cleanWs()
    //     }
    // }
}