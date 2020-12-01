def repourl = 'https://github.com/EugeneKalashnikov/DockerBuilds.git'
properties([
  parameters([
    [$class: 'CascadeChoiceParameter', 
      choiceType: 'PT_SINGLE_SELECT', 
      description: 'Select environment',
      filterLength: 1,
      filterable: false,
      name: 'Environment', 
      script: [
        $class: 'GroovyScript', 
        script: [
          classpath: [], 
          sandbox: false, 
          script: 
          '''
def gettags = ("git ls-remote -t -h https://github.com/EugeneKalashnikov/DockerBuilds.git").execute()
return gettags.text.readLines().collect { 
  it.split()[1].replaceAll('refs/heads/', '').replaceAll('refs/tags/', '').replaceAll('/^/{/}+', '')
}
          '''
        ]
      ]
    ],
    [$class: 'CascadeChoiceParameter', 
      choiceType: 'PT_SINGLE_SELECT', 
      description: 'Select Site',
      filterLength: 1,
      filterable: false,
      referencedParameters: 'Environment',
      name: 'Site', 
      script: [
        $class: 'GroovyScript', 
        script: [
          classpath: [], 
          sandbox: false, 
          script: 
            '''if (Environment.equals("Development")){
    def command = $/ git log --pretty=oneline /$
        def proc = command.execute()
        return proc.text.readLines()
}
else if(Environment.equals("Production")){
    def command = $/ git log --pretty=oneline /$
        def proc = command.execute()
        return proc.text.readLines()
}'''
        ]
      ]
    ],
    [$class: 'CascadeChoiceParameter', 
        choiceType: 'PT_SINGLE_SELECT', 
        description: 'Select Folder. \'.\' - Remove all site folder',
        filterLength: 1,
        filterable: false,
        referencedParameters: 'Environment, Site',
        name: 'Folder', 
        script: [
          $class: 'GroovyScript', 
          script: [
            classpath: [], 
            sandbox: false, 
            script: 
              '''if (Environment.equals("Development")){
    def command = $/ git log --pretty=oneline /$
        def proc = command.execute()
        return proc.text.readLines()
}
else if(Environment.equals("Production")){
    def command = $/ git log --pretty=oneline/$
        def proc = command.execute()
        return proc.text.readLines()
}'''
        ]
      ]
    ],
    [$class: 'BooleanParameterDefinition', 
      description: 'Set this to confirm deletion',
      name: 'Confirm',
      defaultValue: false
    ]
  ])
])
