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

class CustomStaticDemo
object CustomStaticDemo {
  @static3 val foo = new Object
}

class CustomStaticSuite extends FunSuite {

  test("static3 respected on Scala 3 only") {
    val fields = classOf[CustomStaticDemo].getFields().map(_.getName).toList

    if (ScalacVersionHelper.isScala3) {
      assertEquals(fields, List("foo"))
    } else { // Scala 2
      assertEquals(fields, Nil)
    }
  }

}
