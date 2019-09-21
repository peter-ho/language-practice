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

import java.io._
import java.math._
import java.security._
import java.text._
import java.util._
import java.util.concurrent._
import java.util.function._
import java.util.regex._
import java.util.stream._
import scala.collection.immutable.Set

/**
 * Emma is playing a new mobile game that starts with consecutively numbered clouds. 
 * Some of the clouds are thunderheads and others are cumulus. She can jump on any cumulus 
 * cloud having a number that is equal to the number of the current cloud plus 1 or 2. 
 * She must avoid the thunderheads. Determine the minimum number of jumps it will take Emma 
 * to jump from her starting postion to the last cloud. It is always possible to win the game.

  For each game, Emma will get an array of clouds numbered 0 if they are safe or 1 if they must be avoided. 
  For example, [0,1,0,0,0,1,0] indexed from 0...6. The number on each cloud is its index in the list 
  so she must avoid the clouds at indexes 1 and 5. She could follow the following two paths: 
  0 -> 2 -> 4 -> 6 or 0 -> 2 -> 3 -> 4 -> 6. The first path takes 3 jumps while the second takes 4. 
**/
object Iv {

    /** Evaluate the superset of a set of integer
      *
      *  @cnt  maximum number of elements to be included in each set
      *  @data an array of Integer to be expanded to its super set
      *  @return a set of number set that contains each combination of input set
      */
  def superset(cnt:Int, data:Array[Int]): scala.collection.mutable.Set[Set[Int]] = {
    if (cnt >= data.length) {
      val curr = superset(data.length-1, data)
      curr.add(Set(data:_*))
      curr
    } else {
      val curr = scala.collection.mutable.Set[Set[Int]]()
      if (cnt > 1) {
        val last = superset(cnt - 1, data)
        last.foreach(y => {
          data.foreach(x => {
            curr.add(y + x)
          })
        })
      } else {
        data.foreach(x => curr.add(Set(x)))
      }
      curr
    }
  }


    /** Evaluate the minimum jump to finish
      *
      *  @param c cloud array indicating 0 for safe and 1 for avoid
      *  @return number of minimum jump to finish
      */

    // Complete the jumpingOnClouds function below.
    def jumpingOnClouds(c: Array[Int]): Int = {
      var moves = 0
      var c1 = c.tail
      while (c1.length > 0) {
        if (c1.length == 1) { moves += 1; c1 = c1.tail }
        else if (c1.tail.head == 0) { moves += 1; c1 = c1.tail.tail }
        else { moves = moves + 1; c1 = c1.tail }
      }
      moves
    }

    def main(args: Array[String]) {
        val stdin = scala.io.StdIn

        val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

        val n = stdin.readLine.trim.toInt

        val c = stdin.readLine.split(" ").map(_.trim.toInt)
        val result = jumpingOnClouds(c)

        printWriter.println(result)

        printWriter.close()
    }
}
