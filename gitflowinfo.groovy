pipelineJob('browse-branches') {
  definition {
    cpsScm {
      scm {
        git {
          remote {
            url('https://github.com/EugeneKalashnikov/DockerBuilds.git')
          }
          branch('*/master')
        }
      }
      lightweight()
    }
  }
}
