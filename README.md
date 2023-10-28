# Конфигурация
ОС: Windows 11
## Установка JDK, Maven, Git
- JDK 17: https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
- Maven: https://maven.apache.org/download.cgi
- Git: https://git-scm.com/downloads

Чтобы избежать чувствительных символов в пути, рекомендуем создать папку с "безопасным" именем для установки всех пакетов.

После загрузки установите эти пакеты и добавьте PATH, чтобы сделать их доступными.
Проверить версию:
```
java --version 
mvn --version
git --version
```
## Создать проект
- Перейдите в клонированную папку git, инициализируйте проект Maven с помощью этой команды:
 ``` mvn archetype:generate -DgroupId=[главный пакет] -DartifactId=[имя репо] -DarchetypeArtifactId=[шаблон проекта] -DinteractiveMode=false ``` при этом:
  - главный пакет = ru.spbstu.telematics.java
  - имя репо = JavaLabs
  - шаблон проекта = maven-archetype-quickstart
 

- После инициализации создайте отчет в Git, затем используйте эти команды, чтобы связать локальное репозиторий с вышестоящей веткой:
```
git init (инициализировать проект git)
git remote add origin [repo link] 
(это создаст локальную ветку "master", которая конфликтует с вышестоящей веткой "main".)
git branch -m master main 
(при необходимости измените название ветки)
git pull origin main --allow-unrelated-histories (разрешить несовпадающие истории)
```
Затем вы можете совершать действия как обычно.


- Чтобы запустить jar-файл, вам необходимо включить *maven jar plugin* info pom.xml
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
# Выполнение
Использование jar-выполнения:
```
java -jar target\JavaLabs-1.0-SNAPSHOT.jar [команда]
```
# Структура программы
Каждая лабораторная работа (сок. *лаб*) находится в отдельном подпакете общего пакета *ru.spbstu.telematics.java*, называемая **lab1, lab2,**... 

Класс **App** имитирует CLI, где он управляет программу на соответственную задачку при первом аргументе. Например, главный аргумент для *Лаб 1* - **ow** (перезапись)

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
Тестовый класс использующий пакет **junit** содержает все методы проверки всех функций. 

## Лаб 1
Этот лаб требует чтобы написал программу, которая перезаписывает существующий файл с заданным текстом. 
### Метод перезаписи

Метод перезаписи откроет файл с имени *pathName*, затем перезапишет его с *buffer*. При выполнения надо также обрабатывать случай несуществующего файла. 

The method returns 0 if succeeds, otherwise 1 if the file doesn't exist, or else -1. 
Метод возвращает 0 если успешно, иначе 1 если файл несуществует, иначе -1. 

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

### Главный метод
Главный метод выполняет метод перезаписи пакета **Lab 1** если он передается аргументом **ow**

```
		if ("ow".equals(args[0])){
			Lab1.overwriteFile(args[1], args[2]);		
		}
```

### Тестирование
Надо тестировать общий случай (перезапись) и случай несуществующего файла. 
#### Перезапись 

Подготовить один файл, туда написать несколько данных, затем запустить метод *Lab1.overwriteFile*, почитать новые данные и проверить одним же ли они с перезаписанными данными. Здесь использовать пакеты *File*, *FileWriter* и *FileReader*.
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
Проверка нормально ли работает метод при том, что файл несуществует (метод возвращает 1).

```````
    @Test	// test if the nonexisted File case is covered. 
    public void testFileNotFound(){
		assert(Lab1.overwriteFile(new String(""), new String("")) == 1);
    }

```
