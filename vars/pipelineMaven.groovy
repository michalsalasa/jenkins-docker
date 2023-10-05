def call(Map settings = [:]) {
    // node {
    //     ansiColor('xterm')
    //     {
    //     def skipTests = settings.get('skipTests', false)
    //     def skipInstall = settings.get('skipInstall', false)

    //         // stage('Download Source Code') {
    //         //         // checkout ([$class: "GitSCM", branches: [[name: 'main']], userRemoteConfigs:[[url: "https://github.com/michalsalasa/spring-petclinic.git"]]])
    //         //         checkout scm
    //         }
            stage('Build') {
                timestamps {
                    // Zbudowanie kodu
                    sh 'mvn package -DskipTests'
                }
            }
        //     stage('Test') {
        //         if (!skipTests)
        //         script {
        //             echo "test przeszedl"
        //             }
        //         }
            
        //     stage('Install') {
        //         if (!skipInstall)
 
        //             // Instalowanie artefaktu w lokalnym repozytorium .m2
        //             sh 'mvn install -DskipTests'
        // }
    }
}