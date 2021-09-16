/*

 */

package callgraph.zio

import callgraph.{CallGraph, CallGraphEvaluator}
import scala.compiletime.uninitialized as ???
import zio.*

object ZIOCallGraphEvaluator extends CallGraphEvaluator:
  // for reference, see FiberContext.evaluateNow
  // e.g. https://github.com/zio/zio/blob/master/core/shared/src/main/scala/zio/internal/FiberContext.scala#L275
  def evaluateNow[E](io0: IO[E, Any]): UIO[CallGraph] = UIO.succeed(new CallGraph {})
