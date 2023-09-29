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
                    cd CalculatorJavaMvn
                    pwd
                    ls

                    '''
            }
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