/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
class IvTest extends org.scalatest.FunSuite {
  test("Intelligence.value") {
    val d = (0 to 3)
    assert(Iv.superset(4, d.toArray).size === 15)
    assert(Iv.superset(0, d.toArray).size === 4)
    assert(Iv.superset(1, d.toArray).size === 4)
    assert(Iv.superset(2, d.toArray).size === 10)
    assert(Iv.superset(3, d.toArray).size === 14)
    assert(Iv.superset(104, d.toArray).size === 15)
  }
}
