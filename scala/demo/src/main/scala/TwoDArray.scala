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

/**
 * We define an hourglass in  to be a subset of values
 * with indices falling in this pattern in arr's graphical representation:
   a b c
     d
   e f g
 * There are 16 hourglasses in arr, and an hourglass sum is the 
 sum of an hourglass' values. Calculate the hourglass sum for every hourglass 
 in arr, then print the maximum hourglass sum.
**/
object TwoDArray {

    /** Evaluate the minimum jump to finish
      *
      *  @param arr two dimension array with values to compute hourglass
      *  @return largest hourglass sum found in arr
      */
    def hourglassSum(arr: Array[Array[Int]]): Int = {
      val leftTop = (0 to 3).map(x => Seq((x,0), (x,1), (x,2), (x,3))).reduce(_ ++ _)
      leftTop.map(x => arr(x._1)(x._2) + arr(x._1)(x._2+1) 
        + arr(x._1)(x._2+2) + arr(x._1+1)(x._2+1) + arr(x._1+2)(x._2) 
        + arr(x._1+2)(x._2+1) + arr(x._1+2)(x._2+2)).max
    }

    def main(args: Array[String]) {
        val stdin = scala.io.StdIn

        val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

        val arr = Array.ofDim[Int](6, 6)

        for (i <- 0 until 6) {
            arr(i) = stdin.readLine.split(" ").map(_.trim.toInt)
        }

        val result = hourglassSum(arr)

        printWriter.println(result)

        printWriter.close()
    }
}