AUTH = "логин и пароль в Base64"                           
GIT_URL = "url до вашей SCM (https://bitbucket.org/)"                       
PROJECT_NAME = "имя проектной области, где находятся репозитории"

def htmlBuild() {
    html = """
            <html>
            <head>
            <meta charset="windows-1251">
            <style type="text/css">
            div.grayTable {
            text-align: left;
            border-collapse: collapse;
            }
            .divTable.grayTable .divTableCell, .divTable.grayTable .divTableHead {
            padding: 0px 3px;
            }
            .divTable.grayTable .divTableBody .divTableCell {
            font-size: 13px;
            }
            </style>
            </head>
            <body>
        """

    def commitOptions = ""
    getCommitsForMicroservice(MICROSERVICE_NAME).each {
        commitOptions += "<option style='font-style: italic' value='COMMIT=${it.getKey()}'>${it}</option>"
    }
    html += """<p style="display: inline-block;">
        <select id="commit_id" size="1" name="value">
            ${commitOptions}
        </select></p></div>"""

    html += """
            </div>
            </div>
            </div>
            </body>
            </html>
         """
    return html
}

def getCommitsForMicroservice(microserviceRepo) {
    def commits = [:]
    def endpoint = GIT_URL + "/rest/api/1.0/projects/${PROJECT_NAME}/repos/${microserviceRepo}/commits"
    def conn = new URL(endpoint).openConnection()
    conn.setRequestProperty("Authorization", "Basic ${AUTH}")
    def response = new groovy.json.JsonSlurper().parseText(conn.content.text)
    response.values.each {
        commits.put(it.displayId, it.message)
    }
    return commits
}

return htmlBuild()
