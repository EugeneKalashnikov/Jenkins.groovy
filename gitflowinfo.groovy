node {

    stage('Clone sources') {
        git url: 'https://github.com/jfrogdev/project-examples.git'
    }
}
