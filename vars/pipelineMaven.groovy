def call(Map settings = [:]) {
    node {
        ansiColor('xterm')
        {
        def skipTests = settings.get('skipTests', false)
        def skipInstall = settings.get('skipInstall', false)

        stage('BuildMVN') {
            steps {
                // Zbudowanie kodu
                sh 'mvn package -DskipTests'
            }
        }
        stage('TestMVN') {
            if (!skipTests)
            steps {
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
            steps {
                // Install artefakt w lokalnym repo .m2
                sh 'mvn install -DskipTests'
            }
        }
    }
}
}