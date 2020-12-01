
def project = 'EugeneKalashnikov/DockerBuilds'
def branchApi = new URL("https://api.github.com/repos/${project}/branches")
def branches = new groovy.json.JsonSlurper().parse(branchApi.newReader())
branches.each {
    def branchName = it.name
    def jobName = "${project}-${branchName}".replaceAll('/','-')
    println(branchName)
}
node{
    stage('Clone sources') {
        parameters{ text(name: 'mytextparam', 
                 defaultValue: 'Default lines for the parameter', 
                 description: 'A description of this param')    
        }
        git url: 'https://github.com/jfrogdev/project-examples.git'
    }
