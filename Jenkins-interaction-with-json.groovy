pipeline {
    agent any

    stages {
        stage('Read JSON') {
            steps {
                script {
                    // Reading the JSON file
                    def jsonContent = readFile('sample_data/sample-poc.json')

                    // Parsing JSON content
                    def jsonSlurper = new groovy.json.JsonSlurper()
                    def jsonData = jsonSlurper.parseText(jsonContent)
                    echo "printing the input json file: ${jsonData}"
                    // Accessing JSON properties (assuming it's a key-value JSON)
                    echo "Value of key 'name': ${jsonData.name}"
                    echo "Value of key 'version': ${jsonData.version}"
                }
            }
        }
    }
}
