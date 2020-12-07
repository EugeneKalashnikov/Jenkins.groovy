
properties([
    parameters([
        gitParameter(branch: '',
                     branchFilter: 'origin/(.*)',
                     defaultValue: 'master',
                     description: '',
                     name: 'BRANCH',
                     visible: false,
                     quickFilterEnabled: false,
                     selectedValue: 'NONE',
                     sortMode: 'NONE',
                     tagFilter: '*',
                     useRepository: 'https://github.com/EugeneKalashnikov/DockerBuilds.git',
                     type: 'PT_BRANCH'),
        string(name: 'COMMIT')
    ])
])

pipeline {
    agent any
    stages {
        stage('Initial') {
            steps {
                checkout([$class: 'GitSCM', 
                          branches: [[name: "${params.TAG}"]], 
                          doGenerateSubmoduleConfigurations: false, 
                          extensions: [], 
                          gitTool: 'Default', 
                          submoduleCfg: [], 
                          userRemoteConfigs: [[url: 'https://github.com/EugeneKalashnikov/DockerBuilds.git']]
                        ])
            }
        }
    }
}
