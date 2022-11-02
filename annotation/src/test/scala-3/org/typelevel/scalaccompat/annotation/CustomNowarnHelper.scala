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

object CustomNowarnHelper {

  def notDeprecated() = ()

  @deprecated("deprecated for all Scala versions", "forever")
  def deprecatedEverywhere() = ()

  def deprecatedInScala212() = ()

  def deprecatedInScala213() = ()

  def deprecatedInScala2() = ()

  @deprecated("deprecated for Scala v3", "forever")
  def deprecatedInScala3() = ()

  @deprecated("deprecated for Scala v3", "forever")
  def deprecatedInScala212andScala3() = ()

  @deprecated("deprecated for Scala v3", "forever")
  def deprecatedInScala213andScala3() = ()
}
