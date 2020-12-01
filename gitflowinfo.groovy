properties([
    parameters([
        gitParameter(branch: '',
                     branchFilter: 'origin/(.*)',
                     defaultValue: 'master',
                     description: '',
                     name: 'BRANCH',
                     quickFilterEnabled: false,
                     selectedValue: 'NONE',
                     sortMode: 'NONE',
                     tagFilter: '*',
                     useRepository: 'https://github.com/EugeneKalashnikov/DockerBuilds.git',
                     type: 'PT_BRANCH')
    ])
])
node {
    git branch: "${params.BRANCH}", url: 'https://github.com/EugeneKalashnikov/DockerBuilds.git'
}
