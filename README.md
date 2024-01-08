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

# Документация
См. [документация](https://dat-2k2.github.io/JavaLabs/docs/)

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

## Lab 2

В этой лабораторной работе требуется реализовать *Bag* на Java, который должен включать методы *size*, *contains*, *add*, *remove*, *get* и некоторые другие, если необходимо.

*Bag* - это ** неупорядоченная ** коллекция, которая принимает дубликаты. Это помогает пользователям быстро получать статистику данных.

Базовой реализацией *Bag* является *HashBag*, использующая *HashMap* в качестве базовой структуры. *HashMap* поддерживает получение количества элементов со сложностью O(1).

### Структура программы

<h4>Главная</h4>
<p>
    <img src="resource/lab2UML.png" alt>
    </img>
</p>

*MyArrayList* и *MyLinkedList*, по сути, не требовались для реализации, вместо этого *HashMap* мог использовать только *Node* и массив. Однако должен быть добавлен метод изменения размера *MyMap* и должны быть реализованы основные методы LinkedList.

Сначала нужно реализовать *MyMap*, тогда *MyBag* будет * Map* с ключом в качестве элемента и значением в качестве количества дубликатов.

<h4>Итерация</h4>

Выполнить итерацию по всем элементам *MyBag*, включая его дубликаты. Однако действие, примененное к каждому элементу, также повлияет на другие дубликаты, поскольку возвращаемое значение является ключом записи элемента в хэш-таблице.

<h4>Добавить</h4>

Товар, добавленный в *MyBag*, увеличит его количество там или создаст новую запись с количеством 1, если для этого товара не было никакой записи. Из-за хэширования заказ на добавление не зарезервирован.

<h4>Удалить</h4>

При удалении товара удаляются все его количества в пакете или удаляется определенное количество, если указано количество. 

<h4>Получить (getCount)</h4>

Попасть в *MyBag* означает получить его количество. Количество непредставленных элементов равно 0.

### Тест

Используйте допустимый *HashBag* из *Common Apache* для проверки *MyHashBag*. Класс *A* с двумя дочерними классами *B* и *C* был создан для обеспечения универсальности тестирования. Тест включает в себя 3 основных метода, описанных выше, и сравнивается с действительными методами *HashBag*.

## Lab 3

**Покупатели**. В отделе сыра в супермаркете постоянно собираются голодные покупатели. Есть два типа покупателей: **торопливые покупатели**, которые стремятся опередить других и требуют обслуживания; и **спокойные покупатели**, которые терпеливо ждут обслуживания. Запрос на обслуживание обозначается действием “get Cheese”, а окончание обслуживания обозначается действием “Cheese”. Сыр всегда есть в наличии, и постоянное количество покупателей - два **торопливых** и два **спокойных**. Каждый клиент должен быть создан как отдельный поток, который входит в очередь, обслуживается и перестает работать.

### Структура программы
<h4>Главная</h4>
<p>
    <img src="resource/lab3UML.png" alt>
    </img>
</p>

*Buyer* инициализируется именем и очередью *allBuyer*. В зависимости от поведения, он будет добавлен в первую или последнюю очередь. Каждый покупатель будет ждать, пока его указатель поворота не установит значение true. Служебный класс *Cashier* одновременно получает первого покупателя в очереди, будит его и продает ему сыр. После этого *Buyer* завершает свою рутинную работу.

Основной метод случайным образом и в конечном итоге генерирует *Buyer* для имитации проблемной сцены.

<h4>Buyer </h4>

Покупатель ждет, пока *isYourTurn* не примет значение true. *функция wait()* должна находиться внутри цикла while.
После того, как кассир разбудит *Покупателя*, приступайте к оформлению заказа. 
```
    @Override
    public void run() {
        //come to the queue
        if (!toQueue(queue)) {
            System.out.println("[" + nameBuyer + "]: Queue full");
            return;
        }

        waitTillTurn();
        //request order
        System.out.println(this.nameBuyer + " get cheese");
        waitTillServed();
        System.out.println(this.nameBuyer + " cheese");
    }
```

<h4>Cashier</h4>

*Cashier* - это служебный класс. Его метод *sell()* выводит первого *Buyer* из очереди, обслуживает его и ждет, пока *Buyer* не завершит свою процедуру.

```
static void sell(BlockingDeque<Buyer> allBuyer){
  Buyer currentBuyer;
  System.out.println("Current queue " + allBuyer);
  currentBuyer = allBuyer.pollFirst();

  //Buyer can join the queue while Cashier is selling so no sync here.
  if (currentBuyer != null){
    System.out.println("Serving "+currentBuyer.nameBuyer);

    //wake it up
    synchronized (currentBuyer){
      currentBuyer.setYourTurn(true);
      currentBuyer.notifyAll();

      waiting(TIME_SERVE);

      currentBuyer.setServed(true);
      currentBuyer.notifyAll();

      //wait till the Buyer exit before serving another
      try {
        currentBuyer.join();
      } catch (InterruptedException e) {
        System.out.println("Buyer "+ currentBuyer.nameBuyer +" is interrupted");
      }
    }
  }
}
```
### Результат
Это решение не учитывает состояние 2 спокойных и 2 торопливых покупателей в очереди, вместо этого оно решает проблему с любой очередью покупателей.


<p>
    <img src="resource/result.png" alt>
    </img>
</p>
