@Library("LibJenkins") _
// library 'LibJenkins'
pipeline {
    agent any
    // agent { 
    //     node {
    //         label 'docker-agent-python'
    //         }
    //   }

        
    // environment {
    //     M2_HOME = '/usr/share/java/maven-3'
    //     PATH = "${env.M2_HOME}/bin:${env.PATH}"
    //     // skipTests = params.skipTests ?: false
    //     // skipInstall = params.skipInstall ?: false
    // }

    tools {
        maven 'Maven 3.6.3'
        // jdk 'Java 11.0.20.1'
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

        // stage('BuildMVN') {
        //     steps {
        //         // Zbudowanie kodu
        //         sh 'mvn package -DskipTests'
        //     }
        // }
        // stage('TestMVN') {
        //     steps {
        //         // Uruchomienie testów aplikacji
        //         sh  'mvn verify'
        //         // Importowanie wyników testów które sie nie robią
        //         junit allowEmptyResults: true,
        //         testResults: '**/target/surefire-reports/*.xml',
        //         skipPublishingChecks: true
        //     }
        // }
        // stage('InstallMVN') {
        //     steps {
        //         // Install artefakt w lokalnym repo .m2
        //         sh 'mvn install -DskipTests'
        //     }
        // }



        stage('Deliver') {
            // environment {
            //     M2_HOME = '/usr/share/java/maven-3'
            //     PATH = "${env.M2_HOME}/bin:${env.PATH}"
            // }
            steps {
                externalLib(name:"Jenkins", dayOfWeek:"basic")
                sh '''
                echo "doing delivery stuff.."

                export MAVEN_HOME='/opt/apache-maven-3.6.3/bin'
                mvn install
                '''
                // pipelineMaven(skipTests:"false", skipInstall:"false")

            }
        }
    }
}