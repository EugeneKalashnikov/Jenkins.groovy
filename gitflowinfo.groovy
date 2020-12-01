pipelineJob('testpipeline-build') {
	description('Build test docker image, test and push it to local registry')
	definition {
    	cpsScm {
      	scm {
          git {
            branch('origin/master')
            remote {
              url('git@gitlab-selfhosted.org:test-group/sample-project.git')
              credentials('gitlab-creds')
            }
          }
      	}
      	scriptPath('Jenkinsfile')
    	}
    }
}
