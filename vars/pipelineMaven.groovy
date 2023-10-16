def call(Map settings = [:]) {
    node('ubuntu-slave1') {
        ansiColor('xterm')
        {
        def skipTests = settings.get('skipTests', false)
        def skipInstall = settings.get('skipInstall', false)
       
        stage('BuildMVN') {
            timestamps {
                sh '''
                cd /home/jenkins/jenkins_slave/workspace/JenPipeline
                pwd
                ls                
                export MAVEN_HOME='/opt/apache-maven-3.6.3/bin'
                mvn package 
                '''
                // -DskipTests
            }
        }
        stage('TestMVN') {
            if (!skipTests)
            script {
                sh  '''
                cd /home/jenkins/jenkins_slave/workspace/JenPipeline
                mvn verify '''
                // Importowanie wyników testów które sie nie robią
                junit allowEmptyResults: true,
                testResults: '**/target/surefire-reports/*.xml',
                skipPublishingChecks: true
            }
        }
        stage('InstallMVN') {
            if (!skipInstall)
                // Install artefakt w lokalnym repo ~/.m2/repository
                sh '''
                cd /home/jenkins/jenkins_slave/workspace/JenPipeline
                mvn install -DskipTests '''           
        }
    }
}
}