/*

 */

package callgraph.zio

import callgraph.{CallGraph, CallGraphEvaluator}
import zio.*
import scala.quoted.*

object ZIOCallGraphEvaluator extends CallGraphEvaluator:

  type ZioAny = ZIO[Any, Any, Any]
  // for reference, see FiberContext.evaluateNow
  // e.g. https://github.com/zio/zio/blob/master/core/shared/src/main/scala/zio/internal/FiberContext.scala#L275
/*   inline def evaluateNow(io0: Expr[ZioAny]): UIO[CallGraph] = ${
    uioSucceed(valName(io0))
  }
  def uioSucceed(name: Expr[String])(using Quotes): Expr[UIO[CallGraph]] = '{ UIO.succeed(new CallGraph(${name}) {}) }

  def valName(value: Expr[Any])(using Quotes): Expr[String] = Expr(value.show)  
 */

  inline def evaluateNow(inline io0: ZioAny): UIO[CallGraph] = ${evaluateNowImpl('io0)}  

  def evaluateNowImpl(expr: Expr[ZioAny])(using Quotes): Expr[UIO[CallGraph]] = '{ UIO.succeed(new CallGraph(${Expr(expr.show)}) {}) }

