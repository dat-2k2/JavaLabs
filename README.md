# Configuration
System: Windows 11
## Install JDK, Maven, Git
- JDK 17: https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
- Maven: https://maven.apache.org/download.cgi
- Git: https://git-scm.com/downloads

To avoid sensitive characters in path, recommend to create a folder with a "safe" name to install all packages. 

After downloading, install those packages and add PATH to make them accessible. 

Check the version:
```
java --version 
mvn --version
git --version
```
## Create project
- Create a Git repo online, then clone it to local folder by
```
git clone https://github.com/dat-2k2/JavaLabs.git
```

- Go to the cloned git folder, initialize a Maven project with this command:
 ``` mvn archetype:generate -DgroupId=[main package] -DartifactId=[repo name] -DarchetypeArtifactId=[project template] -DinteractiveMode=false ``` where:
  - main package = ru.spbstu.telematics.java
  - repo name = JavaLabs
  - project template = maven-archetype-quickstart
 

- After initialization, create a repo on Git, then use these commands to link the local repo to the upstream branch:
```
git remote add origin [repo link] 
(this will create the local branch "master", which conflicts with the upstream branch "main")
git branch -m master main 
(change the name of branch if needed)
git pull origin main --allow-unrelated-histories (allow mismatched histories)
```
From now you can commit as usual. 

- To execute jar file, you need to include *maven jar plugin* into pom.xml
```
 <build>
  <plugins>
    <plugin>
      <!-- Build an executable JAR -->
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-jar-plugin</artifactId>
      <version>3.1.0</version>
      <configuration>
        <archive>
          <manifest>
            <mainClass>ru.spbstu.telematics.java.App</mainClass>
          </manifest>
        </archive>
      </configuration>
    </plugin>
  </plugins>
</build>
```
# Execution
Using jar execution:
```
java -jar target\JavaLabs-1.0-SNAPSHOT.jar [command]
```
# Program Structure
Each laboratory (short. *lab*) is put in a separated subpackage of the main pack *ru.spbstu.telematics.java*, named as **lab1, lab2,**... 

The class **App** mocks the CLI, which navigates the program to the respective task by the first argument. For example, the command argument for *Lab 1* is **ow** (overwrite).

```
public class App 
{	// main function
    public static void main(String[] args){
		if ("ow".equals(args[0])){
			Lab1.overwriteFile(args[1], args[2]);		
		}
    }

}
```

The testing class using package **junit** contains every methods to test all functions. 

## Lab 1
This lab requires to write a program that helps overwrite an *existed* file with a text. 
### Overwriting method
The overwriting method open a file with parameter *pathName*, then overwrite text from parameter *text*. During execution it also needs to handle the case of nonexisted file.

The method returns 0 if succeeds, otherwise 1 if the file doesn't exist, or else -1. 

```
    public static int overwriteFile(String pathName, String buffer){
        try {
            if (new File(pathName).exists() == false){
                throw new FileNotFoundException();
            }
            FileWriter writer = new FileWriter(pathName,false);
            writer.write(buffer);
            writer.close();
            return 0;
        } catch (FileNotFoundException e){
            System.out.println("File not found ");
            return 1;
        } catch (Exception e){
            System.out.println("An error occured");
            e.printStackTrace();
            return -1;
        }
    } 
```

### Main method
The main function executes the main function of package **Lab 1** if it receives argument **ow**. 

```
		if ("ow".equals(args[0])){
			Lab1.overwriteFile(args[1], args[2]);		
		}
```

### Testing
We need to test the general case of overwriting and the case of non-existed file. 

#### Overwriting
Prepare a file, write some data to it, then run the overwriting method *Lab1.overwriteFile*, read the new data and check whether they are the same with the overwritten. Here used *File*, *FileWriter* and *FileReader*
```
	@Test //test the main function
	public void testOverWrite(){

		//create new file
		String testPath = new String("tmp.txt");
		File fileTest = new File(testPath);
		try{
			FileWriter writerTest = new FileWriter(testPath);
			//write a text to file
			writerTest.write("this is a test");
			writerTest.close();
		}
		catch(IOException e){
			System.out.println("An error occured. ");
			e.printStackTrace();
		}

		//try overwrite
		String overwriteString = new String("this is the overwritten data");
		Lab1.overwriteFile(testPath,overwriteString);

		//prepare a buffer to read the new data
		File tmp = new File(testPath);
		char[] cbuff = new char[(int)tmp.length()];

		// read the new overwritten data
		try{
			FileReader readerTest = new FileReader(testPath); 
			//read text from the file
			readerTest.read(cbuff);
			readerTest.close();
		}
		catch(IOException e){
			System.out.println("An error occured. ");
			e.printStackTrace();
		}

		//delete the file
		fileTest.delete();

		//check if the data is overwritten successfully
		assert(overwriteString.equals(new String(cbuff)));
	};

```
#### FileNotFound
Test if the FileNotFound run normal, just check if the method returns 1. 

```````
    @Test	// test if the nonexisted File case is covered. 
    public void testFileNotFound(){
		assert(Lab1.overwriteFile(new String(""), new String("")) == 1);
    }

```
