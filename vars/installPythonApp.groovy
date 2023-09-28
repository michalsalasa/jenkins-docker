def call(String firstname="Obama", String lastname="Barrack") {
    script {
        sh """
            cd myapp
            python3 hello.py
            python3 hello.py --name=Michal
        """
    }
    return "Yes Man"
}