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

class CustomTargetNameSuite extends FunSuite {

  @targetName3("bar")
  def foo(): Unit = ()

  test("targetName3 respected on Scala 3 only") {
    val methods = getClass().getMethods().map(_.getName)

    if (CustomTargetNameHelper.isScala3) {
      assert(clue(methods).contains("bar"))
      assert(!clue(methods).contains("foo"))
    } else { // Scala 2
      assert(clue(methods).contains("foo"))
      assert(!clue(methods).contains("bar"))
    }
  }

}
