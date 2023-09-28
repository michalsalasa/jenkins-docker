@Library("LibJenkins") _
// library 'LibJenkins'
pipeline {
    agent { 
        node {
            label 'docker-agent-python'
            }
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
                cd myapp
                pip install -r requirements.txt
                '''
            }
        }
        stage('Test') {
            steps {
                another()
                echo "Testing.."
                // sh '''
                // cd myapp
                // python3 hello.py
                // python3 hello.py --name=Michal
                // '''
            }
        }
        stage('Deliver') {
            steps {
                echo 'Deliver....'
                sh '''
                echo "doing delivery stuff.."
                '''
            }
        }
    }
}