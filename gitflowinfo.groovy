node {
    def project = 'Netflix/asgard'
    def branchApi = new URL("https://api.github.com/repos/${project}/branches")
    def branches = new groovy.json.JsonSlurper().parse(branchApi.newReader())
    stage('Clone sources') {
        git url: 'https://github.com/Netflix/asgard.git'
    }
}
