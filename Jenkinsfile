@Library("LibJenkins") _
// library 'LibJenkins'
pipeline {
    agent { label 'ubuntu-slave1' }

    // environment {
    //     M2_HOME = '/usr/share/java/maven-3'
    //     PATH = "${env.M2_HOME}/bin:${env.PATH}"
    // }

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
                    export MAVEN_HOME='/opt/apache-maven-3.6.3/bin'
                    mvn install
                    '''
            } 
        }
        stage('Deliver') {

            steps {
                externalLib(name:"Jenkins", dayOfWeek:"basic")
                sh '''
                export MAVEN_HOME='/opt/apache-maven-3.6.3/bin'
                '''
                pipelineMaven(['skipTests' : false, 'skipInstall': false])
            }
        }
        // stage('Sonarqube analisys'){
        // steps{
        // withSonarQubeEnv('sonarqube-7.6'){
        // sh "mvn sonar:sonar"
        // }
        // }
        // }
    }
    // post {
    //     always {
    //         cleanWs()
    //     }
    // }
}