pipeline {  
    agent any  
    stages {  
            stage ('Git-Checkout') {  
                steps{
                    git 'https://github.com/PavanGandham/CI-CD-Project-1.git'
                    echo "Checkout successful";
                } 
            }
            stage ('Compile') {  
                steps{
                    sh 'mvn compile'
                    echo "test successful";
                    
                } 
            }
            stage ('Build') {  
                steps{
                    sh 'mvn clean'
                    sh 'mvn package'
                    echo "build successful";
                    
                } 
            }
            stage ('Test') {  
                steps{
                    sh 'mvn test'
                    echo "test successful";
                } 
            }
            
        stage ('Deploy') {
            steps{
            deploy adapters: [tomcat9(credentialsId: '88488dca-c16a-4bbb-8a95-abb46f153b57', path: '', url: 'http://ec2-54-211-192-201.compute-1.amazonaws.com:8081')], contextPath: 'jenkins_calci', war: '**/*.war'
            echo "Deploy successful";
            }
        }
        stage ('Monitor') { 
            steps{ 
                echo "Now you can monitor!";
            }
        }
    }
}
