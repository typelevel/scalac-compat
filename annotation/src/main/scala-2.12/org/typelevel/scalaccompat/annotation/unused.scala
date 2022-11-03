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

import scala.annotation.meta

/** Mimics `@unused` from Scala v2.13 and v3 libraries. It reproduces the same singature of the
  * original annotation, but inerits to `@nowarn` rather than `ConstantAnnotation`.
  *
  * @param message
  *   currently not in use
  * @see
  *   [[https://github.com/scala/scala/blob/2.13.x/src/library/scala/annotation/unused.scala]]
  */
@meta.getter @meta.setter
@nowarn(
  "cat=other&msg=Implementation restriction: subclassing ClassfileAnnotation does not\nmake your annotation visible at runtime."
)
class unused(@nowarn("cat=unused") message: String) extends nowarn("cat=unused") {
  def this() = this("")
}
