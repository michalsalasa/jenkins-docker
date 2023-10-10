def call(Map settings = [:]) {
    node {
        ansiColor('xterm')
        {
        def skipTests = settings.get('skipTests', false)
        def skipInstall = settings.get('skipInstall', false)
    environment {
        M2_HOME = '/usr/share/java/maven-3'
        MAVEN_HOME='/opt/apache-maven-3.6.3/bin'
        PATH = "${env.M2_HOME}/bin:${env.PATH}"
        // skipTests = params.skipTests ?: false
        // skipInstall = params.skipInstall ?: false
    }
        // stage('Download Source Code') {
        //         checkout scm
        // }

        stage('BuildMVN') {
            timestamps {
                // Zbudowanie kodu
                MAVEN_HOME='/opt/apache-maven-3.6.3/bin'
                sh '''
                export MAVEN_HOME='/opt/apache-maven-3.6.3/bin'

                mvn package -DskipTests
                '''
            }
        }
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