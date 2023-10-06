def call(Map settings = [:]) {
    node {
        ansiColor('xterm')
        {
        def skipTests = settings.get('skipTests', false)
        def skipInstall = settings.get('skipInstall', false)

        // stage('Download Source Code') {
        //         checkout scm
        // }

        stage('BuildMVN') {
            timestamps {
                // Zbudowanie kodu
                sh '''
                export MAVEN_HOME='/opt/apache-maven-3.6.3/bin'

                pwd
                ls
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