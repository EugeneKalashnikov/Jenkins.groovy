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
            'return[\'Development\',\'Production\']'
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
    def command = $/ssh tech@192.168.1.101 sudo ls /var/www/html /$
        def proc = command.execute()
        return proc.text.readLines()
}
else if(Environment.equals("Production")){
    def command = $/ssh tech@192.168.1.102 sudo ls /var/www/html /$
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
    def command = $/ssh centos@192.168.1.101 sudo ls -a /var/www/html'/'${Site} | grep -v '\\.\\.' /$
        def proc = command.execute()
        return proc.text.readLines()
}
else if(Environment.equals("Production")){
    def command = $/ssh centos@192.168.1.102 sudo ls -a /var/www/html'/'${Site} | grep -v '\\.\\.'/$
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
