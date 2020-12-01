def repourl = 'https://github.com/EugeneKalashnikov/DockerBuilds.git'
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
                     useRepository: repourl,
                     type: 'PT_BRANCH'),
         gitParameter(branch1: '',
                     branchFilter: 'origin/(.*)',
                     defaultValue: 'master',
                     description: '',
                     name: 'BRANCH',
                     quickFilterEnabled: false,
                     selectedValue: 'NONE',
                     sortMode: 'NONE',
                     tagFilter: '*',
                     useRepository: repourl,
                     type: 'PT_BRANCH')       
    ])
])
node {
    git branch: "${params.BRANCH}", url: repourl
}
