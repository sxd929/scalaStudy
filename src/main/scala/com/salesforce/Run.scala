package com.salesforce

import java.io.File

import com.github.tototoshi.csv._

import scala.util.Try



object Main extends App {

  println("Hello, world")
  println("the following examples are found online (scala documentation and others)")

}

object Basic extends App {

  println("val and var")
  val x = 1 + 1
  // x = 3 This does not compile.

  var y = 1 + 1
  y = 3 // This compiles because "x" is declared with the "var" keyword.
  println(y * y) // 9

  println("***********")

  println("list")
  val list0 = 1 :: List(2, 3) :: List(4, 5)
  println(list0(1))
  val list1 = List(List(1, 2), List(3), List(), List(4, 5)).flatten
  println(list1)
  println(list1.length)

  println("***********")
  println("option")
  val option1: Option[String] = Some("a")
  val option2: Option[String] = None
  println(option1.get)
  println(option1.getOrElse("b"))
  println(option2.getOrElse("b"))
  println(option1.isDefined)
  println(option1.isEmpty)
  println(option1.map(x => x + "a"))
  println(option2.map(x => x + "a"))

}

object BasicFun extends App {

  println("zip function")

  val abcde = List("a", "b", "c", "d", "e")
  val zipped = abcde.indices zip abcde

  println (zipped)
  println("***********")

  println("filter function")

  val filter1 = abcde.filter(x => x == "a")

  println (filter1)
  println("***********")

  println("map function")

  val map1 = abcde.map(x => x + "a")

  println (map1)
  println("***********")

  println("flatMap function")
  def fun(x: String): List[String] = List("1", x, "2")
  val flatMap1 = abcde.flatMap(x => fun(x))

  println(abcde.map(x => fun(x)))
  println (flatMap1)
  println("***********")

  println("collect function")

  val collect1 = abcde.collect{case "a" => "as"}
  val collect2 = abcde.filter(x => x =="a").map(x => "as")
  println (collect1)
  println (collect2)
  println("***********")

  println("customized function")
  def add(x: Int, y: Int): Int = x + y

  println(add(1, 2))

  val add2 = (x: Int, y: Int) => x + y
  println(add2(1, 2))

}

object TypeMatch extends App {
  val num: Int = 5
  println (num.toString)

  def describe(x: Any): String = {
    x match {
      case num: Int => num.toString
      case None => "nothing"
      case Some(num: Int) => num.toString
      case "hello" => "hi!"
      case Nil => "the empty list"
      case _ => "something else"
    }
  }

  val lists = List(None, Some(6), 5, Nil, "hello", List(2, 3))
  val zippedlists = lists zip lists.map(x => describe(x))

  println(zippedlists)
}

object ClassIntro extends App {

  class Point(var x: Int, var y: Int) {
    def move(dx: Int, dy: Int): Unit = {
      x = x + dx; y = y + dy
    }
    override def toString: String = "(" + x + ", " + y + ")"
  }

  val pt = new Point(1, 2)
  println(pt)
  pt.move(10, 10)
  println(pt)

  println("***********")

  println("abstract ")

  abstract class Pet (name: String) {
    val greeting: String
    var age: Int
    def sayHello: Unit = println(greeting)
    override def toString: String = s"I say $greeting, and I'm $age"
  }

  class Dog (name: String) extends Pet (name) {
    val greeting = "Woof"
    var age = 2
  }

  class Cat (name: String) extends Pet (name) {
    val greeting = "Meow"
    var age = 5
  }

  val dog = new Dog("Fido")
  val cat = new Cat("Morris")
  dog.sayHello
  cat.sayHello
  println(dog)
  println(cat)
  // verify that the age can be changed
  cat.age = 10
  println(cat)

  case class CatCase (name: String) extends Pet (name) {
    val greeting = "Meow"
    var age = 5
  }

  val catCase = CatCase("Morris")
  // println(cat.name) will not compile
  println(catCase.name)

  println(TypeMatch.describe(5))
}

object MixinCompo extends App {

  trait Runner {
    def run(feet: Double): Unit = {
      println (s"ran: $feet feets")
    }
  }

  trait Walker {
    def walk(feet: Double): Unit = {
      println (s"Walked: $feet feets")
    }
  }

  class Person extends Walker with Runner

  val person = new Person

  person.walk(20)
  person.run(30)

  // We can mixin traits while instantiating classes
  class Human()

  val human = new Human with Walker with Runner
  human.walk(20)
  human.run(30)

}

object TitanicExample extends App {

  implicit object MyFormat extends DefaultCSVFormat {
    override val delimiter = '\t'
  }

  def datReader(path: String): List[Map[String, String]] = {
    val reader = CSVReader.open(new File(path))
    val dat = reader.allWithHeaders()
    reader.close()
    dat
  }

  val titanicTrainData = datReader("src/main/resources/TitanicTrain.txt")

  def getNoneFromEmpty (str: Option[String]) : Option[String] = {
    str match {
      case Some("") => None
      case other => other
    }
  }

  def datFormat(dat: List[Map[String, String]]): Seq[Passenger] = {
    dat.map(pInfo => Passenger
    (pInfo.getOrElse("PassengerId", "0"), pInfo.getOrElse("Pclass", "0").toInt,
      pInfo.getOrElse("Name", "NA"), pInfo.getOrElse("Sex", "NA"),
      pInfo.get("Age").flatMap(s => Try(s.toInt).toOption), pInfo.getOrElse("Sibsp", "0").toInt,
      pInfo.getOrElse("Parch", "0").toInt, pInfo.getOrElse("Ticket", "NA"),
      pInfo.getOrElse("Fare", "0").toDouble, getNoneFromEmpty(pInfo.get("Cabin")),
      pInfo.getOrElse("Pclass", "NA")))
  }

  val titanicTrain = datFormat(titanicTrainData)

  println(titanicTrain.head)

  // try yourself!
  // TODO: load the label data from "src/main/resources/TitanicTrainLabels.txt"
  // TODO: revise the case class + Survived: Int; merge the titanicTrain with the label
  // TODO: calculate the percentage of survivors
  // TODO: get the Null value percentage for Age and Cabin
  // TODO: calculate the correlation for sex and survived

}

case class Passenger(PassengerId: String, Pclass: Int, Name: String, Sex: String,
                     Age: Option[Int], Sibsp: Int, Parch: Int, Ticket: String, Fare: Double,
                     Cabin: Option[String], Embarked: String)