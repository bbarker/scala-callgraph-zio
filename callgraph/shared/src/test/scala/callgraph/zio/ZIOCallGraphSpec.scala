package callgraph.zio

import callgraph.zio.ZIOCallGraphEvaluator.*
import zio.*
import zio.test.Assertion.*
import zio.test.*
import zio.test.environment.TestConsole
import java.io.IOException
import scala.compiletime.ops.any

object ZIOCallGraphSepc extends DefaultRunnableSpec:
  def spec = suite("ZIOCallGraph tests")(
    suiteEvaluateNow,
  )

  val suiteEvaluateNow: ZSpec[ZEnv & Has[TestConsole], Throwable] = zio.test.suite("ZIO evaluateNow")(
    test(s"evaluateNow identifies top-level effect nam") {
      val uioInt7 = UIO.succeed(7)
      for {
        cg <- evaluateNow(uioInt7)
      } yield assert(cg.metaData)(equalTo("uioInt7"))
    },
  )
