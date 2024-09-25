pipeline {
    agent any

    stages {
        stage('Parallel Tasks with Random Sleep') {
            steps {
                script {
                    // Initialize an empty map for tasks
                    def tasks = [:]
                    def random = new Random() // Create an instance of Random

                    // Define a function to generate a random sleep time in minutes
                    def getRandomSleepTimeInMinutes() { ->
                        return random.nextInt(5) + 1 // Random sleep between 1 and 5 minutes
                    }

                    // Add tasks to the map with random sleep
                    tasks['Task 1'] = {
                        def sleepTime = getRandomSleepTimeInMinutes()
                        echo "Task 1 will start in ${sleepTime} minutes..."
                        sleep sleepTime * 60 // Convert minutes to seconds
                        echo 'Executing Task 1...'
                        sleep 3 // Simulating task duration
                        echo 'Task 1 completed.'
                    }
                    tasks['Task 2'] = {
                        def sleepTime = getRandomSleepTimeInMinutes()
                        echo "Task 2 will start in ${sleepTime} minutes..."
                        sleep sleepTime * 60 // Convert minutes to seconds
                        echo 'Executing Task 2...'
                        sleep 5 // Simulating task duration
                        echo 'Task 2 completed.'
                    }
                    tasks['Task 3'] = {
                        def sleepTime = getRandomSleepTimeInMinutes()
                        echo "Task 3 will start in ${sleepTime} minutes..."
                        sleep sleepTime * 60 // Convert minutes to seconds
                        echo 'Executing Task 3...'
                        sleep 2 // Simulating task duration
                        echo 'Task 3 completed.'
                    }

                    // Execute all tasks in parallel
                    parallel tasks
                }
            }
        }
    }
}
