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

package org.typelevel.scalaccompat.annotation

import munit.FunSuite

import java.util.concurrent.atomic.AtomicInteger
import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.Future

class CustomThreadUnsafeSuite extends FunSuite {

  val counter = new AtomicInteger

  @threadUnsafe3
  lazy val foo = {
    counter.incrementAndGet()
    Thread.sleep(100)
    ()
  }

  test("threadUnsafe3 respected on Scala 3 only") {
    Future.sequence(List(Future(foo), Future(foo))).map { _ =>
      if (ScalacVersionHelper.isScala3) {
        assertEquals(counter.get(), 2)
      } else { // Scala 2
        assertEquals(counter.get(), 1)
      }
    }
  }

}
