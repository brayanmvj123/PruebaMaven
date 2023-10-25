

def fd = "--oneline -n 1 "
def String[] Archivo = ["MODULE-OPAPCDT","MODULE-OPL-AUTH","MODULE-OPL-SERVFRONT","MODULE-OPLBATCH","MODULE-OPLCDECEVAL","MODULE-OPLCONSULTA","MODULE-OPLMCRM","MODULE-OPLSIMULADOR","MODULE-OPLSSQLS","MODULE-OPLTRADUCTOR","MODULE-OPLBATCHANUAL","MODULE-OPLBATCHDIARIOS","MODULE-OPLBATCHMENSUAL","MODULE-OPLBATCHSEMANAL","MODULE-OPLEMAIL","MODULE-OPLCONTROL","MODULE-OPLMDS","MODULE-OPLFRONTEND","MODULE-OPLOFICINA","MODULE-OPLBATCHOCASIONAL","MODULE-OPLCOFNAL", "MODULE-OPLPAGOSAUT","MODULE-OPLDEBITO", "MODULE-OPLREPORTE"]
def Opalo = []
def Opalo2 = []
def exists  
def RAMA= SELECTRAMA
def Valor
def conta = 0
def con
def compare
def module
def commit_total
properties([buildDiscarder(logRotator(artifactDaysToKeepStr: '',
			artifactNumToKeepStr: '', 
			daysToKeepStr: '', numToKeepStr: '10')), 
			disableConcurrentBuilds(),
			pipelineTriggers([pollSCM('H/5 * * * *')])])

node {
	  // ****** stage para verificar cambios en los proyectos antes de hacer checkout ******
               dir("${env.WORKSPACE}")
				{
            		 exists = fileExists 'Jenkinsfile'
                }
        stage('pruebasInicial')
        {
			if (exists) 
			{
				/*for(int i=0; i<Archivo.length; i++)
				{
				     module =   fileExists Archivo[i]+'/pom.xml'
				   
				    if (module)
				    {
    					//println Archivo[i]
    					Valor = bat label: '',returnStdout: true, script:'git log '+ fd +Archivo[i]
    					def valorCommit = Valor.split(' ')
                   		//println valorCommit[6].replaceAll("\\s","")
    					Opalo[i] = valorCommit[6].replaceAll("\\s","")
                        commit_total= commit_total+","+valorCommit[6].replaceAll("\\s","")
				    }
				}*/
                echo 'ok'
			}
		
        } 
        
		// ****** stage para traer cambios de la rama DEV para el proyecto OPALO ******
		stage('checkout'){
						checkout([$class: 'GitSCM', branches: [[name: RAMA]],
						doGenerateSubmoduleConfigurations: false, 
						extensions: [], submoduleCfg: [], 
						userRemoteConfigs: [[credentialsId: '4a06b86b-f83e-4d79-a6c1-0d08f44c8a10',
						url: 'https://github.com/bdb-dns/bbog-bth-opalo-adapter.git']]])
					
						}

		// ****** stage para verificar cambios en los proyectos, parecido al paso inicial. Ahora despues del checkout ******				
        stage('Pruebas')
        {
            for(int i=0; i<Archivo.length; i++)
            {
                 module =   fileExists Archivo[i]+'/pom.xml'
				
				if (module)
				{
                   // println Archivo[i]
    				Valor = bat label: '',returnStdout: true, script:'git log '+ fd +Archivo[i]
    				def valorCommit2 = Valor.split(' ')
                   	//println valorCommit2[6].replaceAll("\\s","")
    				Opalo2[i] = valorCommit2[6].replaceAll("\\s","")
    				conta = conta+1
    				if(conta==1){
                    commit_total= Opalo2[i]
    				}else{commit_total= commit_total+","+Opalo2[i]}
                 // writeFile file: "D:\\Automatizacion\\hash\\${JOB_NAME}.txt", text: commit_total
				}
            }
        }

		// ****** stage para comparar los dos array Opalo de los stages anteriores, para ejecutar build donde sea necesario ******
		stage('comparacion')
		{
            result = readFile "D:\\Automatizacion\\hash\\${JOB_NAME}.txt"
			con = result.split(',')
		   for(int i=0; i<Archivo.length; i++)
		   {
			   try {compare = con[i]}catch (ex){compare =123}
			    if (compare != Opalo2[i])
			    {
					echo "Se construira "+ Archivo[i]
				    build job: 'bdb-dns-OPALOBDB-'+Archivo[i], parameters: [string(name: 'BRANCH_NAME', value: RAMA), string(name: 'MODULOOPALO', value: Archivo[i])], propagate: false
                  
                }
		   }
		   writeFile file: "D:\\Automatizacion\\hash\\${JOB_NAME}.txt", text: commit_total
		}

	}

