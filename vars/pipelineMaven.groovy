def call(Map settings = [:]) {
  
 
        def skipTests = settings.get('skipTests', false)
        def skipInstall = settings.get('skipInstall', false)

        stage('Download Source Code') {
                // checkout ([$class: "GitSCM", branches: [[name: 'main']], userRemoteConfigs:[[url: "https://github.com/michalsalasa/spring-petclinic.git"]]])
                checkout scm
        }

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