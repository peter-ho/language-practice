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
 
class FrequencyQueriesTest extends org.scalatest.FunSuite {
  test("FrequencyQueriesTest.freqQuery") {
    assert(FrequencyQueries.freqQuery(Array(
      Array(1, 5),
      Array(1, 6),
      Array(3, 2),
      Array(1, 10),
      Array(1, 10),
      Array(1, 6),
      Array(2, 5),
      Array(3, 2)
    )).deep == Array(0, 1).deep)

    assert(FrequencyQueries.freqQuery(Array(
      Array(1, 3),
      Array(2, 3),
      Array(3, 2),
      Array(1, 4),
      Array(1, 5),
      Array(1, 5),
      Array(1, 4),
      Array(3, 2),
      Array(2, 4),
      Array(3, 2)
    )).deep == Array(0, 1, 1).deep)
  }
}