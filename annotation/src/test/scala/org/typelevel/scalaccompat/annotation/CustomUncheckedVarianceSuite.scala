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

import CustomUncheckedVarianceHelper._

object CustomUncheckedVarianceSuite {

  class Covariant[+A]    extends Invariant[A @uncheckedVariance]
  class Covariant2[+A]   extends Invariant2[A @uncheckedVariance2]
  class Covariant212[+A] extends Invariant212[A @uncheckedVariance212]
  class Covariant213[+A] extends Invariant213[A @uncheckedVariance213]
  class Covariant3[+A]   extends Invariant3[A @uncheckedVariance3]

}
