def call(Map config = [:]) {
    loadScriptLinux(name: 'installLinux.sh')
    sh "./installLinux.sh ${config.name} ${config.dayOfWeek}"
}