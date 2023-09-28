def call(Map config = [:]) {
    script{
        sh """
            echo rodzaj of pipeline ${config.rodzaj} a Level of advance ${config.level}.
        """
    }
}