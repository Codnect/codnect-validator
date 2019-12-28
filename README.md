# Codnect Validator
Codnect Validator is open source library that provides Bean Validation constraint that allows to use **Spring Expression Language (SpEL)**.

Usage
=====
The following are different ways of usage of **@Assert** annotation. 

Using Standard
--------------

```java
public class Sample {

    @Assert("#this == null")
    private String testFieldForNullCheck;

    @Assert("#this != null")
    private String testFieldForNotNullCheck;

    @Assert("'hello'.equals(#this)")
    private String testFieldForEquals;

    @Assert("!'hello'.equals(#this)")
    private String testFieldForNotEquals;

    @Assert("#this == 5")
    private int testFieldForEqual;

    @Assert("#this > 5")
    private int testFieldForGreaterThan;

    @Assert("#this >= 5")
    private int testFieldForGreaterThanOrEqualTo;

    @Assert("#this < 5")
    private int testFieldForLessThan;

    @Assert("#this >= 5")
    private int testFieldForLessThanOrEqualTo;

    @Assert("#this >= 5 AND #this <= 9")
    private int testFieldForAndOperator;

    @Assert("#this == 5 OR #this == 9")
    private int testFieldForOrOperator;
    
    ...
}
```
If you like to apply your assert expression under some conditions, you can use the attribute of **applyIf**. Similary, you can specify **SpEL expression** within **applyIf**.

```java
public class Sample {

    @Assert(value = "#this.bytes.length < 80", applyIf = "#this != null")
    private String movieName;
    
    @Assert(value = "#this > 0.0 AND #this < 10.0", applyIf = "#this != null")
    private Double imdb;
    
    ....
```
In addition, you can apply **@Assert** annotations on your class level.

```java

@Assert(value = "#this.movieName != null AND (#this.imdb > 0.0 AND #this.imdb < 10.0)")
public class Sample {

    private String movieName;
    
    private Double imdb;
    
    ....
```
Using Assert Helpers
--------------------
If you would like to use your methods within assert expression, you need to put your class into the attribute of **assertHelpers**.
Notice that you can only use **static methods** within assert expression.

```java
public class Sample {

    @Assert(value = "#isNull(#this)", assertHelpers = AssertHelpers.class)
    private String name;

    @Assert(value = "#isNotNull(#this)", assertHelpers = AssertHelpers.class)
    private String surname;

    @Assert(value = "#length(#this) == 5", assertHelpers = AssertHelpers.class)
    private String address;
    
    ...
}
```
**AssertHelpers.java**
```java
public class AssertHelpers {

    static boolean isNull(Object object) {
        return object == null;
    }

    static boolean isNotNull(Object object) {
        return object != null;
    }

    static int length(String str) {
        return str == null ? 0 : str.length();
    }

}
```
Using Your Beans
--------------------
If you would like to access your beans within assert expression, your bean name should be prefixed with **@** character. 

```java
public class Sample {

    @Assert(value = "@userService.exist(#this)")
    private String userId;

}
```

Maven
-----
If you want to use the codnect validator, just add the dependency below to your project.
```xml
<dependency>
  <groupId>org.codnect.validator</groupId>
  <artifactId>codnect-validator</artifactId>
  <version>1.0-snapshot</version>
</dependency>
```

License
-------
Codnect Validator is open source library released under the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0.html).
