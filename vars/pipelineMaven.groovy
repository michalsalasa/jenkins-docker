def call(Map settings = [:]) {
    node {
        ansiColor('xterm')
        {
        def skipTests = settings.get('skipTests', false)
        def skipInstall = settings.get('skipInstall', false)

            stage('Download Source Code') {
                    // checkout ([$class: "GitSCM", branches: [[name: 'main']], userRemoteConfigs:[[url: "https://github.com/michalsalasa/spring-petclinic.git"]]])
                    checkout scm
            }
            stage('Build') {
                timestamps {
                    // Zbudowanie kodu
                    sh 'mvn package -DskipTests'
                }
            }
            stage('Test') {
                if (!skipTests)
                script {
                    // try {
                    //     if (!settings.skipTests){
                    // Uruchomienie testów aplikacji
                    sh  'mvn verify'
                    // Importowanie wyników testów
                    junit '**/target/surefire-reports/*.xml'
                    echo "test przeszedl"
                    }
                //     catch (Exception e) {
                //     archiveArtifacts allowEmptyArchieve: true, artifacts: '**/target/surefire-reports/*.xml'
                // }
                }
            
            stage('Install') {
                if (!skipInstall)
 
                    // Instalowanie artefaktu w lokalnym repozytorium .m2
                    sh 'mvn install -DskipTests'
        }
    }
    }
}