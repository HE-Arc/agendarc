pipeline
{
    agent any    
    stages
	{
        stage('Build')
		{
            agent
			{
              docker
			  {
               image 'maven:3.6.3-jdk-11-slim'
              }
            }
            steps
			{
                echo 'Building..'
			    sh '(cd ./agendarc/; mvn clean package)'
		        stash name: "app", includes: "**" //Sauvegarde tout les fichier sous le nom "app" pour un utilisation ultérieure
            }
        }

        stage('QualityTests')
        {
            agent
            {
                docker
                {
                    image 'maven:3.6.3-jdk-11-slim'
                }
            }
            steps
            {
                echo 'Quality Tests...'
                unstash "app"
                sh '(cd ./agendarc/; mvn clean test)' //Ce déplace dans le répertoire du projt et nettoie les fichiers généré par maven
                sh '(cd ./agendarc/; mvn sonar:sonar)'
            }
        }
        
        stage('IntegrationTests')
		{
            agent
            {
                docker
                {
                    image 'lucienmoor/katalon-for-jenkins:latest'
				    args '-p 8888:8080'
                }
            }
            steps
			{
                echo 'Integration Tests...'
                unstash "app"
                sh 'java -jar ./agendarc/target/agendarc-0.0.1-SNAPSHOT.jar >/dev/null 2>&1 &'
                sh 'sleep 30'
                sh 'chmod +x ./runtest.sh'
                sh './runtest.sh' //Lancement des tests d'integration

                cleanWs() //Netoyage du workspace
            }
        }
    }

    post
	{
        always
		{
            echo 'Always clean up'
            deleteDir()
        }
    }
}
