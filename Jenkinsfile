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
                    pwd
                    ls
                    cd CalcJavaMvn
                    pwd
                    ls
                    MAVEN_HOME = '/usr/share/java/maven-3'
                    PATH = "${MAVEN_HOME}/bin:${env.PATH}"
                   
                    mvn clean install
                    '''
            } // /usr/share/java/maven-3/bin/mvn clean install
        }
        stage('Deliver') {
            steps {
                externalLib(name:"Jenkins", dayOfWeek:"basic")
                sh '''
                echo "doing delivery stuff.."
                '''
            }
        }
    }
}