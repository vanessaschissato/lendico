# Lendico IBAN Generator Test

## Usage

- Download project from Github:
```bash
git clone https://github.com/vanessaschissato/lendico.git
```

- Install project to local Maven repository:
```bash
mvn clean install
```

- Add dependency to client project pom.xml:
```xml
<dependency>
	<groupId>de.lendico</groupId>
	<artifactId>iban-generator</artifactId>
	<version>1.0</version>
</dependency>
```

- API
```java
IbanFactory ibanFactory = new IbanFactory();
System.out.println(ibanFactory.factory("DE"));
System.out.println(ibanFactory.factory("AT"));
System.out.println(ibanFactory.factory("NL"));
```

# Technologies
- Maven
- Java 8 
- TestNG

# Approach
- The project was developped following TDD methodology to improve clean code and quality.
- The project organization follows DDD (Domain Driven Design)
- Some Design Patterns was used to better organize the code and to allow easier evolution: **Factory** (IbanFactory, IbanStrategyFactory), **Strategy** (IbanStrategy, BbanStrategy), **Flyweight**: (IbanStrategyFactory)
- To keep the IBAN in-memory storage was used HashSet, since HashSet contains() O(1), unlike ArrayList that is O(N)
