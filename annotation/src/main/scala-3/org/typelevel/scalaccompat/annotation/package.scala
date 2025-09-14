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

package org.typelevel.scalaccompat

/** Custom annotations for Scala v3
  */
package object annotation {
  import internal.*

  type nowarn    = scala.annotation.nowarn
  type nowarn2   = nowarnIgnored
  type nowarn212 = nowarnIgnored
  type nowarn213 = nowarnIgnored
  type nowarn3   = nowarn

  type static3 = scala.annotation.static

  type targetName3 = scala.annotation.targetName

  type threadUnsafe3 = scala.annotation.threadUnsafe

  type uncheckedVariance    = scala.annotation.unchecked.uncheckedVariance
  type uncheckedVariance2   = uncheckedVarianceIgnored
  type uncheckedVariance212 = uncheckedVarianceIgnored
  type uncheckedVariance213 = uncheckedVarianceIgnored
  type uncheckedVariance3   = uncheckedVariance

  type unused = scala.annotation.unused
}
