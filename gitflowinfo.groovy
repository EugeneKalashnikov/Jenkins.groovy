
repoName = "DockerBuilds"

def getBranchesFromRepoName(repoName) {
    def branches = []
    def endpoint = GIT_URL + "/rest/api/1.0/projects/${PROJECT}/repos/${repoName}/branches"
    def conn = new URL(endpoint).openConnection()
    //conn.setRequestProperty("Authorization", "Basic ${AUTH}")
    def response = new groovy.json.JsonSlurper().parseText(conn.content.text)
    response.values.each {
        branches.add(it.displayId)
    }
    return branches
}
return getBranchesFromRepoName(repoName)
