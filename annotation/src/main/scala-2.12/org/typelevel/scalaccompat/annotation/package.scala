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

/** Custom annotations for Scala v2.12
  */
package object annotation {
  import internal.*

  type nowarn    = scala.annotation.nowarn
  type nowarn2   = nowarn
  type nowarn212 = nowarn
  type nowarn213 = nowarnIgnored
  type nowarn3   = nowarnIgnored

  type static3 = staticIgnored

  type targetName3 = targetNameIgnored

  type threadUnsafe3 = threadUnsafeIgnored

  type uncheckedVariance    = scala.annotation.unchecked.uncheckedVariance
  type uncheckedVariance2   = uncheckedVariance
  type uncheckedVariance212 = uncheckedVariance
  type uncheckedVariance213 = uncheckedVarianceIgnored
  type uncheckedVariance3   = uncheckedVarianceIgnored
}
