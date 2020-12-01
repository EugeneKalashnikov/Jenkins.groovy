import com.cloudbees.plugins.flow.*;
 
jobs = Jenkins.instance.getAllItems(BuildFlow);
jobs.each { it ->
    it.builds.each { b -> 
        GIT_BRANCH = b.envVars['GIT_BRANCH']
        ( GIT_BRANCH =~ /(?:refs\/remotes\/)?(.+)/ ).each { full,branch ->
            b.displayName = branch + ' (#' + b.number + ')'
        }
    }
}
