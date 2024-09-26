def my_map = [:] // intializing the new map
def my_array = ["alpha","bravo","charlie"]
pipeline {
    agent any

    stages {
        stage('Read JSON') {
            steps {
                script {
                    // Reading the JSON file
                    def userInput = input(
                        id: 'userInput', message: 'Please provide your inputs', parameters: [
                            string(defaultValue: '', description: 'Enter a name', name: 'name'),
                            string(defaultValue: '', description: 'Enter a version', name: 'version')
                        ]
                    ) // end of user's input
                    // adding the recieve user input into map
                    // syntax nameofthemap['<name of key we want to insert>'] = <value>
                    my_map['App_Name'] = userInput['name']
                    my_map['App_version'] = userInput['version']
                    echo "printing the map: ${my_map}"
                    def jsonContent = readFile('sample_data/sample-poc.json')
                    // Parsing JSON content
                    def jsonSlurper = new groovy.json.JsonSlurper()
                    // jsonSlurper helps to convert data into map using this class
                    def jsonData = jsonSlurper.parseText(jsonContent)
                    echo "printing the type of json data: ${jsonData.getClass()}"
                    echo "printing the input json file: ${jsonData}"
                    jsonData['APP_info'] = my_map // adding new map to json
                    echo "printing my array: ${my_array}"
                    jsonData['Dummy_array'] = my_array // adding new array to json
                    // Optional: If appending to an existing list in the JSON
                    // jsonData['existingListKey'] += newList
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
