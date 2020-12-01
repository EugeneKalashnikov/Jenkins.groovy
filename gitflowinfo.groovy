import hudson.model.*
 
for(item in Hudson.instance.items) {
  prop = item.getProperty(ParametersDefinitionProperty.class)
  if(prop != null) {
    println("--- Parameters for " + item.name + " ---")
    for(param in prop.getParameterDefinitions()) {
      try {
        println(param.name + " " + param.defaultValue)
      }
      catch(Exception e) {
        println(param.name)
      }
    }
    println()
  }
}
node {
    //def project = 'Netflix/asgard'
    //def branchApi = new URL("https://api.github.com/repos/${project}/branches")
    //def branches = new groovy.json.JsonSlurper().parse(branchApi.newReader())
    stage('Clone sources') {
        println("branches")
    }
}
