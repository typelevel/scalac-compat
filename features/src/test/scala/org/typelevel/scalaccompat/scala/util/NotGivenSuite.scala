/*
 * Copyright 2022 Typelevel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package other.org.typelevel.scalaccompat.scala.util

import org.typelevel.scalaccompat.scala.util.NotGiven
import munit.FunSuite

/** Examples demonstrating how to use `NotGiven` for cross-version compatibility.
  *
  * `NotGiven` is a feature from Scala 3 that allows providing logic that depends on the absence of
  * an implicit instance. This module provides a Scala 2 implementation that mimics this behavior
  * using implicit ambiguity, enabling easier cross-compilation of code that relies on it.
  */
class NotGivenSuite extends FunSuite {

  /** Basic example: `NotGiven[A]` exists if and only if no implicit instance of `A` is in scope.
    */
  test("NotGiven[Int] should exist") {
    assert(implicitly[NotGiven[Int]] != null)
  }

  trait Exists
  implicit val exists: Exists = new Exists {}

  // This should NOT compile:
  // implicitly[NotGiven[Exists]]

  test("NotGiven[Exists] should not compile") {
    val s = compileErrors("""implicitly[NotGiven[Exists]]""")

    val scala2Assertion = s.startsWith("error: Found an implicit value of type")
    val scala3Assertion = s.startsWith("error: No given instance of type scala.util.NotGiven")
    assert(scala2Assertion || scala3Assertion)
  }

  /** Shows how to use `NotGiven` as a way to provide a fallback value when another type's implicit
    * is missing from the environment.
    */
  test("NotGiven allows providing a fallback when another type is missing") {
    trait A

    def choice(implicit @scala.annotation.unused ev: NotGiven[A]): String = "NotGiven[A]"

    assertEquals(choice, "NotGiven[A]")
  }

  /** `NotGiven` can be used to achieve mutual exclusion between two implicit instances.
    */
  test("Mutual exclusion example") {
    trait TagA
    trait TagB

    def onlyOneA(implicit
      @scala.annotation.unused ev: TagA,
      @scala.annotation.unused ng: NotGiven[TagB]
    ): String = "A only"
    def onlyOneB(implicit
      @scala.annotation.unused ev: TagB,
      @scala.annotation.unused ng: NotGiven[TagA]
    ): String = "B only"

    {
      implicit val a: TagA = new TagA {}
      assertEquals(onlyOneA, "A only")
    }

    {
      implicit val b: TagB = new TagB {}
      assertEquals(onlyOneB, "B only")
    }
  }

  /** A common pattern for providing default typeclass instances only if the user hasn't defined
    * their own specific instance.
    */
  test("Default instance example") {
    trait SimpleType[A]

    def useSimple[A](implicit ev: SimpleType[A]): String = ev.toString

    trait FallbackLowPriority {
      implicit def fallback[A](implicit
        @scala.annotation.unused ev: NotGiven[List[A]]
      ): SimpleType[A] = new SimpleType[A] {
        override def toString = "fallback"
      }
    }
    object Alternative extends FallbackLowPriority
    import Alternative.fallback

    assertEquals(useSimple[Int], "fallback")

    {
      implicit val custom: SimpleType[Int] = new SimpleType[Int] {
        override def toString = "custom"
      }
      assertEquals(useSimple[Int], "custom")
    }
  }

}
