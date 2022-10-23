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

/** Not really a usual test suite. The purpose of this class is to fail compilation if any of the
  * annotation tested is not working as expected.
  */
class CustomNowarnSuite {
  import CustomNowarnHelper._

  def testNowarn() = {
    deprecatedEverywhere(): @nowarn("cat=deprecation")
  }
  def testNowarn212() = {
    deprecatedInScala212(): @nowarn212("cat=deprecation")
  }
  def testNowarn213() = {
    deprecatedInScala213(): @nowarn213("cat=deprecation")
  }
  def testNowarn2() = {
    deprecatedInScala2(): @nowarn2("cat=deprecation")
  }
  def testNowarn3() = {
    deprecatedInScala3(): @nowarn3("cat=deprecation")
  }
}
