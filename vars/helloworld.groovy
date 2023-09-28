def call(String type,String level) {
    script {
        sh "Type of pipeline: ${type}. Level of advance: ${level}."
    }
}