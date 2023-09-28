def call(Map config = [:]) {
// def call(String typ , String level) {
        sh "Typ of pipeline: ${config.typ}. Level of advance: ${config.level}."
}