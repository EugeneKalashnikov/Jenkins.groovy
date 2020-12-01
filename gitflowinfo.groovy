node {
    // Get Artifactory server instance, defined in the Artifactory Plugin administration page.
    def server = Artifactory.server "SERVER_ID"
    // Create an Artifactory Maven instance.
    def rtMaven = Artifactory.newMavenBuild()
    def buildInfo

    stage('Clone sources') {
        git url: 'https://github.com/jfrogdev/project-examples.git'
    }
}
