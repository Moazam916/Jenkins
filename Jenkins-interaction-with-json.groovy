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
                    // jsonSlurper helps to convert data into map using this class
                    def jsonData = jsonSlurper.parseText(jsonContent)
                    echo "printing the type of json data: ${jsonData.getClass()}"
                    echo "printing the input json file: ${jsonData}"
                    // Accessing JSON properties (assuming it's a key-value JSON)
                    // echo "Value of key 'name': ${jsonData.name}"
                    // echo "Value of key 'version': ${jsonData.version}"
                    //Converting Data back to json from map
                    def maptojson = groovy.json.JsonOutput.toJson(jsonData)
                    // enabling pretty print on converted json
                    def prettyjson = groovy.json.JsonOutput.prettyPrint(maptojson)
                    echo "the outputted json file: ${prettyjson}"
                }
            }
        }
    }
}
