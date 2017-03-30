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

  println("***********")
  println("convert types")
  println(3.toDouble)
}

object Practice0 extends App
// create a Map[(Char, int)] called letter count with 'a'-> 2, 'b' -> 4, 'c' -> 5
// find out what are map.get('a') and map.get('d'), and whether they are empty or not
// convert the Map into a List
// print out the first element of this list
// print out the count of 'a's

object IfFor extends App {

  println("***********")
  // example of if else and function
  val test1 = None
  val test2 = Some("some")

  def getOrNothing(x: Option[String]): String = {
    if (x.isDefined) x.get
    else ""
  }
  // same as .getOrElse("nothing")
  println(getOrNothing(test1))

  // example of for loop
  for {x <- 0 to 10} println(x)

  // println - print in a new line
}

object Practice1 extends App {
  def pascal(c: Int, r: Int): Int = ???

  // print the first 10 lines of numbers
  /*
  1
  1 1
  1 2 1
  1 3 3 1
  1 4 6 4 1
  1 5 10 10 5 1
  1 6 15 20 15 6 1
  1 7 21 35 35 21 7 1
  1 8 28 56 70 56 28 8 1
  1 9 36 84 126 126 84 36 9 1
  1 10 45 120 210 252 210 120 45 10 1
   */

}

object BasicFun extends App {

  val abcde = List("a", "b", "c", "d", "e")
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

  // functions - reduce

  val nums = Array(20, 12, 6, 15, 2, 9)
  println(nums.reduceLeft(_ + _))
  println(nums.sum)
  println("***********")

  val counts = List(("a", 1), ("a", 2), ("b", 3), ("c", 1), ("a", 1))
  println(counts.groupBy(_._1))

}

object Practice2 extends App {
  /** Converts the word into its character occurrence list.
    *
    *  Note: the uppercase and lowercase version of the character are treated as the
    *  same character, and are represented as a lowercase character in the occurrence list.
    *
    *  Note: you must use `groupBy` to implement this method!
    */
  def wordOccurrences(w: String): List[(Char, Int)] = ???

  /** Converts a sentence into its character occurrence list. */
  def sentenceOccurrences(s: List[String]): List[(Char, Int)] = ???

  /** Subtracts occurrence list `y` from occurrence list `x`.
    *
    *  The precondition is that the occurrence list `y` is a subset of
    *  the occurrence list `x` -- any character appearing in `y` must
    *  appear in `x`, and its frequency in `y` must be smaller or equal
    *  than its frequency in `x`.
    *
    *  Note: the resulting value is an occurrence - meaning it is sorted
    *  and has no zero-entries.
    */
  def subtract(x: List[(Char, Int)], y: List[(Char, Int)]): List[(Char, Int)] = ???
}

object customizedFun extends App {
  println("customized function")
  def add(x: Int, y: Int): Int = x + y

  println(add(1, 2))

  val add2 = (x: Int, y: Int) => x + y
  println(add2(1, 2))

  // && represent and; || represent or
}

object Practice3 extends App {
  /**
    * We represent a set by its characteristic function, i.e.
    * its `contains` predicate.
    */
  type Set = Int => Boolean

  /**
    * Indicates whether a set contains a given element.
    */
  def contains(s: Set, elem: Int): Boolean = ???

  /**
    * Returns the set of the one given element.
    */
  def singletonSet(elem: Int): Set = x => ???

  /**
    * Returns the union of the two given sets,
    * the sets of all elements that are in either `s` or `t`.
    */
  def union(s: Set, t: Set): Set = ???

  /**
    * The bounds for `forall` and `exists` are +/- 1000.
    */
  val bound = 1000

  /**
    * Returns whether all bounded integers within `s` satisfy `p`.
    */
  def forall(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      if (???) ???
      else if (???) ???
      else iter(???)
    }
    iter(???)
  }
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