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

Чтобы использовать dependencies (commons-cli) с jar, надо добавить/ заменить плагин *maven-jar-plugin* с *maven-assembly-plugin*.
```
<plugin>
      <artifactId>maven-assembly-plugin</artifactId>
      <configuration>
        <archive>
          <manifest>
            <mainClass>ru.spbstu.telematics.java.App</mainClass>
          </manifest>
        </archive>
        <descriptorRefs>
          <descriptorRef>jar-with-dependencies</descriptorRef>
        </descriptorRefs>
      </configuration>
    </plugin>
```
и построить с командой:
```
mvn clean package assembly:single
```
JAR файл с *commons-cli* заканчивается на *jar-with-dependencies*.

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

```
     public static void overwriteFile(String pathName, String buffer) throws FileNotFoundException{
        //throw exception if the file doesn't exist
        if (!new File(pathName).exists()){
            throw new FileNotFoundException();
        }
        
        // if file already exists, write the new data to it. 
        try {
            FileWriter writer = new FileWriter(pathName,false);
            writer.write(buffer);
            writer.close();
        }
        catch (IOException e){
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }
```

### Главный метод
Главный метод выполняет метод перезаписи пакета **Lab 1** если он передается аргументом **ow**


### Тестирование
Надо тестировать общий случай (перезапись) и случай несуществующего файла. 
#### Перезапись 

Подготовить один файл, туда написать несколько данных, затем запустить метод *Lab1.overwriteFile*, почитать новые данные и проверить одним же ли они с перезаписанными данными. Здесь использовать пакеты *File*, *FileWriter* и *FileReader*.

#### FileNotFound
Проверка нормально ли работает метод при том, что файл несуществует.

[Docs](./doc)
