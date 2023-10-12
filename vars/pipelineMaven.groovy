def call(Map settings = [:]) {
    // agent {
    //     ubuntu-slave1
    // }
    node('ubuntu-slave1') {
        ansiColor('xterm')
        {
        def skipTests = settings.get('skipTests', false)
        def skipInstall = settings.get('skipInstall', false)
    // environment {
    //     M2_HOME = '/usr/share/java/maven-3'
    //     PATH = "${env.M2_HOME}/bin:${env.PATH}"
    //     // skipTests = params.skipTests ?: false
    //     // skipInstall = params.skipInstall ?: false
    // }
        // stage('Download Source Code') {
        //         checkout scm
        // }
       
        stage('BuildMVN') {
            timestamps {
                // Zbudowanie kodu... timestamps instead of withMaven
                
                sh '''
                cd /home/jenkins/jenkins_slave/workspace/JenPipeline
                pwd
                ls                
                export MAVEN_HOME='/opt/apache-maven-3.6.3/bin'
                mvn package -DskipTests

                '''
            }
        }                // mvn package -DskipTests.......export MAVEN_HOME='/opt/apache-maven-3.6.3/bin'
        stage('TestMVN') {
            if (!skipTests)
            script {
                // Uruchomienie testów aplikacji
                sh  'mvn verify'
                // Importowanie wyników testów które sie nie robią
                junit allowEmptyResults: true,
                testResults: '**/target/surefire-reports/*.xml',
                skipPublishingChecks: true
            }
        }
        stage('InstallMVN') {
            if (!skipInstall)

                // Install artefakt w lokalnym repo .m2
                sh 'mvn install -DskipTests'
            
        }
    }
}
}