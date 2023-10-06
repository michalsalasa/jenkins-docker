@Library("LibJenkins") _
// library 'LibJenkins'
pipeline {
    agent any
    // agent { 
    //     node {
    //         label 'docker-agent-python'
    //         }
    //   }

    environment {
        M2_HOME = '/usr/share/java/maven-3'
        PATH = "${env.M2_HOME}/bin:${env.PATH}"
    }
    tools {
        maven 'Maven 3.6.3'
        // jdk 'Java 13.0.12'
    }

    // triggers {
    //     pollSCM '* 5 * * 1-5'
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
                // sh '''
                // cd myapp
                // python3 hello.py
                // python3 hello.py --name=Michal
                // '''
            }
        }
        stage('MVN') {
            steps {
                    sh '''
                 
                    export MAVEN_HOME='/opt/apache-maven-3.6.3/bin' 
                    
                    mvn install
                    '''
            } // /usr/share/java/maven-3/bin/mvn clean install. PATH = "${MAVEN_HOME}/bin:${env.PATH}"..'/home/jenkins/apache-maven-3.6.3/bin'... MAVEN_HOME = '/usr/share/java/maven-3'
        }
        stage('RunUnitTests') {
            steps {
                sh '''
                ls
                pwd
                mvn test
                '''
                junit allowEmptyResults: true,
                testResults: '**/target/surefire-reports/*.xml',
                skipPublishingChecks: true
            }
        }

        stage('Deliver') {
            steps {
                externalLib(name:"Jenkins", dayOfWeek:"basic")
                sh '''
                echo "doing delivery stuff.."
                mvn package -DskipTests
                '''
            }
        }
        stage('Testanother') {
            steps {
                // Uruchomienie testów aplikacji
                sh  'mvn verify'
                // Importowanie wyników testów
                junit '**/target/surefire-reports/*.xml'
            }
            }
    }
}