pipelineJob("item1") {
	description()
	keepDependencies(false)
	parameters {
		activeChoiceParam("Branche") {
			description()
			groovyScript {
				script("return [\"master\",\"dev\"]")
				fallbackScript("")
			}
			choiceType("SINGLE_SELECT")
			filterable(false)
		}
		stringParam("repourl", "https://github.com/EugeneKalashnikov/DockerBuilds.git", "")
		stringParam("chechouthash", "", "")
	}
	definition {
		cpsScm {
"""println "\${params.repourl}"
println "\${params.chechouthash}"
node {
    sh label: '1', script: "git checkout \${params.Branche}"
    sh label: '2', script: 'git log'
    sh label: '3', script: "git checkout \${params.chechouthash}"
}"""		}
	}
	disabled(false)
	configure {
		it / 'properties' / 'jenkins.model.BuildDiscarderProperty' {
			strategy {
				'daysToKeep'('5')
				'numToKeep'('5')
				'artifactDaysToKeep'('-1')
				'artifactNumToKeep'('-1')
			}
		}
	}
}
