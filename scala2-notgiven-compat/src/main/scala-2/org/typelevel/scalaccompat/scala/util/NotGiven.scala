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

package org.typelevel.scalaccompat.scala.util

import scala.annotation.{implicitAmbiguous, unused}

sealed trait NotGiven[A]

object NotGiven {
  implicit def notGiven[A]: NotGiven[A] = new NotGiven[A] {}

  @implicitAmbiguous("Found an implicit value of type ${A}, so NotGiven[${A}] cannot be satisfied.")
  implicit def amb1[A](implicit @unused ev: A): NotGiven[A] = null
  implicit def amb2[A](implicit @unused ev: A): NotGiven[A] = null
}
